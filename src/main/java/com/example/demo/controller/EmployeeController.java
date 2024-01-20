package com.example.demo.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.exporttoexcel.ExportAssetAssignHistory;
import com.example.demo.exporttoexcel.ExportAssignedAssets;
import com.example.demo.models.AssetAssignHistory;
import com.example.demo.models.AssetType;
import com.example.demo.models.Assets;
import com.example.demo.models.AssignedAssets;
import com.example.demo.models.Company;
import com.example.demo.models.Department;
import com.example.demo.models.Designation;
import com.example.demo.models.Employee;
import com.example.demo.service.AssetAssignHistService;
import com.example.demo.service.AssetService;
import com.example.demo.service.AssetTypeService;
import com.example.demo.service.AssignedAssetService;
import com.example.demo.service.CompanyService;
import com.example.demo.service.DesignationService;
import com.example.demo.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("employee")
@Slf4j
public class EmployeeController {

	@Autowired
	EmployeeService empserv;
	
	@Autowired
	CompanyService compserv;
	
	@Autowired
	DesignationService desigserv;
	
	@Autowired
	AssetService assetserv;
	
	@Autowired
	AssignedAssetService assignserv;
	
	@Autowired
	AssetAssignHistService ahistserv;
	
	@Autowired
	AssetTypeService atypeserv;
	
	@Autowired
	Environment env;
	
//	private LocalDateTime today;
	
	private DateTimeFormatter ddate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	private DateTimeFormatter dtime = DateTimeFormatter.ofPattern("HH:mm:ss");
	
//	private String tday=ddate.format(LocalDateTime.now()),ttime=dtime.format(LocalDateTime.now());
	

