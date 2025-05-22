package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.NewassetmanagementWithJpaRestApplication;
import com.example.demo.models.Company;

//@SpringBootTest(classes = NewassetmanagementWithJpaRestApplication.class) 
//@Transactional
class CompanyTest {

//	private Logger logger = LoggerFactory.getLogger(getClass());
//	
//	@Autowired
//	private CompanyRepo comprepo;
//	
//	@Test
//	void findCompanyById_Test() {
//		Optional<Company> company = comprepo.findById(1L);
//		assertNotNull(company);
//	}
//
//	@Test @DirtiesContext	
//	void saveCompany_Test() {
//		Company company = comprepo.save(new Company("Test Company11"));
//		assertEquals("Test Company11", company.getComp_name());
//	}
//	
//	@Test @DirtiesContext	
//	void updateCompany_Test() {
//		
//		Optional<Company> company = comprepo.findById(5L);
//		Company comp = null;
//		if(company.isPresent()) {
//			comp = company.get();
//		}
//		logger.info("Company found {} ",comp);
//		comp.setComp_name("Test Company (Updated)");
//		Company updatedComp = comprepo.save(comp);
//		logger.info("Company found {} ",updatedComp);
//		//assertEquals("Test Company11", comp.getComp_name());
//	}
	
}
