package com.stackroute.userservice.service;

import static org.mockito.MockitoAnnotations.initMocks;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.stackroute.userservice.domain.IUserDomain;
import com.stackroute.userservice.dto.ResponseDTO;
import com.stackroute.userservice.dto.UserDTO;

public class UserServiceImplTest {
	
	@Mock
	private IUserDomain userDomain;

	@InjectMocks
	private UserServiceImpl userServiceTest;

	@Before
	public void setUp() {
		initMocks(this);
	}
	
	@Test
	public void check()  {
		UserDTO payload = (UserDTO) createObjectWithData(UserDTO.class);
		
		ResponseDTO masterView = (ResponseDTO) createObjectWithData(ResponseDTO.class);
		
		Mockito.when(userDomain.userLogin(payload)).thenReturn(masterView);
		
		userServiceTest.userLogin(payload);
	}
	
	@Test
	public void registerNewUser() throws SQLException  {
UserDTO payload = (UserDTO) createObjectWithData(UserDTO.class);
		
		ResponseDTO masterView = (ResponseDTO) createObjectWithData(ResponseDTO.class);
		
		Mockito.when(userDomain.newUser(payload)).thenReturn(masterView);
		
		userServiceTest.newUser(payload);
	}
	
	
	@Test
	public void registerNewUserExp() throws SQLException  {
UserDTO payload = (UserDTO) createObjectWithData(UserDTO.class);
		
		ResponseDTO masterView = (ResponseDTO) createObjectWithData(ResponseDTO.class);
		
		Mockito.when(userDomain.newUser(null)).thenThrow(SQLException.class);
		
		userServiceTest.newUser(payload);
	}

	public Object createObjectWithData(Class<?> className) {
		Object obj = null;
		try {
			Class<?> classObj = className;
			obj = classObj.newInstance();
			Method[] methodArray = classObj.getMethods();
			if (methodArray != null && methodArray.length > 0) {
				for (Method method : methodArray) {
					if (method.getName().contains("set")) {
						Parameter[] parameters = method.getParameters();
						if (parameters != null && parameters.length > 0) {
							for (Parameter parameter : parameters) {
								Class<?> classType = parameter.getType();
								if (classType.equals(int.class) || classType.equals(Integer.class)) {
									classObj.getDeclaredMethod(method.getName(), classType).invoke(obj, 100);
								} else if (classType.equals(float.class) || classType.equals(Float.class)) {
									classObj.getDeclaredMethod(method.getName(), classType).invoke(obj, 100.25);
								} else if (classType.equals(double.class) || classType.equals(Double.class)) {
									classObj.getDeclaredMethod(method.getName(), classType).invoke(obj, 1000.25);
								} else if (classType.equals(boolean.class) || classType.equals(Boolean.class)) {
									classObj.getDeclaredMethod(method.getName(), classType).invoke(obj, true);
								} else if (classType.equals(short.class) || classType.equals(Short.class)) {
									classObj.getDeclaredMethod(method.getName(), classType).invoke(obj, (short) 1);
								} else if (classType.equals(long.class) || classType.equals(Long.class)) {
									classObj.getDeclaredMethod(method.getName(), classType).invoke(obj, 3L);
								} else if (classType.equals(char.class) || classType.equals(Character.class)) {
									classObj.getDeclaredMethod(method.getName(), classType).invoke(obj, 'Y');
								} else if (classType.equals(String.class)) {
									classObj.getDeclaredMethod(method.getName(), classType).invoke(obj, "testdata");
								} else if (classType.equals(Timestamp.class)) {
									classObj.getDeclaredMethod(method.getName(), classType).invoke(obj,
									    new Timestamp(1246041000000L));
								} else if (classType.equals(Date.class)) {
									classObj.getDeclaredMethod(method.getName(), classType).invoke(obj, new Date());
								} else if (classType.equals(java.sql.Date.class)) {
									classObj.getDeclaredMethod(method.getName(), classType).invoke(obj,
									    new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
								} else if (classType.equals(BigDecimal.class)) {
									classObj.getDeclaredMethod(method.getName(), classType).invoke(obj,
									    new BigDecimal(12));
								}

							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
}
