package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@Entity
@Table(name="tbl_activity")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Activity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "activity_seq")
	@SequenceGenerator(name = "activity_seq",allocationSize = 1,initialValue = 1)
	private Long activity_id;
	
	private String activity;
	
	private String operation_date;
	
	private String operation_time;

//	public Long getActivity_id() {
//		return activity_id;
//	}
//
//	public void setActivity_id(Long activity_id) {
//		this.activity_id = activity_id;
//	}
//
//	public String getActivity() {
//		return activity;
//	}
//
//	public void setActivity(String activity) {
//		this.activity = activity;
//	}
//
//	public String getOperation_date() {
//		return operation_date;
//	}
//
//	public void setOperation_date(String operation_date) {
//		this.operation_date = operation_date;
//	}
//
//	public String getOperation_time() {
//		return operation_time;
//	}
//
//	public void setOperation_time(String operation_time) {
//		this.operation_time = operation_time;
//	}
//
//	public Activity(Long activity_id, String activity, String operation_date, String operation_time) {
//		super();
//		this.activity_id = activity_id;
//		this.activity = activity;
//		this.operation_date = operation_date;
//		this.operation_time = operation_time;
//	}
//
//	public Activity() {
//		super();
//	}
	
}
