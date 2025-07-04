package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Assets;

@Repository("assetrepo")
public interface AssetRepo extends JpaRepository<Assets, Long> {

	
	@Modifying
	@Transactional
	@Query("UPDATE Assets a SET a.asset_name=:aname,a.atype.type_id=:tid,a.asset_number=:asnum,a.model_number=:monum,a.quantity=:qty WHERE a.asset_id=:aid")
	public int updateAsset(String aname,Long tid,String asnum,String monum,String qty,Long aid);
	

	
	//@Query(value="SELECT * FROM tbl_assets JOIN tbl_assettype ON tbl_assettype.type_id=tbl_assets.type_id ",nativeQuery = true)
	@Query("SELECT a FROM Assets a JOIN a.atype")// JOIN using JPQL
	public List<Assets> getAllAssets(); 
	
	@Modifying
	@Transactional
	@Query("UPDATE Assets a SET a.quantity=:qty WHERE a.asset_id=:asid")
	public int updateAssetQuantityByAssetId(Long asid,String qty);
	
//	@Query("SELECT new com.example.demo.models.Assets(a.asset_id,COUNT(a.asset_id)) FROM Assets a WHERE a.asset_id=:asid")
//	public int getAssetQuantityByAssetId(Long asid);
	
	@Query("SELECT a.quantity FROM Assets a WHERE a.asset_id=:asid")
	public int getQuantiyByAssetId(Long asid);
	
}
     