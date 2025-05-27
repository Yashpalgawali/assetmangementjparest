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

	String dept_name;

	@ToString.Exclude
//	@ManyToOne(targetEntity = Company.class,cascade = {CascadeType.MERGE},fetch = FetchType.EAGER)
	@ManyToOne(targetEntity = Company.class, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "comp_id", referencedColumnName = "comp_id")
	Company company;

}
