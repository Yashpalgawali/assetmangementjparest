package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Activity;
import com.example.demo.models.AssetAssignHistory;
import com.example.demo.models.AssetType;
import com.example.demo.models.Assets;
import com.example.demo.models.AssignedAssets;
import com.example.demo.models.Employee;
import com.example.demo.repository.ActivityRepo;
import com.example.demo.repository.AssetAssignHistoryRepo;
import com.example.demo.repository.AssetRepo;
import com.example.demo.repository.AssetTypeRepo;
import com.example.demo.repository.AssignedAssetsRepo;
import com.example.demo.repository.EmployeeRepo;

@Service("empserv")
public class EmployeeServImpl implements EmployeeService {

	@Autowired
	EmployeeRepo emprepo;
	
	@Autowired
	AssignedAssetsRepo assignassetrepo;
	
	@Autowired
	AssetRepo assetrepo;
	
	@Autowired
	AssetAssignHistoryRepo assetassignhistrepo; 
	
	@Autowired
	AssetTypeRepo atyperepo;
	
	@Autowired
	ActivityRepo actrepo;
	
	private LocalDateTime today;
	
	private DateTimeFormatter ddate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	private DateTimeFormatter dtime = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	private String tday=ddate.format(LocalDateTime.now()),ttime=dtime.format(LocalDateTime.now());
	
	@Override
	public Employee saveEmployee(Employee emp) {
		
		Employee empl = emprepo.save(emp);
		if(empl!=null) {
			Activity act = new Activity();
			act.setActivity(emp.getEmp_name()+" is saved successfully");
			act.setOperation_date(ddate.format(LocalDateTime.now()));
			act.setOperation_time(dtime.format(LocalDateTime.now()));
			actrepo.save(act);
			return empl;
		}
		else {
			return null;
		}
	}

	@Override
	public List<Employee> getAllEmployees() {
		try {
			return emprepo.findAll();
			//return emprepo.getAllEmployees();
		}
		catch(Exception e) {
			return null;
		}
	}

	@Override
	public Employee getEmployeeById(String empid) {
		if(empid!=null) {
			try {
				return emprepo.getAllEmployeeById(Long.valueOf(empid));
			}
			catch(Exception e) {
				return null;
			}
		}
		else {
			return null;
		}
	}

