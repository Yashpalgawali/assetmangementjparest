package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tbl_company")
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {

	@Id
	@SequenceGenerator(name = "comp_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "comp_seq")
	private Long comp_id;

	private String comp_name;

	public Company(String comp_name) {
		super();
		this.comp_name = comp_name;
	}

	// @ToString.Exclude
//	@JsonIgnore
//	@OneToMany(mappedBy = "company")
//	private List<Department> deplist;

}
