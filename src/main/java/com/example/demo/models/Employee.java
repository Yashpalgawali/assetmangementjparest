package com.example.demo.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "tbl_employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Employee {

	@Id
	@SequenceGenerator(name = "emp_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "emp_seq")
	Long emp_id;

	@NotEmpty(message = "Employee name cannot be blank")
	@Size(min = 2 , max = 100, message = "Enter with at least 2 characters")
	String emp_name;

	@NotEmpty(message = "Email cannot be blank")
	@Email(message = "Please Enter a valid email address")
	String emp_email;

	@NotEmpty(message = "Mobile cannot be blank")
	@Pattern(regexp = "(^|[0-9]{10})" , message = "Mobile Number must be 10 digits" )
	String emp_contact;

	//@ManyToOne(targetEntity = Designation.class, cascade = { CascadeType.MERGE })
	@ManyToOne(targetEntity = Designation.class)
	@JoinColumn(name = "desig_id", referencedColumnName = "desig_id")
	Designation designation;

//	@ManyToOne(targetEntity = Department.class, cascade = { CascadeType.MERGE })
	@ManyToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept_id", referencedColumnName = "dept_id")
	Department department;

	@Transient
	String multi_assets;

	@Transient
	List<String> asset_ids;

	@Transient
	String comments;

	@Transient
	String assigned_assets;

	@Transient
	String assigned_asset_types;

//	@Transient
//	List<AssignedAssets> assigned_asts;

}
