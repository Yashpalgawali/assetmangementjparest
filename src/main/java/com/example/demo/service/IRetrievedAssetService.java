package com.example.demo.service;

import java.util.List;

import com.example.demo.models.RetrievedAssets;

public interface IRetrievedAssetService {
	
	public RetrievedAssets saveRetrievedAssets(RetrievedAssets asset);
	
	public List<RetrievedAssets> getRetrievedAssetsByEmpId(Long empid);
	
	public List<RetrievedAssets> getAllRetrievedAssetsList();
	
}
