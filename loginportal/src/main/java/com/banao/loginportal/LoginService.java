package com.banao.loginportal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

	@Autowired 
	private UserRepository userRepository;
	public List<User> getStudentUsers(){
		List<User> list = new ArrayList<>();
		for(User user: this.userRepository.findAll()) {
		    list.add(user);	
		}
		return list;
	}
	public void postUser(User s) {
		userRepository.save(s);
	}
	
	public String findUserByEmail(User s) {
		
		List<User> list1=userRepository.findByEmail(s.getEmail());
		if(list1.isEmpty()) {
			return "invalid email";
		}
		if(!list1.get(0).getPassword().equals(s.getPassword())) {
			return "invalid password";
		}
		return "authentication successful-"+list1.get(0).getFirstName();
	}
	
    public List<User> findUserByFirstName(User s) {
		
		List<User> list1=userRepository.findByFirstName(s.getFirstName());
		if("admin".equals(list1.get(0).getUserType())) {
			return userRepository.findAll();
		}
		return null;
	}
}
