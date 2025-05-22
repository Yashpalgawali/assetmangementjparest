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

@Entity
@Table(name="tbl_assets")
public class Assets {

	@Id
	@SequenceGenerator(name="asset_seq",initialValue = 1,allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO , generator = "asset_seq")
	private Long asset_id;
	
	private String asset_name;
	
	private String asset_number;
	
	private String model_number;
	
	private String quantity;
	
	@Transient
	private Long count;
	
	public Assets(Long asset_id, Long count) {
		super();
		this.asset_id = asset_id;
		this.count = count;
	}

	//@ToString.Exclude
	@ManyToOne(targetEntity = AssetType.class,cascade = {CascadeType.MERGE})
	@JoinColumn(name="type_id",referencedColumnName = "type_id")
	private AssetType atype;

	public Long getAsset_id() {
		return asset_id;
	}

	public void setAsset_id(Long asset_id) {
		this.asset_id = asset_id;
	}

	public String getAsset_name() {
		return asset_name;
	}

	public void setAsset_name(String asset_name) {
		this.asset_name = asset_name;
	}

	public String getAsset_number() {
		return asset_number;
	}

	public void setAsset_number(String asset_number) {
		this.asset_number = asset_number;
	}

	public String getModel_number() {
		return model_number;
	}

	public void setModel_number(String model_number) {
		this.model_number = model_number;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public AssetType getAtype() {
		return atype;
	}

	public void setAtype(AssetType atype) {
		this.atype = atype;
	}

	public Assets(Long asset_id, String asset_name, String asset_number, String model_number, String quantity,
			Long count, AssetType atype) {
		super();
		this.asset_id = asset_id;
		this.asset_name = asset_name;
		this.asset_number = asset_number;
		this.model_number = model_number;
		this.quantity = quantity;
		this.count = count;
		this.atype = atype;
	}

	public Assets() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Assets [asset_id=" + asset_id + ", asset_name=" + asset_name + ", asset_number=" + asset_number
				+ ", model_number=" + model_number + ", quantity=" + quantity + ", count=" + count + ", atype=" + atype
				+ "]";
	}

	
		
}

