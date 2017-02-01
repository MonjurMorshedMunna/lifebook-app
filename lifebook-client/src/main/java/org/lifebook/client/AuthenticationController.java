package org.lifebook.client;

import javax.validation.Valid;

import org.lifebook.configuration.CustomUserDetailsService;
import org.lifebook.models.Role;
import org.lifebook.models.User;
import org.lifebook.models.UserRole;
import org.lifebook.repositories.RoleRepository;
import org.lifebook.repositories.UserRepository;
import org.lifebook.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthenticationController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@GetMapping("/login")
	public String showLoginForm(){
		return "login";
	}

	@PostMapping("/login")
	public String login(@Valid User user, BindingResult bindingResult, Model model){
		userDetailsService.loadUserByUsername(user.getUserName());
		return "/";
	}
	
	@GetMapping("/register")
	public String showRegisterForm(Model model){
		model.addAttribute("errorMessage", "");
		return "register";
	}
	
	@PostMapping("/register")
	@Transactional
	public String register(@Valid User user, BindingResult bindingResult, Model model){
		if(!user.getPassword().equals(user.getRetypePassword())){
			model.addAttribute("errorMessage", "Password didn't match");
			return "register";
		}
		user.setUserName(user.getEmail());
		userRepository.save(user);
		Role role = roleRepository.findOne(Long.parseLong("1"));
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		userRoleRepository.save(userRole);
		userDetailsService.loadUserByUsername(user.getUserName());
		return "/";
	}

}
