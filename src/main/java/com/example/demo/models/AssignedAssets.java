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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "tbl_assigned_assets")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AssignedAssets {

	@Id
	@SequenceGenerator(name = "assigned_asset_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "assigned_asset_seq")
	Long assigned_asset_id;

	@ManyToOne(targetEntity = Employee.class, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "emp_id", referencedColumnName = "emp_id")
	Employee employee;

	@ManyToOne(targetEntity = Assets.class, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "asset_id", referencedColumnName = "asset_id")
	Assets asset;

	String assign_date;

	String assign_time;

	@Transient
	String assigned_assets;

	@Transient
	List<String> ass_assets;

	@Transient
	List<String> assigned_asset_types;

	@Transient
	String assigned;

	@Transient
	String assigned_types;

	@Transient
	String model_numbers;

	@Transient
	Long asset_id;

	@Transient
	Long emp_id;

	@Transient
	String multi_assets;

	public AssignedAssets(Long eid, String aname) {
	}

}
