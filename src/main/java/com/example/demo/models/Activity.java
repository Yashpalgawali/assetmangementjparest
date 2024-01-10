package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tbl_activity")
public class Activity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long activity_id;
	
	private String activity;
	
	private String operation_date;
	
	private String operation_time;
}
