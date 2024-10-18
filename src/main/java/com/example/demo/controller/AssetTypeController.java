package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.models.AssetType;
import com.example.demo.service.AssetTypeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("assettype")
@CrossOrigin("*")
public class AssetTypeController {

	AssetTypeService atypeserv;
	
	public AssetTypeController(AssetTypeService atypeserv) {
		super();
		this.atypeserv = atypeserv;
	}

	@PostMapping("/")
	@ApiOperation("This will save the Asset Type")
	public ResponseEntity<List<AssetType>> saveAssetType(@RequestBody AssetType atype)
	{
		AssetType type = atypeserv.saveAssetType(atype);
		if(type!=null) {
			return new ResponseEntity<List<AssetType>>(atypeserv.getAllAssetTypes(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/")
	@ApiOperation("This will get the Asset Types list")
	public ResponseEntity<List<AssetType>> getAllAssetType()
	{
		List<AssetType> type = atypeserv.getAllAssetTypes();
		if(type.size()>0) {
			return new ResponseEntity<List<AssetType>>(type , HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<AssetType>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/{id}")
	@ApiOperation("This will get the Asset Type by ID")
	public ResponseEntity<AssetType> editAssetTypeById(@PathVariable("id") String id)
	{
		AssetType atype = atypeserv.getAssetTypeById(id);
		if(atype!=null) {
			return new ResponseEntity<AssetType>(atype , HttpStatus.OK);
		}
		else {
			return new ResponseEntity<AssetType>( HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/")
	@ApiOperation("This will Update the Asset Type")
	public ResponseEntity<List<AssetType>> updateAssetType(@RequestBody AssetType atype)
	{
		int res = atypeserv.updateAssetType(atype);
		if(res>0) {
			return new ResponseEntity<List<AssetType>>(atypeserv.getAllAssetTypes(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>( HttpStatus.NOT_FOUND);
		}
	}

}
