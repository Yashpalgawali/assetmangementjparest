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
import javax.persistence.Transient;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "tbl_asset_assign_history")
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AssetAssignHistory {

	@Id
	@SequenceGenerator(name = "hist_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "hist_seq")
	Long hist_id;

	@ManyToOne(targetEntity = Assets.class, cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE })
	@JoinColumn(name = "asset_id", referencedColumnName = "asset_id")
	Assets asset;

	@ManyToOne(targetEntity = Employee.class, cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE })
	@JoinColumn(name = "emp_id", referencedColumnName = "emp_id")
	Employee employee;

	String operation_date;

	String operation_time;

	String operation;

	@Transient
	Long asset_id;

}
