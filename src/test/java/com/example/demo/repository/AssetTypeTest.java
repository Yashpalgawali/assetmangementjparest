package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigInteger;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.NewassetmanagementWithJpaRestApplication;
import com.example.demo.models.AssetType;

@SpringBootTest(classes = NewassetmanagementWithJpaRestApplication.class) 
@Transactional
class AssetTypeTest {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AssetTypeRepo assettyperepo;
	
	@Autowired
	private EntityManager entityManager;
	
	private long initialSequence;
	
	@Test
	void findAssetTypeById_Test() {
		 
		AssetType assettype = assettyperepo.findById(2L).get();
		logger.info("Asset Type for ID {} is {} ",2,assettype);
		assertNotNull(assettype);
	}
	
	@Test @DirtiesContext  
	void saveAssetType_Test() {
		
		AssetType assetType = assettyperepo.save(new AssetType("Test Asset Type"));
		assertEquals("Test Asset Type", assetType.getType_name());
		
	}
	
	@Test @DirtiesContext  
	void updateAssetType_Test() {
		
		  AssetType assetTypeObj = assettyperepo.findById(3L).get(); 
		  assetTypeObj.setType_name("Test Asset Type (Updated)");
		  AssetType updated = assettyperepo.save(assetTypeObj);
		assertEquals("Test Asset Type (Updated)", updated.getType_name());
		
	}


}
