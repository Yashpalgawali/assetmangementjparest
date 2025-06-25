package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDto;
import com.example.demo.exceptions.NoContentException;
import com.example.demo.exceptions.ResourceNotModifiedException;
import com.example.demo.models.AssetType;
import com.example.demo.service.AssetTypeService;

//import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("assettype")
public class AssetTypeController {

	private final AssetTypeService atypeserv;
	
	public AssetTypeController(AssetTypeService atypeserv) {
		super();
		this.atypeserv = atypeserv;
	}

	@PostMapping("/")
//	@ApiOperation("This will save the Asset Type")
	public ResponseEntity<ResponseDto> saveAssetType(@RequestBody AssetType atype)
	{
		AssetType type = atypeserv.saveAssetType(atype);		 
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.toString(), "Asset type "+atype.getType_name()+" is saved successfully "));		 
		 
	}
	
	@GetMapping("/")
//	@ApiOperation("This will get the Asset Types list")
	public ResponseEntity<List<AssetType>> getAllAssetType() throws NoContentException
	{
		List<AssetType> type = atypeserv.getAllAssetTypes();
		return new ResponseEntity<List<AssetType>>(type , HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
//	@ApiOperation("This will get the Asset Type by ID")
	public ResponseEntity<AssetType> getAssetTypeById(@PathVariable Long id)
	{
		AssetType atype = atypeserv.getAssetTypeById(id);
		return new ResponseEntity<AssetType>(atype , HttpStatus.OK);
	}
	
	@PutMapping("/")
//	@ApiOperation("This will Update the Asset Type")
	public ResponseEntity<ResponseDto> updateAssetType(@RequestBody AssetType atype)
	{
		int result = atypeserv.updateAssetType(atype);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.toString(), "Asset type "+atype.getType_name()+" is updated successfully "));
		
	}

}
