package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDto;
import com.example.demo.models.RetrievedAssets;
import com.example.demo.service.IRetrievedAssetService;

@RestController
@RequestMapping("retrievedassets")
public class RetrievedAssetsController {

	private final IRetrievedAssetService assetserv;

	public RetrievedAssetsController(IRetrievedAssetService assetserv) {
		super();
		this.assetserv = assetserv;
	}

	public ResponseEntity<ResponseDto> saveRetrievedAssets(@RequestBody RetrievedAssets assets) {

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDto(HttpStatus.OK.toString(), "Assets Are retrieved Successfully"));
	}
}
