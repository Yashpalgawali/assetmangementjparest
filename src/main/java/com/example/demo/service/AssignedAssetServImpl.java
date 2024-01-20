package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.example.demo.models.AssetAssignHistory;
import com.example.demo.models.Assets;
import com.example.demo.models.AssignedAssets;
import com.example.demo.models.Employee;
import com.example.demo.repository.AssetAssignHistoryRepo;
import com.example.demo.repository.AssetRepo;
import com.example.demo.repository.AssignedAssetsRepo;
import com.example.demo.repository.EmployeeRepo;

@Service("assignassetserv")
public class AssignedAssetServImpl implements AssignedAssetService {

	@Autowired
	AssignedAssetsRepo assignassetrepo;
	
	@Autowired
	AssetAssignHistoryRepo assignhistrepo;
	
	@Autowired
	AssetRepo assetrepo;
	
	@Autowired
	EmployeeRepo emprepo;
	
	private DateTimeFormatter ddate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	private DateTimeFormatter dtime = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	@Override
	public AssignedAssets saveAssignedAssets(AssignedAssets assign) {
		AssignedAssets asset = assignassetrepo.save(assign);
		if(asset!=null)
		{
			System.out.println("\n Assets assigned successfully\n");
		}
		else {
			System.out.println(" Assets are NOT assigned \n");
		}
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
	public int retrieveAssetByEmpId(AssignedAssets assign) {
		String asset_ids = assign.getMulti_assets();
		String[] strarr	=   asset_ids.split(",");
		int res = 0;
		
		for(int i=0;i<strarr.length;i++) {
			Long asid = Long.valueOf(strarr[i]);
			Assets asset = new Assets();
			asset=assetrepo.findById(asid).get();
			Employee emp = emprepo.getAllEmployeeById(assign.getEmp_id());
			res  = assignassetrepo.deleteAssignedAssetByEmpidAssetId(asid, assign.getEmp_id());
			int asset_qty = assetrepo.getQuantiyByAssetId(asid);
			asset_qty +=1;
			assetrepo.updateAssetQuantityByAssetId(asid, ""+asset_qty);
			AssetAssignHistory ahist = new AssetAssignHistory();
			ahist.setOperation("Asset "+asset.getAsset_name()+" Retrieved From "+emp.getEmp_name());
			ahist.setOperation_date(ddate.format(LocalDateTime.now()));
			ahist.setOperation_time(dtime.format(LocalDateTime.now()));
			assignhistrepo.save(ahist);
			ahist.setOperation("Quantity of asset "+asset.getAsset_name()+" is increased to "+asset_qty);
			ahist.setOperation_date(ddate.format(LocalDateTime.now()));
			ahist.setOperation_time(dtime.format(LocalDateTime.now()));
			assignhistrepo.save(ahist);
		}
		return res;
	}

	@Override
	public List<Object[]> getAllAssignedassetsGroup() {
		try {
			List<Object[]> nobj = assignassetrepo.getAllNewAssignedAssets();
			return nobj;
		}
		catch(Exception e)
		{
			return null;
		}
	}

	@Override
	public List<AssignedAssets> getOnlyAssignedAssetsByEmpId(Long empid) {
		
		return assignassetrepo.getOnlyAssignedAssetsByEmpId(empid);
	}
}
