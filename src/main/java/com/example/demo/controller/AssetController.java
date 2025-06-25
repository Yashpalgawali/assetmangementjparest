package com.example.demo.controller;

import java.time.LocalDateTime;
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

import com.example.demo.dto.ErrorResponseDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.models.Assets;
import com.example.demo.service.AssetService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

//import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("asset")
@Tag(name = "Asset",description = "Assets Management API")
public class AssetController {

	private final AssetService assetserv;
	
	public AssetController(  AssetService assetserv) {
		super();
		this.assetserv = assetserv;
	}
 
	@PostMapping("/")
	@Operation(description = "This Will save the Asset" ,summary = "Save Asset")
	@ApiResponses(
				value = {
						@ApiResponse(description = "Asset is saved Successfully",responseCode = "200"),
						@ApiResponse(description = "Asset is not saved ",responseCode = "500")
				}
			)
	public ResponseEntity<ResponseDto> saveAssets(@RequestBody Assets asset)
	{
		Assets ast = assetserv.saveAssets(asset);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.toString() , "Asset "+ast.getAsset_name()+" is saved successfully"));
	}
	
	@GetMapping("/")
	@Operation(description = "This Will get the Asset list" ,summary = "Get List of Assets")
	@ApiResponses(
				value = {
						@ApiResponse(description = "Asset List is fetched Successfully", responseCode = "200"),
						@ApiResponse(description = "No Assets are found ", responseCode = "404")
				}
			)
	public ResponseEntity<List<Assets>> viewAssets()
	{
		List<Assets> asset = assetserv.getAllAssets();
		if(asset.size()>0) {
			return ResponseEntity.status(HttpStatus.OK).body(asset);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{id}")
	@Operation(description = "This Will get the Asset by ID" ,summary = "Retrieve Asset details")
	@ApiResponses(
			value = {
					@ApiResponse(description = "Asset is fetched Successfully", responseCode = "200"),
					@ApiResponse(description = "Asset is not fetched ", responseCode = "404")
			}
		)
	public ResponseEntity<Assets> getAssetById(@PathVariable Long id) {
		Assets asset = assetserv.getAssetsById(id);
		return new ResponseEntity<Assets>(asset , HttpStatus.OK);
	}
	 
	@PutMapping("/")
	@Operation(description = "This Will update the Asset Details" ,summary = "Update Asset details")
	@ApiResponses(
			value = {
					@ApiResponse(description = "Asset is Updated successfully", responseCode = "200"),
					@ApiResponse(description = "Asset is not Updated ", responseCode = "304")
			}
		)
	public ResponseEntity<?> updateAsset(@RequestBody Assets ast) {
		int res = assetserv.updateAssets(ast);
		 
		return  ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.toString(), "Asset "+ast.getAsset_name()+" is updated successfully"));
	}
}
