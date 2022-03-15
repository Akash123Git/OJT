package com.working.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.working.db.DbConn;
import com.working.product.User;


public class UserDao {

	public User GetUserDetails(long userId, String password) throws Exception {
		
		
		try {
			DbConn db = new DbConn();
			Connection c = db.Get_Connection();
			PreparedStatement ps = c.prepareStatement("SELECT * from user_details where user_id=? and password=?");
			
			ps.setLong(1,userId);
			ps.setString(2,password);
			ResultSet rs = ps.executeQuery();
			User u=new User();
			while (rs.next()) {
				
				u.setUser_id(Long.parseLong(rs.getString("user_id")));
				u.setUser_name(rs.getString("user_name"));
				u.setFull_name(rs.getString("full_name"));
				u.setPassword(rs.getString("password"));
				u.setDate_of_birth(rs.getString("date_of_birth"));
				}
			
			
			c.close();
			if((u.getUser_id()==0)) {
				return null;
			}
			
			
			return u;
		} catch (Exception e) {
			return null;
		}
	}
	
	public User addUser(User u) {
		
		try {
			DbConn db = new DbConn();
			Connection c = db.Get_Connection();
			PreparedStatement ps = c.prepareStatement("insert into user_details (user_id,full_name,user_name,password,date_of_birth) values(?,?,?,?,?)");
			
			ps.setLong(1,u.getUser_id());
			ps.setString(2,u.getFull_name());
			ps.setString(3,u.getUser_name());
			ps.setString(4,u.getPassword());
			ps.setString(5, u.getDate_of_birth());
			
			try{
				ps.executeUpdate();
			}catch(Exception e) {
				System.out.println(e.getMessage());
				return null;
			}
			c.close();
			u.setPassword("*******");
			return u;
		} catch (Exception e) {
			return null;
		}
	}
}