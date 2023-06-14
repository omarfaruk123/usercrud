package dev.faruk.devproject.dto;

import dev.faruk.devproject.model.User;

public class UserResponseDto {

	private User user;
	private boolean success;
	private String message;
	public UserResponseDto(User user, boolean success, String message) {
		super();
		this.user = user;
		this.success = success;
		this.message = message;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
