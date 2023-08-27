package com.myblogapi.controller;

import java.util.Collections;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myblogapi.entity.Role;
import com.myblogapi.entity.User;
import com.myblogapi.payload.JWTAuthResponse;
import com.myblogapi.payload.SignIn;
import com.myblogapi.payload.SignUp;
import com.myblogapi.repository.RoleRepository;
import com.myblogapi.repository.UserRepository;
import com.myblogapi.security.JwtTokenProvider;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@PostMapping("/signin")
	public ResponseEntity<?> verifyLoginCredentials(@RequestBody SignIn signIn){
		Authentication authenticate = authManager.authenticate(new 
				UsernamePasswordAuthenticationToken(signIn.getUsernameOrEmail(), signIn.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		
		String generateToken = tokenProvider.generateToken(authenticate);
		return ResponseEntity.ok(new JWTAuthResponse(generateToken));
		
		//return new ResponseEntity<>("User loggedin successfully!!!", HttpStatus.OK);
	}
	@PostMapping("/signup")
	public ResponseEntity<?> createNewUserId(@RequestBody SignUp signUp){
		
		if(userRepo.existsByUsername(signUp.getUsername())) {
			return new ResponseEntity<>("This username is already taken!!!", HttpStatus.BAD_REQUEST);
		}
		
		if(userRepo.existsByEmail(signUp.getEmail())) {
			return new ResponseEntity<>("This email is already taken!!!", HttpStatus.BAD_REQUEST);
		}
		
		User user = new User();
		user.setName(signUp.getName());
		user.setUsername(signUp.getUsername());
		user.setEmail(signUp.getEmail());
		user.setPassword(encoder.encode(signUp.getPassword()));
		
		Role role = roleRepo.findByName("ROLE_ADMIN").get();
		Set<Role> roles = Collections.singleton(role);
		user.setRoles(roles);
		
		userRepo.save(user);
//		SignUp signUpDto = new SignUp();
//		signUpDto.setId(savedUser.getId());
//		signUpDto.setName(savedUser.getName());
//		signUpDto.setUsername(savedUser.getUsername());
//		signUpDto.setEmail(savedUser.getEmail());
//		signUpDto.setPassword(savedUser.getPassword());
//		signUpDto.setRoles(savedUser.getRoles());
		
		return new ResponseEntity<>("User registered successfully!!!", HttpStatus.CREATED);
	}

}
