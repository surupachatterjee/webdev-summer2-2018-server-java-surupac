package com.example.webdevsummer22018serverjavasurupac.services;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND)
class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UserNotFoundException(String userId) {
			super("could not find user '" + userId + "'.");
		}

}
	