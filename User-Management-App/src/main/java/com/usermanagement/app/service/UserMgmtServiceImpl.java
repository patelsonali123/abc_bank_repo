package com.usermanagement.app.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.usermanagement.app.binding.LoginForm;
import com.usermanagement.app.binding.UnlockAccountForm;
import com.usermanagement.app.binding.UserForm;
import com.usermanagement.app.entity.Cities;
import com.usermanagement.app.entity.Country;
import com.usermanagement.app.entity.State;
import com.usermanagement.app.entity.User;
import com.usermanagement.app.repository.CityRepo;
import com.usermanagement.app.repository.CountryRepo;
import com.usermanagement.app.repository.StateRepo;
import com.usermanagement.app.repository.UserRepo;
import com.usermanagement.app.utils.EmailUtils;

public class UserMgmtServiceImpl implements UserMgmtService{
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private StateRepo stateRepo;
	@Autowired
	private CountryRepo countryRepo;
	@Autowired
	private CityRepo cityRepo;
	@Autowired
	private EmailUtils emailUtils;

	@Override
	public String checkEmail(String email) {
		User user= userRepo.findByEmail(email);
				if(user==null) {
					return "Unique";
				}
				return "Duplicate";
	}

	@Override
	public Map getCountries() {
		List<Country> countries= countryRepo.findAll();
		Map<Integer, String> COUNTRIES_MAP = new HashMap<>();
		
		countries.forEach(country -> {
			 COUNTRIES_MAP.put(country.getCountryId(),country.getCountryName());	
		});
		return COUNTRIES_MAP;
		
	}

	@Override
	public Map getStates(Integer countryId) {
		List<State> states= stateRepo.findByCountryId(countryId);
		
		 Map<Integer, String>State_MAP = new HashMap<>();
		 
		 states.forEach(state ->
		 State_MAP.put(state.getStateId(),state.getStateName()));
			return State_MAP;
	}

	@Override
	public Map getCities(Integer stateId) {
		List<Cities> cities= cityRepo.finByStateId(stateId);
		
		Map<Integer, String> Cities_MAP= new HashMap<>();
		
		cities.forEach(city ->
		Cities_MAP.put(city.getCitiesId(), city.getCitiesName()));
		return Cities_MAP;
	}

	@Override
	public String registerUser(UserForm userform) {
		User entity= new User();
		BeanUtils.copyProperties(userform, entity); 
		
		entity.setUserpwd(generateRandomPwd());
		//set account status locked
		entity.setAccstatus("Locked");
		userRepo.save(entity);
		
		String to=userform.getEmail();
		String subject= "Registration Email";
		String body= readEmailBody("REG_EMAIL_BODY.txt", entity);
		emailUtils.sendEmail(to,subject,body);
		
		return "User Account created";
	}
	
	@Override
	public String unlockAccount(UnlockAccountForm unlockaccForm) {
		String email= unlockaccForm.getEmail();
		User user=userRepo.findByEmail(email);
		if(user!=null && user.getUserpwd().equals(unlockaccForm.getTempPwd())){
			user.setUserpwd(unlockaccForm.getNewPwd());
			user.setAccstatus("Unlocked");
			userRepo.save(user);
			return "Account unlocked";
		}
		return "Invalid temp Pwd";
	}

	@Override
	public String login(LoginForm loginForm) {
		User user=userRepo.finByEmailandUserPwd(loginForm.getEmail(),loginForm.getPwd());
		if (user== null) {
			return "Invalid Credentials";
		}
		if(user.getAccstatus().equals("Locked")) {
			return "Account Locked";
		}
		
		return "Success";
	}
	

	@Override
	public String forgotPwd(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	private String generateRandomPwd() {
		String text= "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder sb= new StringBuilder();
		Random random= new Random();
		int pwdLength= 6;
		for(int i=1;i<=pwdLength; i++) {
			 int index= random.nextInt(text.length());
			 sb.append(text.charAt(index));
		}
		return sb.toString();		
	}

	private String readEmailBody(String filename, User user) {
		
		StringBuffer sb= new StringBuffer();
		try (Stream<String> lines= Files.lines(Paths.get(filename))){
			lines.forEach(line-> {
				line=line.replace("${FNAME}",user.getFname());
				line=line.replace("${LNAME}",user.getLname());
				line=line.replace("${TEMP_PWD}",user.getUserpwd());
				line=line.replace("${EMAIL}",user.getEmail());
				line=line.replace("${EMAIL}",user.getEmail());
				
				sb.append(line);
			});
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	


}
