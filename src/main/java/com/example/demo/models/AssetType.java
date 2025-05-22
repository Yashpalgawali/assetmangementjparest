package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_assettype")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetType {

	@Id
	@SequenceGenerator(name = "assettype_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "assettype_seq")
	private Long type_id;

	private String type_name;

	public AssetType(String type_name) {
		super();
		this.type_name = type_name;
	}

//	public Long getType_id() {
//		return type_id;
//	}
//
//	public void setType_id(Long type_id) {
//		this.type_id = type_id;
//	}
//
//	public String getType_name() {
//		return type_name;
//	}
//
//	public void setType_name(String type_name) {
//		this.type_name = type_name;
//	}
//
//	public AssetType(Long type_id, String type_name) {
//		super();
//		this.type_id = type_id;
//		this.type_name = type_name;
//	}
//
//	public AssetType() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	@Override
//	public String toString() {
//		return "AssetType [type_id=" + type_id + ", type_name=" + type_name + "]";
//	}

//	@JsonIgnore
//	@OneToMany(mappedBy = "atype",fetch = FetchType.LAZY)
//	private List<Assets> asset;

}
