package com.working.product;

import javax.xml.bind.annotation.XmlRootElement;


public class User {

	long user_id;	
	String full_name;
	String user_name;
	String password;
	String date_of_birth;
	
	

	public User() {
		
	}
	
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public User(long user_id, String full_name, String user_name, String password, String date_of_birth) {
		super();
		this.user_id = user_id;
		this.full_name = full_name;
		this.user_name = user_name;
		this.password = password;
		this.date_of_birth = date_of_birth;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", full_name=" + full_name + ", user_name=" + user_name + ", password="
				+ password + ", date_of_birth=" + date_of_birth + "]";
	}
	
	
	
	
}
