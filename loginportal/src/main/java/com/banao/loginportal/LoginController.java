package com.banao.loginportal;

import java.util.List;
import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	@Autowired
	private EmailService emailService;

	@RequestMapping("/hell")
	public String sayhi() {
		return "hi";
	}
	
	@PostMapping("/signup")
	public String createUser(@RequestBody User s) {
		System.out.println(s.getFirstName());
		System.out.println(s.getFirstName());
		System.out.println(s.getFirstName());
		s.setUserType("user");
		s.setVerified("yes");
		this.loginService.postUser(s);
		return "success";
	}
	
	@PostMapping("/login")
	public String loginUser(@RequestBody User s) {
		System.out.println(s.getEmail());
		String st= this.loginService.findUserByEmail(s);
		System.out.println(st);
		return st;
		
	}
	
	@PostMapping("/homepage")
	public List<User> homepage(@RequestBody User s) {
		System.out.println(s.getFirstName());
		List<User> st= this.loginService.findUserByFirstName(s);
		System.out.println(st);
		
		return st;
		
	}
	
	@PostMapping("/verify")
	public String verify(@RequestBody User s) throws MessagingException {
		Random rand = new Random();
		String otp = String.format("%04d", rand.nextInt(10000));
		System.out.println(otp);
		String body ="Your OTP for verification is "+otp;
		System.out.println(s.getEmail());
//		emailService.sendEmail(s.getEmail(), "Verification Email", body);
//		emailService.sendHtmlEmail();
		emailService.sendSimpleEmail(s.getEmail(), body, "Verification Email");
		return otp;
	}
}
