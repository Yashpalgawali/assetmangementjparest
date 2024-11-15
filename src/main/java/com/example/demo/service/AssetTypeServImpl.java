package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.AssetType;
import com.example.demo.repository.AssetTypeRepo;

@Service("atypeserv")
public class AssetTypeServImpl implements AssetTypeService {

	private	AssetTypeRepo atyperepo;
	
	public AssetTypeServImpl(AssetTypeRepo atyperepo) {
		super();
		this.atyperepo = atyperepo;
	}

	@Override
	public AssetType saveAssetType(AssetType atype) {
		return atyperepo.save(atype);
	}

	@Override
	public List<AssetType> getAllAssetTypes() {
		return atyperepo.findAll();
	}

	@Override
	public AssetType getAssetTypeById(String id) {
		AssetType atype = atyperepo.findById(Long.valueOf(id)).get();
		if(atype!=null) {
			return atype;
		}
		else {
			return atype;
		}
	}

	@Override
	public int updateAssetType(AssetType atype) {
		return atyperepo.updateAssetType(atype.getType_id(), atype.getType_name());
	}

}
