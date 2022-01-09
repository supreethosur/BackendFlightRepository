//package com.example.Service;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class JwtUserDetailsService implements UserDetailsService {
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//		HashMap<String,String> credentials=findUserName(username);
////		if ("javainuse".equals(username)) {
////			return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
////					new ArrayList<>());
//		if (credentials!=null) { 
//			return new User(username,   credentials.get(username) ,
//					new ArrayList<>());
//		} else {
//			throw new UsernameNotFoundException("User not found with username: " + username);
//		}
//	}
//
//	private HashMap<String, String> findUserName(String username) {
//		HashMap<String,String> userCredentials=new HashMap<>();
//		PasswordEncoder encoder=new BCryptPasswordEncoder();
//		HashMap<String,String> userData=new HashMap<>();
//		userCredentials.put("admin1", encoder.encode("admin1pwd"));
//		userCredentials.put("admin2", encoder.encode("admin2pwd"));
//		if(userCredentials.get(username)!=null) {
//			userData.put(username, userCredentials.get(username));
//			return userData;
//		}
//		return null;
//	}
//	
//	
//	
//	
//	
//}