	@PostMapping("/")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee empl ) {
		log.info("inside save employee \n");
	System.out.println("inside save employee method \n Asset Ids = "+empl.getAsset_ids());
		String asset_ids = empl.getAsset_ids().toString().replace("[", "").replace("]", "").replace(" ", "");
		
		AssignedAssets isassigned = null;
		
		Employee emp = empserv.saveEmployee(empl);
		if(emp!=null) {
			System.out.println("Employee is saved successfully\n");
			String[] asset_arr = asset_ids.split(",");
							
			for(int i=0;i<asset_arr.length;i++){
				
				System.err.println("Asset ID is = "+asset_arr[i]+"\n");
				AssignedAssets assignasset = new AssignedAssets();
				int qty =0;
				Long astid = Long.valueOf(asset_arr[i]);
				Assets ast = new Assets();
				Assets getasset = assetserv.getAssetsById(""+astid);
				
				System.out.println("Asset is = "+getasset.toString());
				
				AssetType atype = new AssetType();
				atype = atypeserv.getAssetTypeById(""+getasset.getAtype().getType_id());
				
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
				
				isassigned = assignserv.saveAssignedAssets(assignasset);
				
				if(isassigned!=null) {	
					qty = (Integer)assetserv.getAssetQuantityByAssetId(astid);
					qty-=1;
					assetserv.updateAssetQuantityByAssetId(astid, ""+qty);
					AssetAssignHistory ahist = new AssetAssignHistory();
				
					ahist.setAsset(ast);
					ahist.setEmployee(emp);
					ahist.setOperation_date(ddate.format(LocalDateTime.now()));
					ahist.setOperation_time(dtime.format(LocalDateTime.now()));
					ahist.setOperation("Asset Assigned");
					
					ahistserv.saveAssetAssignHistory(ahist);
				}
			}
			System.err.println("emp saved successfully");
			return new ResponseEntity<Employee>(emp, HttpStatus.OK);
		}
		else {
			System.err.println("emp is not saved successfully");
			return new ResponseEntity<Employee>(HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
	

	@GetMapping("/viewassignedassets")
	public ResponseEntity<List<AssignedAssets>> viewAllAssignedAssets()
	{log.info("inside viewassignedassets method \n");
		System.err.println("Inside viewassignedassets method \n");
		List<AssignedAssets> alist = new ArrayList<AssignedAssets>();
		List<Object[]>  aslist = assignserv.getAllAssignedassetsGroup();
		
		if(aslist.size() >0){
			aslist.forEach(ast->{
					AssignedAssets asts = new AssignedAssets();
					String assets= "",asset_type="";
					asts.setAssigned_asset_id(Long.valueOf(ast[0].toString()));
					asts.setAssign_date(ast[1].toString());
					asts.setAssign_time(ast[2].toString());
					asts.setAsset_id(Long.valueOf(ast[3].toString()));
					asts.setEmp_id((Long.valueOf(ast[4].toString())));
					assets = Stream.of(ast[5].toString().split(",")).collect(Collectors.toList()).toString();
					assets = assets.replace("[", "").replace("]", ""); 
					asts.setAssigned(assets);
					asset_type = Stream.of(ast[6].toString().split(",")).collect(Collectors.toList()).toString();
					asset_type = asset_type.replace("[", "").replace("]", "");
					asts.setAssigned_types(asset_type);
					
					Employee emp = new Employee();
					
					emp.setEmp_name(ast[7].toString());
					emp.setEmp_email(ast[8].toString());
					emp.setEmp_contact(ast[9].toString());
					
					Designation desig = new Designation();
					desig.setDesig_id((Long.valueOf(ast[10].toString())));
					desig.setDesig_name(ast[11].toString());

					Department dept = new Department();
					dept.setDept_id((Long.valueOf(ast[12].toString())));
					dept.setDept_name(ast[13].toString());
					
					Company comp = new Company();
					comp.setComp_id((Long.valueOf(ast[14].toString())));
					comp.setComp_name(ast[15].toString());
					
					String mod_num = "";
					
					mod_num = Stream.of(ast[16].toString().split(",")).collect(Collectors.toList()).toString().replace("[", "").replace("]", "");
					
					asts.setModel_numbers(mod_num);
					
					dept.setCompany(comp);
					
					emp.setDepartment(dept);
					emp.setDesignation(desig);
					
					asts.setEmployee(emp);
				
					alist.add(asts);
			});
			return new ResponseEntity<List<AssignedAssets>>(alist ,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<AssignedAssets>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Employee>> viewAllEmployees() {
		List<Employee> elist = empserv.getAllEmployees();
		return new ResponseEntity<List<Employee>>(elist, HttpStatus.OK);
	}
	
	@GetMapping("/retrieveassetsbyempid/{id}")
	public ResponseEntity<List<AssignedAssets>> retrieveAssets(@PathVariable("id") Long id)
	{
		List<AssignedAssets> assign = assignserv.getAssignedAssetsByEmpId(id);
		assign.stream().forEach(e->System.err.println(e));
		if(assign.size()>0) {
			return new ResponseEntity<List<AssignedAssets>>(assign,HttpStatus.OK);
		}
		else{
			return new ResponseEntity<List<AssignedAssets>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getassignedassetsbyempid/{id}")
	public ResponseEntity<List<AssignedAssets>> getAssignedAssetsByEmpId(@PathVariable("id") Long id)
	{
		System.out.println("inside getAssignedAssetsByEmpId() \n The emp Id is = "+id);
		
		List<AssignedAssets> assign = assignserv.getOnlyAssignedAssetsByEmpId(id);
		assign.stream().forEach(e->System.err.println(e.toString()));
	
		if(assign.size()>0) {
			return new ResponseEntity<List<AssignedAssets>>(assign,HttpStatus.OK);
		}
		else{
			return new ResponseEntity<List<AssignedAssets>>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/delete")
	public String updateRetrieveAssets(@RequestBody AssignedAssets assign)
	{
		System.err.println("Delete mapping called \n"+assign.getAssigned_assets());
		return "Delete mapping called";
//		int val = assignserv.retrieveAssetByEmpId(assign);
//		if(val>0) {
//			attr.addFlashAttribute("response", "Assets are assigned successfully");
//			return "redirect:/viewassignedassets";
//		}
//		else{
//			attr.addFlashAttribute("reserr", "Assets are not assigned successfully");
//			return "redirect:/viewassignedassets";
//		}
	}
	 
	
//	@PostMapping("/updateretrieveassets")
//	public String updateRetrieveAssets(@ModelAttribute("AssignedAssets")AssignedAssets assign,RedirectAttributes attr)
//	{
//		int val = assignserv.retrieveAssetByEmpId(assign);
//		if(val>0) {
//			attr.addFlashAttribute("response", "Assets are assigned successfully");
//			return "redirect:/viewassignedassets";
//		}
//		else{
//			attr.addFlashAttribute("reserr", "Assets are not assigned successfully");
//			return "redirect:/viewassignedassets";
//		}
//	}
	 
	@GetMapping("/viewemphistbyempid/{id}")
	public ResponseEntity<List<AssetAssignHistory>> viewEmployeeHistoryByEmpId(@PathVariable("id") String id)
	{
		List<AssetAssignHistory> ahist = ahistserv.getAssetAssignHistoryByEmpId(id);
		if(ahist.size()>0){	
			return new ResponseEntity<List<AssetAssignHistory>>(ahist,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<AssetAssignHistory>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/editempassignassetbyempid/{id}")
	public ResponseEntity<Employee> editEmployeeByEmpId(@PathVariable("id") String empid) {
		Employee emp = empserv.getEmployeeById(empid);
		if(emp!=null) {
			List<AssignedAssets> aslist =  assignserv.getAssignedAssetsByEmpId(Long.valueOf(empid));
			String assigned_assets = "",assigned_asset_type="";
			Long[] strArray = new Long[aslist.size()];
			for(int i=0;i<aslist.size();i++) {
				if(i==0) {
					assigned_assets = assigned_assets+aslist.get(i).getAsset().getAsset_name()+"("+aslist.get(i).getAsset().getModel_number()+")";
					assigned_asset_type =assigned_asset_type+aslist.get(i).getAsset().getAtype().getType_name();
					strArray[i] = aslist.get(i).getAsset().getAsset_id();
				}
				else {
					assigned_assets = assigned_assets+","+aslist.get(i).getAsset().getAsset_name()+"("+aslist.get(i).getAsset().getModel_number()+")";
					assigned_asset_type =assigned_asset_type+","+aslist.get(i).getAsset().getAtype().getType_name();
					strArray[i] =aslist.get(i).getAsset().getAsset_id(); 
				}
			}
			emp.setAssigned_assets(assigned_assets);
			emp.setAssigned_asset_types(assigned_asset_type); 
			return new ResponseEntity<Employee>(emp , HttpStatus.OK) ;
		}
		else {
			return new ResponseEntity<Employee>( HttpStatus.NOT_FOUND) ;
		}
	}
	
	@PutMapping("/")
	public  ResponseEntity<Employee> updateAssignedAssets(@RequestBody Employee emp)
	{
		int res = empserv.updateEmployee(emp);
		if(res>0) {
			return new  ResponseEntity<Employee>(emp,HttpStatus.OK);
		}
		else {
			return new  ResponseEntity<Employee>(HttpStatus.NOT_MODIFIED);
		}
	}
	
	@GetMapping("/exportassignedassets/excel")
    public ResponseEntity<InputStreamResource> exportToExcel(HttpServletResponse response) throws IOException {
		// Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=All_Assigned_Assets.xlsx");
        
        List<AssignedAssets> alist = new ArrayList<AssignedAssets>();
		List<Object[]>  aslist = assignserv.getAllAssignedassetsGroup();
		
		if(aslist.size() >0){
			aslist.forEach(ast->{
					AssignedAssets asts = new AssignedAssets();
					
					String assets= "",asset_type="";
					
					asts.setAssigned_asset_id(Long.valueOf(ast[0].toString()));
					asts.setAssign_date(ast[1].toString());
					asts.setAssign_time(ast[2].toString());
					asts.setAsset_id(Long.valueOf(ast[3].toString()));
					asts.setEmp_id((Long.valueOf(ast[4].toString())));
					
					assets = Stream.of(ast[5].toString().split(",")).collect(Collectors.toList()).toString();
					
					assets = assets.replace("[", "").replace("]", "");
					
					asts.setAssigned(assets);
					
					asset_type = Stream.of(ast[6].toString().split(",")).collect(Collectors.toList()).toString();
					asset_type = asset_type.replace("[", "").replace("]", "");
					
					asts.setAssigned_types(asset_type);
					
					Employee emp = new Employee();
					
					emp.setEmp_name(ast[7].toString());
					emp.setEmp_email(ast[8].toString());
					emp.setEmp_contact(ast[9].toString());
					
					Designation desig = new Designation();
					desig.setDesig_id((Long.valueOf(ast[10].toString())));
					desig.setDesig_name(ast[11].toString());

					Department dept = new Department();
					dept.setDept_id((Long.valueOf(ast[12].toString())));
					dept.setDept_name(ast[13].toString());
					
					Company comp = new Company();
					comp.setComp_id(Long.valueOf(ast[14].toString()));
					comp.setComp_name(ast[15].toString());
					
					String mod_num = "";
					
					mod_num = Stream.of(ast[16].toString().split(",")).collect(Collectors.toList()).toString().replace("[", "").replace("]", "");
					mod_num = mod_num.replace("[", "").replace("]", "");
					
					asts.setModel_numbers(mod_num);
					dept.setCompany(comp);
					
					emp.setDepartment(dept);
					emp.setDesignation(desig);
					asts.setEmployee(emp);
				
					alist.add(asts);
			});
		}
		
        ExportAssignedAssets excelExporter = new ExportAssignedAssets(alist);
        byte[] excelContent = excelExporter.export(response);
        
        // Return the file as a ResponseEntity
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(new ByteArrayInputStream(excelContent)));

	}
	
//		@GetMapping("/exportassignedassets/excel")
//	    public void exportToExcel(HttpServletResponse response) throws IOException {
//			System.err.println("exporto to excel");
		
//	        response.setContentType("application/octet-stream");
//	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//	        String currentDateTime = dateFormatter.format(new Date());
//	         
//	        String headerKey = "Content-Disposition";
//	        String headerValue = "attachment; filename=Assigned_Assets_" + currentDateTime + ".xls";
//	        response.setHeader(headerKey, headerValue);
//	         
//	        List<AssignedAssets> alist = new ArrayList<AssignedAssets>();
//			List<Object[]>  aslist = assignserv.getAllAssignedassetsGroup();
//			
//			if(aslist.size() >0){
//				aslist.forEach(ast->{
//						AssignedAssets asts = new AssignedAssets();
//						
//						String assets= "",asset_type="";
//						
//						asts.setAssigned_asset_id(Long.valueOf(ast[0].toString()));
//						asts.setAssign_date(ast[1].toString());
//						asts.setAssign_time(ast[2].toString());
//						asts.setAsset_id(Long.valueOf(ast[3].toString()));
//						asts.setEmp_id((Long.valueOf(ast[4].toString())));
//						
//						assets = Stream.of(ast[5].toString().split(",")).collect(Collectors.toList()).toString();
//						
//						assets = assets.replace("[", "").replace("]", "");
//						
//						asts.setAssigned(assets);
//						
//						asset_type = Stream.of(ast[6].toString().split(",")).collect(Collectors.toList()).toString();
//						asset_type = asset_type.replace("[", "").replace("]", "");
//						
//						asts.setAssigned_types(asset_type);
//						
//						Employee emp = new Employee();
//						
//						emp.setEmp_name(ast[7].toString());
//						emp.setEmp_email(ast[8].toString());
//						emp.setEmp_contact(ast[9].toString());
//						
//						Designation desig = new Designation();
//						desig.setDesig_id((Long.valueOf(ast[10].toString())));
//						desig.setDesig_name(ast[11].toString());
//
//						Department dept = new Department();
//						dept.setDept_id((Long.valueOf(ast[12].toString())));
//						dept.setDept_name(ast[13].toString());
//						
//						Company comp = new Company();
//						comp.setComp_id(Long.valueOf(ast[14].toString()));
//						comp.setComp_name(ast[15].toString());
//						
//						String mod_num = "";
//						
//						mod_num = Stream.of(ast[16].toString().split(",")).collect(Collectors.toList()).toString().replace("[", "").replace("]", "");
//						mod_num = mod_num.replace("[", "").replace("]", "");
//						
//						asts.setModel_numbers(mod_num);
//						dept.setCompany(comp);
//						
//						emp.setDepartment(dept);
//						emp.setDesignation(desig);
//						asts.setEmployee(emp);
//					
//						alist.add(asts);
//				});
//			}
//			
//	        ExportAssignedAssets excelExporter = new ExportAssignedAssets(alist);
//	        excelExporter.export(response);
//	    } 
		
		@RequestMapping("/exportassignshistory/excel/{id}")
	    public ResponseEntity<InputStreamResource> exportToExcel(HttpServletResponse response,@PathVariable("id")Long empid ) throws IOException {
		
			System.err.println("inside exportassignshistory excel history \n ID = "+empid);
			// Set headers
	        HttpHeaders headers = new HttpHeaders();
	        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Asset_Assigned_History_Employee.xlsx");
	        
//	        response.setContentType("application/octet-stream");
//	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//	        String currentDateTime = dateFormatter.format(new Date());
//	        String headerKey   = "Content-Disposition";
//	        String headerValue = "attachment; filename=Assigned_Assets_History" + currentDateTime + ".xls";
//	        response.setHeader(headerKey, headerValue);
	        List<AssetAssignHistory> alist = ahistserv.getAssetAssignHistoryByEmpId(""+empid);
	        
	        ExportAssetAssignHistory ahist = new ExportAssetAssignHistory(alist);
	        ahist.export(response);
	        
	        byte[] excelContent = ahist.export(response);
	        
	        // Return the file as a ResponseEntity
	        return ResponseEntity
	                .ok()
	                .headers(headers)
	                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
	                .body(new InputStreamResource(new ByteArrayInputStream(excelContent)));
		}
}
