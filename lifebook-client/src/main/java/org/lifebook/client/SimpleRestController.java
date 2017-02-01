package org.lifebook.client;

import java.util.List;

import org.lifebook.models.User;
import org.lifebook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleRestController {
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping("/")
	public String sayHello(){
		return "Hello world";
	}
	
	@RequestMapping("/users")
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}

}
