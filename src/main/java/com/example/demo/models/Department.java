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
import lombok.ToString;

@Entity
@Table(name="tbl_department")
public class Department {

	@Id
	@SequenceGenerator(name = "dept_seq",allocationSize = 1,initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO , generator = "dept_seq")
	private Long dept_id;
	
	private String dept_name;
	
	
	@ToString.Exclude 
//	@ManyToOne(targetEntity = Company.class,cascade = {CascadeType.MERGE},fetch = FetchType.EAGER)
	@ManyToOne(targetEntity = Company.class,cascade = {CascadeType.MERGE})
	@JoinColumn(name = "comp_id", referencedColumnName = "comp_id")
	private Company company;

	public Long getDept_id() {
		return dept_id;
	}

	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}


	public Department(Long dept_id, String dept_name, Company company) {
		super();
		this.dept_id = dept_id;
		this.dept_name = dept_name;
		this.company = company;
	}

	public Department() {
		super();
	}
	
	@Override
	public String toString() {
		return "Department [dept_id=" + dept_id + ", dept_name=" + dept_name + ", company=" + company + "]";
	}

}
