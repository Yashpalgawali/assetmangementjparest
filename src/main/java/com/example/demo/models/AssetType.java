package com.example.demo.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tbl_assettype")
public class AssetType {

	@Id
	@SequenceGenerator(name="assettype_seq",allocationSize = 1,initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO , generator = "assettype_seq")
	private Long type_id;
	
	private String type_name;

	public Long getType_id() {
		return type_id;
	}

	public void setType_id(Long type_id) {
		this.type_id = type_id;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public AssetType(Long type_id, String type_name) {
		super();
		this.type_id = type_id;
		this.type_name = type_name;
	}

	public AssetType() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "AssetType [type_id=" + type_id + ", type_name=" + type_name + "]";
	}
	
//	@JsonIgnore
//	@OneToMany(mappedBy = "atype",fetch = FetchType.LAZY)
//	private List<Assets> asset;


	
}
