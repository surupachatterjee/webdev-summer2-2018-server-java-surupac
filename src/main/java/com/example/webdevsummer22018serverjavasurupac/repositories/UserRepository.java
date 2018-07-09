package com.example.webdevsummer22018serverjavasurupac.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.webdevsummer22018serverjavasurupac.models.User;

public interface UserRepository extends CrudRepository<User,Integer> {
	@Query("SELECT u FROM User u WHERE u.username=:username")
	Iterable<User> findUserByUserName(
		@Param("username") String username); 	

}
