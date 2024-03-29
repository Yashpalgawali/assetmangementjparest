package com.example.demo.service;

import java.util.List;

import com.example.demo.models.AssignedAssets;

public interface AssignedAssetService {

	public AssignedAssets saveAssignedAssets(AssignedAssets assign);
	
	public List<AssignedAssets> getAllAssignedAssets();

	public List<AssignedAssets> getAssignedAssetsByEmpId(Long empid);
	
	public List<AssignedAssets> getOnlyAssignedAssetsByEmpId(Long empid);

	public int retrieveAssetByEmpId(Long empid);
	
	public List<Object[]> getAllAssignedassetsGroup();
	
}
