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
@Table(name="tbl_assigned_assets")
public class AssignedAssets {

	@Id
	@SequenceGenerator(name="assigned_asset_seq",initialValue = 1,allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "assigned_asset_seq")
	private Long assigned_asset_id;

	@ManyToOne(targetEntity = Employee.class , cascade = {CascadeType.MERGE})
	@JoinColumn(name="emp_id",referencedColumnName = "emp_id")
	private Employee employee;

	@ManyToOne(targetEntity = Assets.class,cascade = {CascadeType.MERGE})
	@JoinColumn(name="asset_id", referencedColumnName = "asset_id")
	private Assets asset;

	private String assign_date;

	private String assign_time;

	@Transient
	private String assigned_assets;

	@Transient
	private List<String> ass_assets;

	@Transient
	private List<String> assigned_asset_types;

	@Transient
	private String assigned;

	@Transient
	private String assigned_types;
	
	@Transient
	private String model_numbers;
	
	@Transient
	private Long asset_id;
	
	@Transient
	private Long emp_id;
	
	@Transient
	private String multi_assets;

	public AssignedAssets(Long eid,String aname) {}

	public Long getAssigned_asset_id() {
		return assigned_asset_id;
	}

	public void setAssigned_asset_id(Long assigned_asset_id) {
		this.assigned_asset_id = assigned_asset_id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Assets getAsset() {
		return asset;
	}

	public void setAsset(Assets asset) {
		this.asset = asset;
	}

	public String getAssign_date() {
		return assign_date;
	}

	public void setAssign_date(String assign_date) {
		this.assign_date = assign_date;
	}

	public String getAssign_time() {
		return assign_time;
	}

	public void setAssign_time(String assign_time) {
		this.assign_time = assign_time;
	}

	public String getAssigned_assets() {
		return assigned_assets;
	}

	public void setAssigned_assets(String assigned_assets) {
		this.assigned_assets = assigned_assets;
	}

	public List<String> getAss_assets() {
		return ass_assets;
	}

	public void setAss_assets(List<String> ass_assets) {
		this.ass_assets = ass_assets;
	}

	public List<String> getAssigned_asset_types() {
		return assigned_asset_types;
	}

	public void setAssigned_asset_types(List<String> assigned_asset_types) {
		this.assigned_asset_types = assigned_asset_types;
	}

	public String getAssigned() {
		return assigned;
	}

	public void setAssigned(String assigned) {
		this.assigned = assigned;
	}

	public String getAssigned_types() {
		return assigned_types;
	}

	public void setAssigned_types(String assigned_types) {
		this.assigned_types = assigned_types;
	}

	public String getModel_numbers() {
		return model_numbers;
	}

	public void setModel_numbers(String model_numbers) {
		this.model_numbers = model_numbers;
	}

	public Long getAsset_id() {
		return asset_id;
	}

	public void setAsset_id(Long asset_id) {
		this.asset_id = asset_id;
	}

	public Long getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Long emp_id) {
		this.emp_id = emp_id;
	}

	public String getMulti_assets() {
		return multi_assets;
	}

	public void setMulti_assets(String multi_assets) {
		this.multi_assets = multi_assets;
	}

	public AssignedAssets(Long assigned_asset_id, Employee employee, Assets asset, String assign_date,
			String assign_time, String assigned_assets, List<String> ass_assets, List<String> assigned_asset_types,
			String assigned, String assigned_types, String model_numbers, Long asset_id, Long emp_id,
			String multi_assets) {
		super();
		this.assigned_asset_id = assigned_asset_id;
		this.employee = employee;
		this.asset = asset;
		this.assign_date = assign_date;
		this.assign_time = assign_time;
		this.assigned_assets = assigned_assets;
		this.ass_assets = ass_assets;
		this.assigned_asset_types = assigned_asset_types;
		this.assigned = assigned;
		this.assigned_types = assigned_types;
		this.model_numbers = model_numbers;
		this.asset_id = asset_id;
		this.emp_id = emp_id;
		this.multi_assets = multi_assets;
	}

	public AssignedAssets() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "AssignedAssets [assigned_asset_id=" + assigned_asset_id + ", employee=" + employee + ", asset=" + asset
				+ ", assign_date=" + assign_date + ", assign_time=" + assign_time + ", assigned_assets="
				+ assigned_assets + ", ass_assets=" + ass_assets + ", assigned_asset_types=" + assigned_asset_types
				+ ", assigned=" + assigned + ", assigned_types=" + assigned_types + ", model_numbers=" + model_numbers
				+ ", asset_id=" + asset_id + ", emp_id=" + emp_id + ", multi_assets=" + multi_assets + "]";
	} 

	

}
