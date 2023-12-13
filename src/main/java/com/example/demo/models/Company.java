package com.example.demo.models;

import java.util.List;

<<<<<<< HEAD
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_company")
@ToString
public class Company {

	
	@Id
	@SequenceGenerator(name = "comp_seq",allocationSize = 1,initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "comp_seq")
	private Long comp_id;
	
	private String comp_name;
	
	//@ToString.Exclude
//	@JsonIgnore
//	@OneToMany(mappedBy = "company")
//	private List<Department> deplist;
=======
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_company")
public class Company {

	
	@Id
	@SequenceGenerator(name = "comp_seq",allocationSize = 1,initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "comp_seq")
	private Long comp_id;
	
	private String comp_name;
	
	//@ToString.Exclude
	@JsonIgnore
	@OneToMany(cascade = CascadeType.MERGE,mappedBy = "company",targetEntity = Department.class )
	private List<Department> deplist;
>>>>>>> branch 'master' of https://github.com/Yashpalgawali/assetmangementjparest.git
	
}
