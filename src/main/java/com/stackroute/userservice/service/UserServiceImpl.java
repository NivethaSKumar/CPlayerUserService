package com.stackroute.userservice.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.userservice.domain.IUserDomain;
import com.stackroute.userservice.dto.ResponseDTO;
import com.stackroute.userservice.dto.UserDTO;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserDomain userDomain;
	
	public ResponseDTO userLogin(UserDTO userDto) {
		ResponseDTO message = userDomain.userLogin(userDto);
		return message;
	}

	@Override
	public ResponseDTO newUser(UserDTO userDto) {
		ResponseDTO message = new ResponseDTO();
		try {
			message = userDomain.newUser(userDto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			message.setResponse("SQL error while saving the data");
			return message;
		}
		return message;
	}
	
}
