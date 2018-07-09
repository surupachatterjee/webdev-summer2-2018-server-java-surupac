package com.example.webdevsummer22018serverjavasurupac.services;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer22018serverjavasurupac.models.User;
import com.example.webdevsummer22018serverjavasurupac.repositories.UserRepository;

@RestController
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/api/user")
	public Iterable<User> findAllUsers(){
		return userRepository.findAll();
	}
	
	
	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int id)
	{
		userRepository.deleteById(id);
	}
	
	@GetMapping("/api/user/{userId}")
	public User findUserById(@PathVariable("userId") int id)
	{
		Optional<User> usrdata = userRepository.findById(id);
		if(usrdata.isPresent())
		{
			return usrdata.get();
			
		}
		return null;
		
	}
	
	@PutMapping("/api/user/{userId}")
	public User updateUser(@PathVariable("userId") int id,@RequestBody User user) {
		Optional<User> usrdata = userRepository.findById(id);
		if(usrdata.isPresent())
		{
			
			User usr = usrdata.get();
			 System.out.println("User ID # " + usr.getId());
			usr.setFirstName(user.getFirstName());
			usr.setLastName(user.getLastName());
			usr.setPassword(user.getPassword());
			usr.setRole(user.getRole());
			usr.setUsername(user.getUsername());
			userRepository.save(usr);
			return usr;
	}
	return null;	
	}
	
}
