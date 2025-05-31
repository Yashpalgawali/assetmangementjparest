package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "tbl_assettype")
@Data
@AllArgsConstructor()
@NoArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
public class AssetType {

	@Id
	@SequenceGenerator(name = "assettype_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "assettype_seq")
	Long type_id;

	String type_name;

	public AssetType(String type_name) {
		super();
		this.type_name = type_name;
	}
}
