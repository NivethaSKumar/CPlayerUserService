package com.stackroute.userservice.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.stackroute.userservice.dto.ResponseDTO;
import com.stackroute.userservice.dto.UserDTO;

@Component
public class UserDomainImpl implements IUserDomain{
//	  @PersistenceContext
//	  private EntityManager entityManager; 
	  
	public ResponseDTO userLogin(UserDTO userDto) {
		String message = "";
		ResponseDTO respone = new ResponseDTO();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn=getConnection();
		
//		String query = "select * from users where username=? and password=? ";
			String query = "select * from usersdtls where username=? and password=? ";
		ps=conn.prepareStatement(query);
		ps.setString(1, userDto.getUser());
		ps.setString(2, userDto.getPwd());
		ResultSet rs = ps.executeQuery();
		System.out.println("result :: "+rs.getRow());
		if(rs.next()) {
			message="Valid User. Login successful";
			respone.setResponse(message);
		}else {
			message="Invalid User";
			respone.setResponse(message);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return respone;
	}
	
//	private Connection getConnection() throws SQLException {
//
////		final Session session = (Session) entityManager.getDelegate();
////		final SessionFactoryImplementor sfi = (SessionFactoryImplementor) session.getSessionFactory();
////		return sfi.getSessionFactoryOptions().getServiceRegistry().getService(ConnectionProvider.class).getConnection();
//		Connection con=null;
//		try{  
//		Class.forName("com.mysql.jdbc.Driver");  
//		con=DriverManager.getConnection(  
//		"jdbc:mysql://localhost:3306/fse","root","root123");  
//		//here sonoo is database name, root is username and password  
//		Statement stmt=con.createStatement();  
//		ResultSet rs=stmt.executeQuery("select * from emp");  
//		while(rs.next())  
//		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
//		}catch(Exception e){ System.out.println(e);}  
//		}
//	return con;
	
	private Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
//		Connection con=DriverManager.getConnection(  
//		"jdbc:mysql://127.0.0.1:3306/FSE","root","root123");  
		Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys","SYSDB","root");
		return con;
	}

	@Override
	public ResponseDTO newUser(UserDTO userDto) throws SQLException {
//		String insertQuery = "insert into users (username,emailaddress,password) values (?,?,?)";
		String insertQuery = "insert into usersdtls (userid,username,emailaddress,password) values (null,?,?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		ResponseDTO res=new ResponseDTO();
		try {
			conn = getConnection();
			ps = conn.prepareStatement(insertQuery);
			ps.setString(1, userDto.getUser());
			ps.setString(2, userDto.getEmail());
			ps.setString(3, userDto.getPwd());
			ps.executeUpdate();

			ps.close();
			
			conn.close();
			res.setResponse("Db saved successfully");
		} catch (SQLException e) {
			throw new SQLException(); 
		} finally {
			ps.close();
			conn.close();
		}
		return res;
	}
}
