package com.example.demo.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "tbl_department")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Department {

	@Id
	@SequenceGenerator(name = "dept_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "dept_seq")
	Long dept_id;
	
	@NotEmpty(message = "Department Name cannot be Empty")
	@Size(min = 2,max = 100 ,message = "Department name should have at least 2 characters")
	String dept_name;

	@ToString.Exclude
//	@ManyToOne(targetEntity = Company.class,cascade = {CascadeType.MERGE},fetch = FetchType.EAGER)
	@ManyToOne(targetEntity = Company.class, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "comp_id", referencedColumnName = "comp_id")
	Company company;

}
