package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.models.Employee;
import com.example.demo.models.RetrievedAssets;
import com.example.demo.repository.RetrievedAssetsRepository;
import com.example.demo.service.IRetrievedAssetService;

@Service("retrievedassetserv")
public class RetrievedAssetListImpl implements IRetrievedAssetService {

	private final RetrievedAssetsRepository assetrepo;

	public RetrievedAssetListImpl(RetrievedAssetsRepository assetrepo) {
		super();
		this.assetrepo = assetrepo;
	}

	@Override
	public RetrievedAssets saveRetrievedAssets(RetrievedAssets asset) {
		 
		return assetrepo.save(asset);
	}

	@Override
	public List<RetrievedAssets> getRetrievedAssetsByEmpId(Long empid) {
//		Employee emp = new Employee();
//		emp.setEmp_id(empid);
//		List<RetrievedAssets> assetList = assetrepo.findByEmployee(emp);
//		if(assetList.size() > 0) {
//			return assetList;
//		}
//		else {
//			throw new ResourceNotFoundException("No retrieved assets found for given employee Id "+empid);
//		}
		return null;
	}

	@Override
	public List<RetrievedAssets> getAllRetrievedAssetsList() {

		return assetrepo.findAll();
	}

}
