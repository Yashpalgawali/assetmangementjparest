package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="tbl_designation")
public class Designation {

	@Id
	@SequenceGenerator(name = "desig_seq",allocationSize = 1,initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "desig_seq")
	private Long desig_id;
	
	private String desig_name;

	public Long getDesig_id() {
		return desig_id;
	}

	public void setDesig_id(Long desig_id) {
		this.desig_id = desig_id;
	}

	public String getDesig_name() {
		return desig_name;
	}

	public void setDesig_name(String desig_name) {
		this.desig_name = desig_name;
	}

	public Designation(Long desig_id, String desig_name) {
		super();
		this.desig_id = desig_id;
		this.desig_name = desig_name;
	}

	public Designation() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Designation [desig_id=" + desig_id + ", desig_name=" + desig_name + "]";
	}

	
}
