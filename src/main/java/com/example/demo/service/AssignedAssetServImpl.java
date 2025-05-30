package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.models.Activity;
import com.example.demo.models.AssetAssignHistory;
import com.example.demo.models.Assets;
import com.example.demo.models.AssignedAssets;
import com.example.demo.models.Employee;
import com.example.demo.models.RetrievedAssets;
import com.example.demo.repository.ActivityRepo;
import com.example.demo.repository.AssetAssignHistoryRepo;
import com.example.demo.repository.AssetRepo;
import com.example.demo.repository.AssignedAssetsRepo;
import com.example.demo.repository.EmployeeRepo;
import com.example.demo.repository.RetrievedAssetsRepository;

@Service("assignassetserv")
public class AssignedAssetServImpl implements AssignedAssetService {

	private	AssignedAssetsRepo assignassetrepo;
	
	private AssetAssignHistoryRepo assignhistrepo;
	
	private AssetRepo assetrepo;
	
	private EmployeeRepo emprepo;
	
	private ActivityRepo actrepo;
	
	private RetrievedAssetsRepository retrievedassetsrepo;
	
	

	public AssignedAssetServImpl(AssignedAssetsRepo assignassetrepo, AssetAssignHistoryRepo assignhistrepo,
			AssetRepo assetrepo, EmployeeRepo emprepo, ActivityRepo actrepo,
			RetrievedAssetsRepository retrievedassetsrepo) {
		super();
		this.assignassetrepo = assignassetrepo;
		this.assignhistrepo = assignhistrepo;
		this.assetrepo = assetrepo;
		this.emprepo = emprepo;
		this.actrepo = actrepo;
		this.retrievedassetsrepo = retrievedassetsrepo;
	}

	private DateTimeFormatter ddate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	private DateTimeFormatter dtime = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	@Override
	public AssignedAssets saveAssignedAssets(AssignedAssets assign) {
		AssignedAssets asset = assignassetrepo.save(assign);
		return  asset;
	}

	@Override
	public List<AssignedAssets> getAllAssignedAssets() {
		List<AssignedAssets> alist =  assignassetrepo.getAllAssignedAssets(); 
		return alist;
	}

	@Override
	public List<AssignedAssets> getAssignedAssetsByEmpId(Long empid) {
		return assignassetrepo.getAllAssignedAssetsByEmpId(empid);
	}

	@Override
	public int retrieveAssetByEmpId(Employee employee) {
		Long eid = employee.getEmp_id();
		List<AssignedAssets> ass_list = assignassetrepo.getAllAssignedAssetsByEmpId(eid);
		
		String asts = "";
		int res = 0;
		if(ass_list.size()>0)
		{
			for(int i=0;i<ass_list.size();i++)
			{
				if(i==0) { asts = ""+ass_list.get(i).getAsset().getAsset_id() ; }
				else { asts = asts+","+ass_list.get(i).getAsset().getAsset_id(); }
			}
		
			//String asset_ids = assign.getMulti_assets();
			String asset_ids = asts;
			String[] strarr	 = asset_ids.split(",");
		
			for(int i=0;i<strarr.length;i++) {
				Long asid = Long.valueOf(strarr[i]);
				Assets asset = new Assets();
				asset=assetrepo.findById(asid).get();

				Employee emp = emprepo.getEmployeeById(eid).get();

				res  = assignassetrepo.deleteAssignedAssetByEmpidAssetId(asid, eid);
				int asset_qty = assetrepo.getQuantiyByAssetId(asid);
				asset_qty +=1;
				assetrepo.updateAssetQuantityByAssetId(asid, ""+asset_qty);

				AssetAssignHistory ahist = new AssetAssignHistory();
				ahist.setOperation("Asset "+asset.getAsset_name()+" Retrieved From "+emp.getEmp_name());
				ahist.setOperation_date(ddate.format(LocalDateTime.now()));
				ahist.setOperation_time(dtime.format(LocalDateTime.now()));
				ahist.setAsset(asset);
				ahist.setEmployee(emp);
				assignhistrepo.save(ahist);

				RetrievedAssets retrievedAssets = new RetrievedAssets();
				retrievedAssets.setAsset_ids(asid);
				retrievedAssets.setEmp_id(eid);
				retrievedAssets.setComments(employee.getComments());

				retrievedassetsrepo.save(retrievedAssets);

				Activity act = new Activity();
				act.setActivity("Quantity of asset "+asset.getAsset_name()+" is increased to "+asset_qty);
				ahist.setOperation_date(ddate.format(LocalDateTime.now()));
				ahist.setOperation_time(dtime.format(LocalDateTime.now()));
				actrepo.save(act);
			}
			return res;
		}
		else {
			return res;
		}
	}

	@Override
	public List<Object[]> getAllAssignedassetsGroup() {
		try {
			List<Object[]> nobj = assignassetrepo.getAllNewAssignedAssets();
			return nobj;
		}
		catch(Exception e) {
			return null;
		}
	}

	@Override
	public List<AssignedAssets> getOnlyAssignedAssetsByEmpId(Long empid) {
		List<AssignedAssets> assignedList = assignassetrepo.getOnlyAssignedAssetsByEmpId(empid);
		
		assignedList.forEach(System.out::println);
		
		if(assignedList.size()>0) {
			return assignedList;
		}
		else {
			throw new ResourceNotFoundException("No assets are assigned to the Employee for retrieval ");
		}
	}
}
