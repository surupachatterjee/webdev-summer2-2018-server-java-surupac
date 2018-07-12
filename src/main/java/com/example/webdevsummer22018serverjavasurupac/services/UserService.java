package com.example.webdevsummer22018serverjavasurupac.services;


import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
		System.out.println("Deleting user with ID :"+ id  );
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
			return userRepository.findById(id).get();
			}
		return null;	
		}
	
	
	public Iterable<User> findUserByUsername(@RequestParam(name="username",required=false) String username)
	{
		System.out.println("executing findUserByUsername");
			return  userRepository.findUserByUserName(username);		
	}
	
	@PostMapping("/api/register")
	public User register(@RequestBody User user,HttpSession session) {
		
		Iterable<User> currentUser = findUserByUsername(user.getUsername());
		if (!currentUser.iterator().hasNext())
		{
			System.out.println("executing register for user " + user.getUsername());
			User registeredUser = createUser(user);
			System.out.println("registered user# " + registeredUser.getId());
			session.setAttribute("currentUser", registeredUser);
			return userRepository.findById(registeredUser.getId()).get();
			//return registeredUser;
		}
		System.out.println("executing register for user " + currentUser.iterator().next().getId());
		return null;
	}
	
	@PostMapping("/api/login")
	public User login(@RequestBody User user,HttpSession session)
	{
		Iterable<User> currentUser = userRepository.findUserByCredentials(user.getUsername(), user.getPassword());
		if (currentUser.iterator().hasNext()){
			User curUser = currentUser.iterator().next();
			System.out.println("Logging In user " + curUser.getId());
			session.setAttribute("currentUser", curUser);
			return userRepository.findById(curUser.getId()).get();
			}
		return null;
		}
	
	
	@GetMapping("/api/profile")
	public User profile(HttpSession session) {
		User currentUser = (User) session.getAttribute("currentUser");
		System.out.println("Logging In user " + currentUser.getId());
		return userRepository.findById(currentUser.getId()).get();
	}
	
	@PutMapping("/api/profile")
	public User updateProfile(@RequestBody User user, HttpSession session) {
		User currentUser =  (User) session.getAttribute("currentUser");
		//currentUser.setFirstName(user.getFirstName());
		System.out.println("Current User id  : "+ currentUser.getId()) ;
		System.out.println("User before update  : "+ user.toString());
		user.setId(currentUser.getId());
		user.setPassword(currentUser.getPassword());
		System.out.println("User after update  : "+ user.toString()) ;
		userRepository.save(user);
		//return findUserById(user.getId()); 
		return userRepository.findById(user.getId()).get();
		
	}
	
	@PostMapping("/api/logout")
	public User logout(HttpSession session) {
		System.out.println("Logging out user :" + session.getAttribute("currentUser") );
		session.invalidate();
		return null;
		
	}
		
	
}