package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.models.Activity;
import com.example.demo.models.Assets;
import com.example.demo.repository.ActivityRepo;
import com.example.demo.repository.AssetRepo;

@Service("assetserv")
public class AssetServImpl implements AssetService {

	private AssetRepo assetrepo;
	
	private ActivityRepo activityrepo;
	
	public AssetServImpl(AssetRepo assetrepo, ActivityRepo activityrepo) {
		super();
		this.assetrepo = assetrepo;
		this.activityrepo = activityrepo;
	}

	DateTimeFormatter tday  =  DateTimeFormatter.ofPattern("dd-MM-yyyy");
	DateTimeFormatter ttime =  DateTimeFormatter.ofPattern("hh:mm:ss");
	
	Activity activity = null;
	
	@Override
	public Assets saveAssets(Assets asset) {
		Assets ast = assetrepo.save(asset);
		if(ast!=null) {
			activity=new Activity();
			activity.setActivity(asset.getAsset_name() +" is saved successfully");
			activity.setOperation_date(tday.format(LocalDateTime.now()));
			activity.setOperation_time(ttime.format(LocalDateTime.now()));
			activityrepo.save(activity);
			return ast;
		}
		else
		 {
			activity=new Activity();
			activity.setActivity(asset.getAsset_name() +" is not saved ");
			activity.setOperation_date(tday.format(LocalDateTime.now()));
			activity.setOperation_time(ttime.format(LocalDateTime.now()));
			activityrepo.save(activity);
			return ast;
		 }
	}

	@Override
	public List<Assets> getAllAssets() {
		return assetrepo.findAll();
	}

	@Override
	public Assets getAssetsById(String id) { 
		return assetrepo.findById(Long.valueOf(id)).get();
	}

	@Override
	public int updateAssets(Assets asset) {
		int res = assetrepo.updateAsset(asset.getAsset_name(), asset.getAtype().getType_id(), asset.getAsset_number(), asset.getModel_number(), asset.getQuantity() , asset.getAsset_id());
		if(res>0) {
			activity=new Activity();
			activity.setActivity(asset.getAsset_name() +" is updated successfully");
			activity.setOperation_date(tday.format(LocalDateTime.now()));
			activity.setOperation_time(ttime.format(LocalDateTime.now()));
			activityrepo.save(activity);
			return res;
		 }
		else {
			activity=new Activity();
			activity.setActivity(asset.getAsset_name() +" is not updated ");
			activity.setOperation_date(tday.format(LocalDateTime.now()));
			activity.setOperation_time(ttime.format(LocalDateTime.now()));
			activityrepo.save(activity);
			return res;
		}
	}

	@Override
	public int updateAssetQuantityByAssetId(Long asid,String qty) {
		int res = assetrepo.updateAssetQuantityByAssetId(asid, qty);
		Assets ast = assetrepo.findById(asid).get();
		if(res>0) {
			activity=new Activity();
			activity.setActivity("Quantity of "+ast.getAsset_name() +" is updated to "+qty+" successfully");
			activity.setOperation_date(tday.format(LocalDateTime.now()));
			activity.setOperation_time(ttime.format(LocalDateTime.now()));
			activityrepo.save(activity);
			return res;
		 }
		else {
			activity=new Activity();
			activity.setActivity("Quantity of "+ast.getAsset_name() +" is not updated ");
			activity.setOperation_date(tday.format(LocalDateTime.now()));
			activity.setOperation_time(ttime.format(LocalDateTime.now()));
			activityrepo.save(activity);
			return res;
		}
	}

	@Override
	public int getAssetQuantityByAssetId(Long asid) {
		return assetrepo.getQuantiyByAssetId(asid);
	}
}
