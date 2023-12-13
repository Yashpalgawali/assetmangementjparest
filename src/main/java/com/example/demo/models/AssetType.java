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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_assettype")
public class AssetType {

	@Id
	@SequenceGenerator(name="assettype_seq",allocationSize = 1,initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO , generator = "assettype_seq")
	private Long type_id;
	
	private String type_name;
	
<<<<<<< HEAD
//	@JsonIgnore
//	@OneToMany(mappedBy = "atype",fetch = FetchType.LAZY)
//	private List<Assets> asset;
=======
	@JsonIgnore
	@OneToMany(mappedBy = "atype",fetch = FetchType.LAZY)
	private List<Assets> asset;
>>>>>>> branch 'master' of https://github.com/Yashpalgawali/assetmangementjparest.git
}
