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
