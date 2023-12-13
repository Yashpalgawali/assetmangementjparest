package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.models.Users;
import com.example.demo.repository.UsersRepository;

@Service("userserv")
public class UserServiceImpl implements UserService {

	@Autowired
	UsersRepository userrepo;
	
	@Override
	public int updateUsersPassword(String pass, Long id) {
	
		return userrepo.updateUsersPassword(new BCryptPasswordEncoder().encode(pass), id);
=======
import org.springframework.stereotype.Service;

import com.example.demo.models.Users;
import com.example.demo.repository.UsersRepository;

@Service("userserv")
public class UserServiceImpl implements UserService {

	@Autowired
	UsersRepository userrepo;
	
	@Override
	public int updateUsersPassword(String pass, Long id) {
		// TODO Auto-generated method stub
		
		System.err.println("Inside updateUserpassword service \n Password is "+pass+" \n ID is "+id);
		return userrepo.updateUsersPassword(pass, id);
>>>>>>> branch 'master' of https://github.com/Yashpalgawali/assetmangementjparest.git
	}

	@Override
	public Users getUserByEmailId(String email) {
		// TODO Auto-generated method stub
		
		return userrepo.getUserByEmailId(email);
	}

	@Override
	public Users getUserByUserName(String uname) {
		// TODO Auto-generated method stub
		return userrepo.getUserByUserName(uname);
	}

	@Override
	public Users getUserByUserId(Long uid) {
		// TODO Auto-generated method stub
		try {
			return userrepo.findById(uid).get();
		}
		catch(Exception e)
		{
			return null;
		}
	}

}
