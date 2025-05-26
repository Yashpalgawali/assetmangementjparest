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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tbl_employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

		
//	@Transient
//	private List<AssignedAssets> assigned_asts;

	
}
