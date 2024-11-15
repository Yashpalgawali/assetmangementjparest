package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.models.AssetAssignHistory;
import com.example.demo.repository.AssetAssignHistoryRepo;

@Service("ahistserv")
public class AssetAssignHistServImpl implements AssetAssignHistService {

	private AssetAssignHistoryRepo assetassignhistrepo;
	
	public AssetAssignHistServImpl(AssetAssignHistoryRepo assetassignhistrepo) {
		super();
		this.assetassignhistrepo = assetassignhistrepo;
	}

	@Override
	public AssetAssignHistory saveAssetAssignHistory(AssetAssignHistory ahist) {
		return assetassignhistrepo.save(ahist);
	}

	@Override
	public List<AssetAssignHistory> getAssetAssignHistoryByEmpId(String empid) {
		return assetassignhistrepo.getAssetAssginHistByEmpId(Long.valueOf(empid));
	}

}
