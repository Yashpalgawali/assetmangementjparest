package com.example.demo.service;

import java.util.List;

import com.example.demo.exceptions.NoContentException;
import com.example.demo.models.AssetAssignHistory;

public interface AssetAssignHistService {

	public AssetAssignHistory saveAssetAssignHistory(AssetAssignHistory ahist);
	
	public List<AssetAssignHistory> getAssetAssignHistoryByEmpId(Long empid) throws NoContentException;
	
}
