package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Company;

@Repository("comprepo")
public interface CompanyRepo extends JpaRepository<Company, String> {

	@Modifying
	@Transactional
	@Query("UPDATE Company c SET c.comp_name=:cname WHERE c.comp_id=:cid")
	public int updateCompany(String cname,String cid);
	
	  
	//@Query(value="select * from tbl_company Left Join tbl_department ON tbl_department.comp_id=tbl_company.comp_id", nativeQuery = true)
	@Query("SELECT c FROM Company c")
	public List<Company> getAllCompanies();
}
