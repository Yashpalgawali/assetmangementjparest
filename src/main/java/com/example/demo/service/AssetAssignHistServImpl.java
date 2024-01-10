package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.AssetAssignHistory;
import com.example.demo.repository.AssetAssignHistoryRepo;

@Service("ahistserv")
public class AssetAssignHistServImpl implements AssetAssignHistService {

	@Autowired
	AssetAssignHistoryRepo assetassignhistrepo;
	@Override
	public AssetAssignHistory saveAssetAssignHistory(AssetAssignHistory ahist) {
		return assetassignhistrepo.save(ahist);
	}

	@Override
	public List<AssetAssignHistory> getAssetAssignHistoryByEmpId(String empid) {
		return assetassignhistrepo.getAssetAssginHistByEmpId(Long.valueOf(empid));
	}

}
