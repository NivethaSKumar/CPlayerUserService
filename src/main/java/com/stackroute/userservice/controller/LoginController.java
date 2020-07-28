package com.stackroute.userservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.userservice.dto.ResponseDTO;
import com.stackroute.userservice.dto.UserDTO;
import com.stackroute.userservice.service.IUserService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/players")
@Api
public class LoginController {
	static final long EXPIRATIONTIME = 90000000;
	Map<String, String> map = new HashMap<>();
	Map<String, String> loginMap = new HashMap<>();
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = "/login" , method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO check(@RequestBody UserDTO userDto) {
		ResponseDTO message = userService.userLogin(userDto);
		return message;
	}
	
	@RequestMapping(value = "/register" , method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO registerNewUser(@RequestBody UserDTO userDto) {
		ResponseDTO message = userService.newUser(userDto);
		return message;
	}
}
