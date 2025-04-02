package com.example.demo.service;

import java.util.List;

import com.example.demo.exceptions.NoContentException;
import com.example.demo.exceptions.ResourceNotModifiedException;
import com.example.demo.models.AssetType;

public interface AssetTypeService {

	public AssetType saveAssetType(AssetType atype);

	public List<AssetType> getAllAssetTypes() throws NoContentException ;

	public AssetType getAssetTypeById(Long id);

	public int updateAssetType(AssetType atype) throws ResourceNotModifiedException;

}
