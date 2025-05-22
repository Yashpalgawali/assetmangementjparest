package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Table(name= "tbl_users")
@Entity
public class Users {

	@Id
	@SequenceGenerator(name = "user_seq",allocationSize = 1 , initialValue = 1)
	@GeneratedValue(generator = "user_seq",strategy = GenerationType.AUTO)
	private Long user_id;

	private String username;

	private String password;

	private String email;
	
	private int enabled;

	private String role;

	@Transient
	private String cnf_pass;

	@Transient
	private String cnf_otp;

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCnf_pass() {
		return cnf_pass;
	}

	public void setCnf_pass(String cnf_pass) {
		this.cnf_pass = cnf_pass;
	}

	public String getCnf_otp() {
		return cnf_otp;
	}

	public void setCnf_otp(String cnf_otp) {
		this.cnf_otp = cnf_otp;
	}

	public Users(Long user_id, String username, String password, String email, int enabled, String role,
			String cnf_pass, String cnf_otp) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
		this.role = role;
		this.cnf_pass = cnf_pass;
		this.cnf_otp = cnf_otp;
	}

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Users [user_id=" + user_id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", enabled=" + enabled + ", role=" + role + ", cnf_pass=" + cnf_pass + ", cnf_otp=" + cnf_otp + "]";
	}

	

}
