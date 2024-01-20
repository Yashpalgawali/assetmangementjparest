package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.AssetType;
import com.example.demo.models.Assets;
import com.example.demo.service.AssetService;
import com.example.demo.service.AssetTypeService;

@RestController
@RequestMapping("asset")
@CrossOrigin("*")
public class AssetController {

	@Autowired
	AssetTypeService atypeserv;

	@Autowired
	AssetService assetserv;
	
	@PostMapping("/")
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
	public ResponseEntity<List<Assets>> viewAssets()
	{
		List<Assets> asset = assetserv.getAllAssets();
		asset.stream().forEach(e->System.err.println(e.toString()));
		if(asset.size()>0) {
			return new ResponseEntity<List<Assets>>(asset,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Assets>>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}")
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
