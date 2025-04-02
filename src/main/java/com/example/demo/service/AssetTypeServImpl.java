package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;

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
		return atyperepo.save(atype);
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
		AssetType atype = atyperepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("No Asset Type found for given ID "+id));
		return atype;
	}

	@Override
	public int updateAssetType(AssetType atype) throws ResourceNotModifiedException {
		var result = atyperepo.updateAssetType(atype.getType_id(), atype.getType_name());
		if(result > 0)
			return result;
		else 
			throw new ResourceNotModifiedException(atype.getType_name()+" is not updated");
	}

}
