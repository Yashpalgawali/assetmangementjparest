package com.example.demo.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="tbl_employee")
public class Employee {
	
	@Id
	@SequenceGenerator(name="emp_seq",allocationSize = 1,initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO , generator = "emp_seq")
	private Long emp_id;
	
	private String emp_name;
	
	private String emp_email;
	
	private String emp_contact;
	
	@ManyToOne(targetEntity = Designation.class,cascade = {CascadeType.MERGE})
	@JoinColumn(name="desig_id",referencedColumnName = "desig_id")
	private Designation designation;
	

	@ManyToOne(targetEntity = Department.class,cascade = {CascadeType.MERGE})
	@JoinColumn(name="dept_id",referencedColumnName = "dept_id")
	private Department department;
	
	@Transient
	private String multi_assets;
	
	@Transient
	private List<String> asset_ids;
	
	@Transient
	private String comments;
	
	@Transient
	private String assigned_assets;
	
	@Transient
	private String assigned_asset_types;

	public Long getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Long emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getEmp_email() {
		return emp_email;
	}

	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email;
	}

	public String getEmp_contact() {
		return emp_contact;
	}

	public void setEmp_contact(String emp_contact) {
		this.emp_contact = emp_contact;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getMulti_assets() {
		return multi_assets;
	}

	public void setMulti_assets(String multi_assets) {
		this.multi_assets = multi_assets;
	}

	public List<String> getAsset_ids() {
		return asset_ids;
	}

	public void setAsset_ids(List<String> asset_ids) {
		this.asset_ids = asset_ids;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getAssigned_assets() {
		return assigned_assets;
	}

	public void setAssigned_assets(String assigned_assets) {
		this.assigned_assets = assigned_assets;
	}

	public String getAssigned_asset_types() {
		return assigned_asset_types;
	}

	public void setAssigned_asset_types(String assigned_asset_types) {
		this.assigned_asset_types = assigned_asset_types;
	}

	public Employee(Long emp_id, String emp_name, String emp_email, String emp_contact, Designation designation,
			Department department, String multi_assets, List<String> asset_ids, String comments, String assigned_assets,
			String assigned_asset_types) {
		super();
		this.emp_id = emp_id;
		this.emp_name = emp_name;
		this.emp_email = emp_email;
		this.emp_contact = emp_contact;
		this.designation = designation;
		this.department = department;
		this.multi_assets = multi_assets;
		this.asset_ids = asset_ids;
		this.comments = comments;
		this.assigned_assets = assigned_assets;
		this.assigned_asset_types = assigned_asset_types;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Employee [emp_id=" + emp_id + ", emp_name=" + emp_name + ", emp_email=" + emp_email + ", emp_contact="
				+ emp_contact + ", designation=" + designation + ", department=" + department + ", multi_assets="
				+ multi_assets + ", asset_ids=" + asset_ids + ", comments=" + comments + ", assigned_assets="
				+ assigned_assets + ", assigned_asset_types=" + assigned_asset_types + "]";
	}
	
//	@Transient
//	private List<AssignedAssets> assigned_asts;

	
}
