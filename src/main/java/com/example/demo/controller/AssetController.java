package com.example.demo.controller;

import java.util.List;
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

import com.example.demo.dto.ResponseDto;
import com.example.demo.models.Assets;
import com.example.demo.service.AssetService;

//import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("asset")
public class AssetController {

	private final AssetService assetserv;
	
	public AssetController(  AssetService assetserv) {
		super();
		this.assetserv = assetserv;
	}
 
	@PostMapping("/")
//	@ApiOperation("This Will save the Asset")
	public ResponseEntity<ResponseDto> saveAssets(@RequestBody Assets asset)
	{
		Assets ast = assetserv.saveAssets(asset);
		if(ast != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.toString() , "Asset "+ast.getAsset_name()+" is saved successfully"));
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.toString() , "Asset "+asset.getAsset_name()+" is not saved "));
		}
	}
	
	@GetMapping("/")
//	@ApiOperation("This Will get the Asset list")
	public ResponseEntity<List<Assets>> viewAssets()
	{
		List<Assets> asset = assetserv.getAllAssets();
		if(asset.size()>0) {
			return new ResponseEntity<List<Assets>>(asset,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Assets>>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/{id}")
//	@ApiOperation("This Will get the Asset by ID ")
	public ResponseEntity<Assets> editAssetByIs(@PathVariable Long id) {
		Assets asset = assetserv.getAssetsById(id);
		return new ResponseEntity<Assets>(asset , HttpStatus.OK);
	}
	 
	@PutMapping("/")
//	@ApiOperation("This Will update the Asset")
	public ResponseEntity<ResponseDto> updateAsset(@RequestBody Assets ast) {
		int res = assetserv.updateAssets(ast);
		if(res > 0) {
			return  ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("", "Asset "+ast.getAsset_name()+" is updated successfully"));
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(new ResponseDto(HttpStatus.NOT_MODIFIED.toString(), "Asset "+ast.getAsset_name()+" is not updated"));
		}
	}
}
