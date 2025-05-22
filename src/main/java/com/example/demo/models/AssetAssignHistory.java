package com.example.demo.models;

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
@Table(name="tbl_asset_assign_history")
public class AssetAssignHistory {

	@Id
	@SequenceGenerator(name = "hist_seq" , allocationSize = 1 , initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "hist_seq")
	private Long hist_id;
	
	
	@ManyToOne(targetEntity = Assets.class, cascade = {CascadeType.MERGE , CascadeType.REFRESH, CascadeType.REMOVE})
	@JoinColumn(name="asset_id",referencedColumnName = "asset_id")
	private Assets asset;
	
	@ManyToOne(targetEntity = Employee.class , cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE })
	@JoinColumn(name="emp_id",referencedColumnName = "emp_id")
	private Employee employee;
	
	private String operation_date;
	
	private String operation_time;
	
	private String operation;
	
	@Transient
	private Long asset_id;

	public Long getHist_id() {
		return hist_id;
	}

	public void setHist_id(Long hist_id) {
		this.hist_id = hist_id;
	}

	public Assets getAsset() {
		return asset;
	}

	public void setAsset(Assets asset) {
		this.asset = asset;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getOperation_date() {
		return operation_date;
	}

	public void setOperation_date(String operation_date) {
		this.operation_date = operation_date;
	}

	public String getOperation_time() {
		return operation_time;
	}

	public void setOperation_time(String operation_time) {
		this.operation_time = operation_time;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Long getAsset_id() {
		return asset_id;
	}

	public void setAsset_id(Long asset_id) {
		this.asset_id = asset_id;
	}

	public AssetAssignHistory(Long hist_id, Assets asset, Employee employee, String operation_date,
			String operation_time, String operation, Long asset_id) {
		super();
		this.hist_id = hist_id;
		this.asset = asset;
		this.employee = employee;
		this.operation_date = operation_date;
		this.operation_time = operation_time;
		this.operation = operation;
		this.asset_id = asset_id;
	}

	public AssetAssignHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "AssetAssignHistory [hist_id=" + hist_id + ", asset=" + asset + ", employee=" + employee
				+ ", operation_date=" + operation_date + ", operation_time=" + operation_time + ", operation="
				+ operation + ", asset_id=" + asset_id + "]";
	}

}
