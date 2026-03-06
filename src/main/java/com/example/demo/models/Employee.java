package com.example.demo.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

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
