package dev.faruk.devproject.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dev.faruk.devproject.dto.UserCreateDto;
import dev.faruk.devproject.dto.UserDto;
import dev.faruk.devproject.model.User;
import dev.faruk.devproject.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
@Component

public class UserServiceImpl {
	@Autowired
	 UserRepository userRepository;
	
	public List<UserDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map(UserDto::new ).collect(Collectors.toList());
	}

	public User saveUser(UserCreateDto user) {
		User newUser = new User();
		newUser.setFirstName(user.getFirstName()); 
		newUser.setLastName(user.getLastName());
		newUser.setFullName(user.getFullName());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		userRepository.save(newUser);
		return newUser;
	}

}
