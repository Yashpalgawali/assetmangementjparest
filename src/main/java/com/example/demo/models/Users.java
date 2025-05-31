package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Table(name = "tbl_users")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Users {

	@Id
	@SequenceGenerator(name = "user_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "user_seq", strategy = GenerationType.AUTO)
	Long user_id;

	String username;

	String password;

	String email;

	int enabled;

	String role;

	@Transient
	String cnf_pass;

	@Transient
	String cnf_otp;

}
