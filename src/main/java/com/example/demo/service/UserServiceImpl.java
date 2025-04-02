package com.example.demo.service;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.models.Users;
import com.example.demo.repository.UsersRepository;

@Service("userserv")
public class UserServiceImpl implements UserService {
	
	private UsersRepository userrepo;
	
	/**
	 * @param userrepo
	 */
	public UserServiceImpl(UsersRepository userrepo) {
		super();
		this.userrepo = userrepo;
	}

	@Override
	public int updateUsersPassword(String pass, Long id) {
	
		return userrepo.updateUsersPassword(pass,id);
		//return userrepo.updateUsersPassword(new BCryptPasswordEncoder().encode(pass), id);
	}

	@Override
	public Users getUserByEmailId(String email) {
		
		return userrepo.getUserByEmailId(email);
	}

	@Override
	public Users getUserByUserName(String uname) {
		return userrepo.getUserByUserName(uname);
	}

	@Override
	public Users getUserByUserId(Long uid) {
		try {
			return userrepo.findById(uid).get();
		}
		catch(Exception e)
		{
			return null;
		}
	}

}
