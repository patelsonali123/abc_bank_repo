package com.usermanagement.app.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.usermanagement.app.binding.LoginForm;
import com.usermanagement.app.binding.UnlockAccountForm;
import com.usermanagement.app.binding.UserForm;
import com.usermanagement.app.service.UserMgmtService;
@RestController
public class UserRestController {
	
	@Autowired
	private UserMgmtService userMgmtService;
	
	@PostMapping("/login")
	public ResponseEntity<String>login(@RequestBody LoginForm loginForm){
		String status=userMgmtService.login(loginForm);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
 
	@GetMapping("/countries")
	public Map<Integer, String>LoadCountries(){
		return userMgmtService.getCountries();
	}
	
	@GetMapping("/states/{countryId}")
	public Map<Integer, String>LoadStates(@PathVariable Integer countryId){
		return userMgmtService.getStates(countryId);
	}
	
	@GetMapping("/cities/{stateId}")
	public Map<Integer, String>LoadCities(@PathVariable Integer stateId){
		return userMgmtService.getStates(stateId);
	}
	@GetMapping("/email/{email}")
	public String emailCheck(@PathVariable String email) {
		return userMgmtService.checkEmail(email);
	}
	@PostMapping("/user")
	public ResponseEntity<String>userRegistration(@RequestBody UserForm userForm) throws Exception{
		String status= userMgmtService.registerUser(userForm);
		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}
	@PostMapping("/unlock")
	public ResponseEntity<String> unlockAccount(@RequestBody UnlockAccountForm unlockAccForm){
		String status= userMgmtService.unlockAccount(unlockAccForm);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
	@GetMapping("/forgotpwd/{email}")
	public ResponseEntity<String> forgotPwd(@PathVariable String email){
		String status= userMgmtService.forgotPwd(email);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
}