	@Override
	public int updateEmployee(Employee emp) {
		String new_assets = "";
		List<String> nl = emp.getAsset_ids();

		for(int i=0;i<nl.size();i++) {
			if(i==0) {
				new_assets = nl.get(i);
			}
			else {
				new_assets = new_assets+","+nl.get(i);
			}
		}
		AssignedAssets isassigned = null;
		int res = emprepo.updateEmployee(emp.getEmp_name(), emp.getEmp_email(), emp.getEmp_contact(), emp.getDepartment().getDept_id(), emp.getDesignation().getDesig_id(), emp.getEmp_id());
		
		List<AssignedAssets> assigned_assets = assignassetrepo.getAllAssignedAssetsByEmpId(emp.getEmp_id());
		
		String[] ol_assets = new String[assigned_assets.size()];
		String[] nw_assets = new String[new_assets.length()];
		
		nw_assets = new_assets.split(",");
		for(int i=0;i<assigned_assets.size();i++)
		{
			ol_assets[i] = assigned_assets.get(i).getAsset().getAsset_id().toString();
		}
		
		if(ol_assets.length==nw_assets.length)
		{
			List<String> olist= Arrays.asList(ol_assets);
			List<String> nlist= Stream.of(new_assets.split(",")).collect(Collectors.toList());
			
			for(int i=0;i<ol_assets.length;i++)
			{
				if(nlist.contains(ol_assets[i])) {
					continue;
				}
				else {
					Long asid = Long.valueOf(ol_assets[i]);
					int output = assignassetrepo.deleteAssignedAssetByEmpidAssetId(asid, emp.getEmp_id());
					
					if(output>0) {	
						int qty = assetrepo.getQuantiyByAssetId(asid);
						qty+=1;
						
						assetrepo.updateAssetQuantityByAssetId(asid, ""+qty);
						AssetAssignHistory ahist = new AssetAssignHistory();
						
						Assets ast = new Assets();
						
						Assets getasset = assetrepo.findById(asid).get();
						
						AssetType atype = new AssetType();
						
						atype = atyperepo.findById(getasset.getAtype().getType_id()).get();
						
						ast.setAtype(atype);
						
						ast.setAsset_id(asid);
						ast.setAsset_name(getasset.getAsset_name());
						ast.setAsset_number(getasset.getAsset_number());
						ast.setModel_number(getasset.getModel_number());
						ast.setQuantity(getasset.getQuantity());
						
						ahist.setAsset(ast);
						ahist.setEmployee(emp);
						ahist.setOperation_date(ddate.format(LocalDateTime.now()));
						ahist.setOperation_time(dtime.format(LocalDateTime.now()));
						ahist.setOperation("Asset Retrieved");
						
						assetassignhistrepo.save(ahist);
						Activity act = new Activity();
						act.setActivity("Asset "+getasset.getAsset_name()+" retrieved from "+ emp.getEmp_name());
						act.setOperation_date(ddate.format(LocalDateTime.now()));
						act.setOperation_time(dtime.format(LocalDateTime.now()));
						actrepo.save(act);
					}
				}
			}
			
			for(int i=0;i<nw_assets.length;i++)
			{
				if(olist.contains(nw_assets[i]))
				{
					continue;
				}
				else
				{
					AssignedAssets assignasset = new AssignedAssets();
					Long asid = Long.valueOf(nw_assets[i]);
					int qty =0;
					
					Long astid = Long.valueOf(asid);
					Assets ast = new Assets();
					Assets getasset = assetrepo.findById(astid).get();
					
					AssetType atype = new AssetType();
					
					atype = atyperepo.findById(getasset.getAtype().getType_id()).get();
					
					ast.setAtype(atype);
					
					ast.setAsset_id(astid);
					ast.setAsset_name(getasset.getAsset_name());
					ast.setAsset_number(getasset.getAsset_number());
					ast.setModel_number(getasset.getModel_number());
					ast.setQuantity(getasset.getQuantity());
					
					assignasset.setEmployee(emp);
					assignasset.setAsset(ast);
				
					assignasset.setAssign_date(ddate.format(LocalDateTime.now()));
					assignasset.setAssign_time(dtime.format(LocalDateTime.now()));
					
					isassigned = assignassetrepo.save(assignasset);
					
					if(isassigned!=null) {	
						qty = assetrepo.getQuantiyByAssetId(astid);
						qty-=1;
						assetrepo.updateAssetQuantityByAssetId(astid, ""+qty);
						
						AssetAssignHistory ahist = new AssetAssignHistory();
					
						ahist.setAsset(ast);
						ahist.setEmployee(emp);
						ahist.setOperation_date(tday);
						ahist.setOperation_time(ttime);
						ahist.setOperation("Asset Assigned");
						
						assetassignhistrepo.save(ahist);
						
						Activity act = new Activity();
						act.setActivity("Asset "+getasset.getAsset_name()+" assigned to "+ emp.getEmp_name());
						act.setOperation_date(ddate.format(LocalDateTime.now()));
						act.setOperation_time(dtime.format(LocalDateTime.now()));
						actrepo.save(act);
					}
				}
			}
		}
		
		//If Assets to be assigned are greater than the Already assigned assets
		if(nw_assets.length>ol_assets.length)
		{
			List<String> olist= Arrays.asList(ol_assets);
			List<String> nlist= Stream.of(new_assets.split(",")).collect(Collectors.toList());
			
			for(int i=0;i<nw_assets.length;i++)
			{
				if(olist.contains(nw_assets[i]))
				{
					continue;
				}
				else
				{
					AssignedAssets assignasset = new AssignedAssets();
					Long asid = Long.valueOf(nw_assets[i]);
					int qty =0;
					
					Long astid = Long.valueOf(asid);
					
					Assets ast = new Assets();
					
					assetrepo.findById(astid);
					Assets getasset = assetrepo.findById(astid).get();
					
					AssetType atype = new AssetType();
					
					atype = atyperepo.findById(getasset.getAtype().getType_id()).get();
					
					ast.setAtype(atype);
					
					ast.setAsset_id(astid);
					ast.setAsset_name(getasset.getAsset_name());
					ast.setAsset_number(getasset.getAsset_number());
					ast.setModel_number(getasset.getModel_number());
					ast.setQuantity(getasset.getQuantity());
					
					assignasset.setEmployee(emp);
					assignasset.setAsset(ast);
				
					assignasset.setAssign_date(ddate.format(LocalDateTime.now()));
					assignasset.setAssign_time(dtime.format(LocalDateTime.now()));
					
					isassigned = assignassetrepo.save(assignasset);
					
					if(isassigned!=null)
					{	
						qty = assetrepo.getQuantiyByAssetId(astid);

						qty-=1;
						assetrepo.updateAssetQuantityByAssetId(astid, ""+qty);
						
						AssetAssignHistory ahist = new AssetAssignHistory();
					
						ahist.setAsset(ast);
						ahist.setEmployee(emp);
						ahist.setOperation_date(tday);
						ahist.setOperation_time(ttime);
						ahist.setOperation("Asset Assigned");
						
						assetassignhistrepo.save(ahist);
						Activity act = new Activity();
						act.setActivity("Asset "+getasset.getAsset_name()+" Assigned to "+ emp.getEmp_name());
						act.setOperation_date(ddate.format(LocalDateTime.now()));
						act.setOperation_time(dtime.format(LocalDateTime.now()));
						actrepo.save(act);
						
					}
				}
			}
		}
		
		//If Assets to be assigned are smaller than the Already assigned assets
		if(nw_assets.length<ol_assets.length)
		{
//			List<String> olist= List.of(ol_assets);
//          List<String> nlist= List.of(nw_assets);
            
			List<String> olist= Arrays.asList(ol_assets);
			List<String> nlist= Stream.of(new_assets.split(",")).collect(Collectors.toList());
			for(int i=0;i<ol_assets.length;i++)
			{
				if(nlist.contains(ol_assets[i]))
				{continue;
				}
				else
				{
					Long asid = Long.valueOf(ol_assets[i]);
					int output = assignassetrepo.deleteAssignedAssetByEmpidAssetId(asid, emp.getEmp_id());
					if(output>0)
					{	
						int qty = assetrepo.getQuantiyByAssetId(asid);
						qty+=1;
						assetrepo.updateAssetQuantityByAssetId(asid, ""+qty);
						AssetAssignHistory ahist = new AssetAssignHistory();
						Assets ast = new Assets();
						
						Assets getasset = assetrepo.findById(asid).get();
						
						AssetType atype = new AssetType();
						
						atype = atyperepo.findById(getasset.getAtype().getType_id()).get();
						
						ast.setAtype(atype);
						
						ast.setAsset_id(asid);
						ast.setAsset_name(getasset.getAsset_name());
						ast.setAsset_number(getasset.getAsset_number());
						ast.setModel_number(getasset.getModel_number());
						ast.setQuantity(getasset.getQuantity());
						
						ahist.setAsset(ast);
						ahist.setEmployee(emp);
						ahist.setOperation_date(ddate.format(LocalDateTime.now()));
						ahist.setOperation_time(dtime.format(LocalDateTime.now()));
						ahist.setOperation("Asset Retrieved");
						
						assetassignhistrepo.save(ahist);
						Activity act = new Activity();
						act.setActivity("Asset "+getasset.getAsset_name()+" retrieved from "+ emp.getEmp_name());
						act.setOperation_date(ddate.format(LocalDateTime.now()));
						act.setOperation_time(dtime.format(LocalDateTime.now()));
						actrepo.save(act);
					}
				}
			}

		}
		if(isassigned!=null)
		{
			return 1;
		}
		else {
			return 0;
		}
	}
}
