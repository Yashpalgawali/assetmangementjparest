package com.example.demo.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

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
