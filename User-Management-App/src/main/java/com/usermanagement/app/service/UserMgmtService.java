package com.usermanagement.app.service;

import java.util.Map;

import com.usermanagement.app.binding.LoginForm;
import com.usermanagement.app.binding.UnlockAccountForm;
import com.usermanagement.app.binding.UserForm;


public interface UserMgmtService {

	public String checkEmail (String email);

	public Map<Integer, String> getCountries ( ) ;

	public Map<Integer, String> getStates (Integer countryId);

	public Map<Integer, String> getCities (Integer stateId);

	public String registerUser (UserForm user) throws Exception;

	public String unlockAccount (UnlockAccountForm accForm);

	public String login (LoginForm loginForm);

	public String forgotPwd (String email);

	
	
}
