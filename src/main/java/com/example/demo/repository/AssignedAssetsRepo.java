package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.AssignedAssets;

@Repository("assignassetrepo")
public interface AssignedAssetsRepo extends JpaRepository<AssignedAssets, Long> {

	@Query("SELECT a FROM AssignedAssets a")
	public List<AssignedAssets> getAllAssignedAssets();

	//@Query(value = "SELECT * FROM tbl_assigned_assets JOIN tbl_employee ON tbl_employee.emp_id=tbl_assigned_assets.emp_id JOIN tbl_assets ON tbl_assets.asset_id=tbl_assigned_assets.asset_id WHERE tbl_assigned_assets.emp_id=:eid", nativeQuery = true)
	@Query("SELECT tas FROM AssignedAssets tas JOIN tas.employee JOIN tas.asset WHERE tas.employee.emp_id=:eid")
	public List<AssignedAssets> getAllAssignedAssetsByEmpId(Long eid);

//	@Query(value = "SELECT tbl_assets.*,tbl_assigned_assets.* FROM tbl_assigned_assets JOIN tbl_employee ON tbl_employee.emp_id=tbl_assigned_assets.emp_id JOIN tbl_assets ON tbl_assets.asset_id=tbl_assigned_assets.asset_id WHERE tbl_assigned_assets.emp_id=:eid", nativeQuery = true)
	@Query("SELECT a FROM AssignedAssets a JOIN a.employee JOIN a.asset WHERE a.employee.emp_id=:eid")
	public List<AssignedAssets> getOnlyAssignedAssetsByEmpId(Long eid);

	@Modifying
	@Transactional
	@Query("DELETE FROM AssignedAssets a  WHERE a.asset.asset_id=:assetid AND a.employee.emp_id=:empid")
	public int deleteAssignedAssetByEmpidAssetId(Long assetid, Long empid);

	@Query(value = "SELECT tas.*,GROUP_CONCAT(ta.asset_name),GROUP_CONCAT(at.type_name),te.emp_name,te.emp_email,te.emp_contact,te.desig_id,des.desig_name,td.dept_id,td.dept_name,td.comp_id,tc.comp_name,GROUP_CONCAT(ta.model_number) FROM tbl_assigned_assets tas JOIN tbl_assets ta ON ta.asset_id=tas.asset_id JOIN tbl_assettype AS at ON at.type_id=ta.type_id JOIN tbl_employee AS te ON te.emp_id=tas.emp_id JOIN tbl_designation AS des ON des.desig_id=te.desig_id JOIN tbl_department AS td ON td.dept_id=te.dept_id JOIN tbl_company AS tc ON tc.comp_id=td.comp_id GROUP BY te.emp_id", nativeQuery = true)
	public List<Object[]> getAllNewAssignedAssets();

}