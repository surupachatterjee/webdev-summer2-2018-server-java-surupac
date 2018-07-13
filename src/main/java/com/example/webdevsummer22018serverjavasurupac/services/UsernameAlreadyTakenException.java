package com.example.webdevsummer22018serverjavasurupac.services;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(org.springframework.http.HttpStatus.CONFLICT)
public class UsernameAlreadyTakenException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UsernameAlreadyTakenException(String userId) {
			super("Username already taken '" + userId + "'.");
		}

}
