package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.GlobalException;
import com.example.demo.exceptions.NoContentException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.exceptions.ResourceNotModifiedException;
import com.example.demo.models.AssetType;
import com.example.demo.repository.AssetTypeRepo;

@Service("atypeserv")
public class AssetTypeServImpl implements AssetTypeService {

	private	final AssetTypeRepo atyperepo;
	
	public AssetTypeServImpl(AssetTypeRepo atyperepo) {
		super();
		this.atyperepo = atyperepo;
	}

	@Override
	public AssetType saveAssetType(AssetType atype) {
		AssetType assettype = atyperepo.save(atype);
		 
		if(assettype != null) {
			return assettype;
		}
		else {
			throw new GlobalException("Asset Type "+atype.getType_name()+" is not saved");
		}
	}

	@Override
	public List<AssetType> getAllAssetTypes() throws NoContentException {
		var atypeList = atyperepo.findAll();
		if(atypeList.size()>0 )
			return atypeList;
		else 
			throw new NoContentException("No Asset Types Found");
	}

	@Override
	public AssetType getAssetTypeById(Long id) {
		return atyperepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("No Asset Type found for given ID "+id));
		
	}

	@Override
	public int updateAssetType(AssetType atype)  {
		var result = atyperepo.updateAssetType(atype.getType_id(), atype.getType_name());
		
		if(result > 0)
			return result;
		else 
			throw new ResourceNotModifiedException("returned "+ atype.getType_name()+" is not updated");
	}

}
