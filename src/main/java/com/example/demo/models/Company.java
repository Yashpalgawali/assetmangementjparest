package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "tbl_company")
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Company {

	@Id
	@SequenceGenerator(name = "comp_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "comp_seq")
	Long comp_id;

	@NotEmpty(message = "Company Name cannot be empty")
	@NotNull(message = "Company name cant be NULL")
	@Size(max = 100, min=2,message = "The Length of the Company Name should be between 2 and 100")
	String comp_name;

	public Company(String comp_name) {
		super();
		this.comp_name = comp_name;
	}
	
	public Company(Long comp_id) {
		super();
		this.comp_id = comp_id;
	}

	// @ToString.Exclude
//	@JsonIgnore
//	@OneToMany(mappedBy = "company")
//	private List<Department> deplist;

}
