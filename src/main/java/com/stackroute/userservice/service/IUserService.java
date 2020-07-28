package com.stackroute.userservice.service;

import com.stackroute.userservice.dto.ResponseDTO;
import com.stackroute.userservice.dto.UserDTO;

public interface IUserService {

	ResponseDTO userLogin(UserDTO userDto);

	ResponseDTO newUser(UserDTO userDto);
}
