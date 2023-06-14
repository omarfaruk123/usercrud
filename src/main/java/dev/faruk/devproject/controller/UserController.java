package dev.faruk.devproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;

import dev.faruk.devproject.dto.UserCreateDto;
import dev.faruk.devproject.dto.UserDto;
import dev.faruk.devproject.dto.UserResponseDto;
import dev.faruk.devproject.model.User;
// import dev.faruk.devproject.service.UserService;
import dev.faruk.devproject.service.impl.UserServiceImpl;

//v ui
import org.springframework.web.servlet.ModelAndView;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin("*")
// @RestController
@Controller
// @RequestMapping("/api")

public class UserController {
	
	
	private final UserServiceImpl userService;
	
	@Autowired
	public UserController(UserServiceImpl userService) {
		this.userService = userService;
	}

	@GetMapping(value = {"/", "/home"})
    ModelAndView getHome(){
        //Calculating today
        SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("dd-MMM-yyyy, E \nhh:mm:ss aa ");
        dateTimeInGMT.setTimeZone(TimeZone.getTimeZone("GMT"));
        String today = dateTimeInGMT.format(new Date());

        //Creating model
        ModelAndView mv = new ModelAndView();
        mv.addObject("today", today);
        mv.setViewName("index.html");

        return mv;
    }

	@GetMapping(value = "/add-user")
    ModelAndView getAddUserForm(User user){
		ModelAndView mv = new ModelAndView();
        mv.setViewName("add-movie.html");

        return mv;
        // return "add-movie.html";
    }
	
	@GetMapping
	public ResponseEntity<?> getAllUsers(){
		List<UserDto> users = userService.getAllUsers();
		return  ResponseEntity.ok().body(users);
	}
	
	@PostMapping(value="/user")
	public ResponseEntity<?> saveUser(@RequestBody UserCreateDto user){
		UserResponseDto userResponseDto;
		try {
			User newuser = userService.saveUser(user);
			if (newuser != null) {
				userResponseDto = new UserResponseDto(newuser, true, "User Created Successfully");
				return ResponseEntity.ok().body(userResponseDto);
			}
			userResponseDto = new UserResponseDto(null, false, "Failed to create User");

			return ResponseEntity.badRequest().body(userResponseDto);
		}catch(Exception e) {
			userResponseDto = new UserResponseDto(null, false, "Failed to create User");

			return ResponseEntity.badRequest().body(userResponseDto);
		}
	}
	
}
