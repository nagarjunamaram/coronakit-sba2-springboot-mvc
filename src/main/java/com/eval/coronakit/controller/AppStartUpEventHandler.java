package com.eval.coronakit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import com.eval.coronakit.dao.UserRepository;
import com.eval.coronakit.entity.Users;



@Component
public class AppStartUpEventHandler {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired	
	private PasswordEncoder penc;
	
	
	@EventListener
	public void onAppStartup(ApplicationReadyEvent event) {
		if(!userRepo.existsByUsername("Admin")) {
			userRepo.save(new Users("Admin", "admin",penc.encode("admin"),true,"admin.gmail.com","1234567890" , "ADMIN"));
		}
		
		if(!userRepo.existsByUsername("First")) {
			userRepo.save(new Users("First", "abc",penc.encode("abc"),true,"First.gmail.com","1234567890" , "USER"));
		}
		if(!userRepo.existsByUsername("Second")) {
			userRepo.save(new Users("Second", "abc",penc.encode("abc"),true,"Second.gmail.com","1234567890" , "USER"));
		}
	}
}