package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Company;

@Repository("comprepo")
@Transactional
public interface CompanyRepo extends JpaRepository<Company, Long> {

	@Modifying
	@Query("UPDATE Company c SET c.comp_name=:cname WHERE c.comp_id=:cid")
	public int updateCompany(String cname, Long cid);

//	@Query("SELECT c FROM Company c")
//	public List<Company> getAllCompanies();
}
