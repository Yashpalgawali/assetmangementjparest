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
import com.example.demo.models.Assets;
import com.example.demo.service.AssetService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("asset")
@CrossOrigin("*")
public class AssetController {

	private final AssetService assetserv;
	
	public AssetController(  AssetService assetserv) {
		super();
		this.assetserv = assetserv;
	}

	@PostMapping("/")
	@ApiOperation("This Will save the Asset")
	public ResponseEntity<List<Assets>> saveAssets(@RequestBody Assets asset)
	{
		Assets ast = assetserv.saveAssets(asset);
		if(ast!=null){
			return new ResponseEntity<List<Assets>>(assetserv.getAllAssets(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Assets>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/")
	@ApiOperation("This Will get the Asset list")
	public ResponseEntity<List<Assets>> viewAssets()
	{
		List<Assets> asset = assetserv.getAllAssets();
		if(asset.size()>0) {
			return new ResponseEntity<List<Assets>>(asset,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Assets>>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}")
	@ApiOperation("This Will get the Asset by ID ")
	public ResponseEntity<Assets> editAssetByIs(@PathVariable("id") String id) {
		Assets asset = assetserv.getAssetsById(id);
		if(asset!=null){
			return new ResponseEntity<Assets>(asset , HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	 
	@PutMapping("/")
	@ApiOperation("This Will update the Asset")
	public ResponseEntity<List<Assets>> updateAsset(@RequestBody Assets ast) {
		int res = assetserv.updateAssets(ast);
		if(res > 0) {
			return new ResponseEntity<List<Assets>>(assetserv.getAllAssets(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Assets>>( HttpStatus.NOT_MODIFIED);
		}
	}
}
