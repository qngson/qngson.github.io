package com.manager.jwtservice;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class JwtUserDetailsService implements UserDetailsService {

	

	 private static final String USER_NAME = "qngson";
	    private static final String PASSWORD = "abcdefgh";
	 
	    @Override
	    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
	        if (USER_NAME.equals(s)){
	            return new User(USER_NAME, PASSWORD, new ArrayList<>());
	        }
	 
	        throw new UsernameNotFoundException(s);
	    }

}
