package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.AssetType;
import com.example.demo.service.AssetTypeService;

@RestController
@RequestMapping("assettype")
@CrossOrigin("*")
public class AssetTypeController {

	@Autowired
	AssetTypeService atypeserv;
	
	
//	@GetMapping("/addassettype")
//	public String addAssetType()
//	{
//		return "AddAssetType";
//	}
	
	@PostMapping("/")
	public ResponseEntity<List<AssetType>> saveAssetType(@RequestBody AssetType atype)
	{
		AssetType type = atypeserv.saveAssetType(atype);
		if(type!=null)
		{
			return new ResponseEntity<List<AssetType>>(atypeserv.getAllAssetTypes(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<AssetType>> getAllAssetType()
	{
		List<AssetType> type = atypeserv.getAllAssetTypes();
		if(type.size()>0)
		{
			return new ResponseEntity<List<AssetType>>(type , HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<AssetType>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AssetType> editAssetTypeById(@PathVariable("id") String id)
	{
		AssetType atype = atypeserv.getAssetTypeById(id);
		if(atype!=null)
		{
			return new ResponseEntity<AssetType>(atype , HttpStatus.OK);
		}
		else {
			return new ResponseEntity<AssetType>( HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/")
	public ResponseEntity<List<AssetType>> updateAssetType(@RequestBody AssetType atype)
	{
		int res = atypeserv.updateAssetType(atype);
		
		if(res>0) {
			return new ResponseEntity<List<AssetType>>(atypeserv.getAllAssetTypes(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>( HttpStatus.NOT_FOUND);
=======
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.AssetType;
import com.example.demo.service.AssetTypeService;

@RestController
@RequestMapping("assettype")
@CrossOrigin("*")
public class AssetTypeController {

	@Autowired
	AssetTypeService atypeserv;
	
	
//	@GetMapping("/addassettype")
//	public String addAssetType()
//	{
//		return "AddAssetType";
//	}
	
	@PostMapping("/")
	public ResponseEntity<List<AssetType>> saveAssetType(@RequestBody AssetType atype)
	{
		AssetType type = atypeserv.saveAssetType(atype);
		if(type!=null)
		{
			return new ResponseEntity<List<AssetType>>(atypeserv.getAllAssetTypes(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<AssetType>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<AssetType>> getAllAssetType()
	{
		List<AssetType> type = atypeserv.getAllAssetTypes();
		if(type.size()>0)
		{
			return new ResponseEntity<List<AssetType>>(type , HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<AssetType>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AssetType> editAssetTypeById(@PathVariable("id") String id)
	{
		AssetType atype = atypeserv.getAssetTypeById(id);
		if(atype!=null)
		{
			return new ResponseEntity<AssetType>(atype , HttpStatus.OK);
		}
		else {
			return new ResponseEntity<AssetType>( HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/updateassettype")
	public ResponseEntity<List<AssetType>> updateAssetType(@RequestBody AssetType atype)
	{
		int res = atypeserv.updateAssetType(atype);
		
		if(res>0) {
			return new ResponseEntity<List<AssetType>>(atypeserv.getAllAssetTypes(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<AssetType>>( HttpStatus.NOT_FOUND);
>>>>>>> branch 'master' of https://github.com/Yashpalgawali/assetmangementjparest.git
		}
	}
//	@RequestMapping("/saveassettype")
//	public String saveAssetType(@ModelAttribute("AssetType")AssetType atype,RedirectAttributes attr)
//	{
//		AssetType type = atypeserv.saveAssetType(atype);
//		if(type!=null)
//		{
//			attr.addFlashAttribute("response", "Asset Type is saved successfully");
//			return "redirect:/viewassettype";
//		}
//		else {
//			attr.addFlashAttribute("reserr", "Asset Type is not saved ");
//			return "redirect:/viewassettype";
//		}
//	}
	
//	@GetMapping("/viewassettype")
//	public String viewAssetTypes(Model model)
//	{
//		List<AssetType> atype = atypeserv.getAllAssetTypes();
//		
//		model.addAttribute("atype", atype);		
//		return "ViewAssetType";
//	}
	
	
//	@GetMapping("/editassettype/{id}")
//	public String editAssetTypeById(@PathVariable("id") String id, Model model ,RedirectAttributes attr)
//	{
//		AssetType atype = atypeserv.getAssetTypeById(id);
//		if(atype!=null)
//		{
//			model.addAttribute("types", atype);
//			return "EditAssetType";
//		}
//		else {
//			attr.addFlashAttribute("reserr", "Asset Type is not found for given ID");
//			return "redirect:/viewassettype";
//		}
//	}
	
//	@RequestMapping("/updateassettype")
//	public String updateAssetType(@ModelAttribute("AssetType")AssetType atype,RedirectAttributes attr)
//	{
//		int res = atypeserv.updateAssetType(atype);
//		
//		if(res>0) {
//			attr.addFlashAttribute("response", "Asset Type is Update successfully");
//			return "redirect:/viewassettype";
//		}
//		else {
//			attr.addFlashAttribute("reserr", "Asset Type is not updated ");
//			return "redirect:/viewassettype";
//		}
//	}
}
