package com.example.demo.models;

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
@Table(name = "tbl_assets")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Assets {

	@Id
	@SequenceGenerator(name = "asset_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "asset_seq")
	Long asset_id;

	String asset_name;

	String asset_number;

	String model_number;

	String quantity;

	@Transient
	Long count;

	// @ToString.Exclude
	@ManyToOne(targetEntity = AssetType.class, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "type_id", referencedColumnName = "type_id")
	AssetType atype;

	public Assets(Long asset_id, Long count) {
		super();
		this.asset_id = asset_id;
		this.count = count;
	}

}
