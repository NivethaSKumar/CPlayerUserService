package com.stackroute.userservice.domain;

import java.sql.SQLException;

import com.stackroute.userservice.dto.ResponseDTO;
import com.stackroute.userservice.dto.UserDTO;

public interface IUserDomain {

	public ResponseDTO userLogin(UserDTO userDto);

	public ResponseDTO newUser(UserDTO userDto) throws SQLException;
}
