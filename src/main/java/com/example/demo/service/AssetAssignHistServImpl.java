package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exceptions.NoContentException;
import com.example.demo.models.AssetAssignHistory;
import com.example.demo.repository.AssetAssignHistoryRepo;

@Service("ahistserv")
public class AssetAssignHistServImpl implements AssetAssignHistService {

	private final AssetAssignHistoryRepo assetassignhistrepo;
	
	public AssetAssignHistServImpl(AssetAssignHistoryRepo assetassignhistrepo) {
		super();
		this.assetassignhistrepo = assetassignhistrepo;
	}

	@Override
	public AssetAssignHistory saveAssetAssignHistory(AssetAssignHistory ahist) {
		return assetassignhistrepo.save(ahist);
	}

	@Override
	public List<AssetAssignHistory> getAssetAssignHistoryByEmpId(Long empid) throws NoContentException {
		var list = assetassignhistrepo.getAssetAssginHistByEmpId(empid);
		if(list.size()>0) {
			return list;
		}
		else {
			throw new NoContentException("No asset assign history found for the employee ");		
		}
	}
}
