package com.working.product;

import java.io.File;
import java.util.HashMap;

import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.apache.commons.io.FileUtils;

import com.working.dao.UserDao;

@Path("working")
public class UserResource {

	@POST
	@Path("/postrequest")
	public Response doUserDetails(User u) {
		
		UserDao u1 = new UserDao();
		
		String status = "";

		try {
			
			User userdetails = u1.GetUserDetails(u.getUser_id(),u.getPassword());
			if (userdetails != null) {
					
					userdetails.setPassword("********");
					
					return Response.ok(userdetails).build();


			} else {
				
				//checking property is true/false and registration of user
				status = PropertyStatusOfUserRegistration(u);
				
				if (status.trim().equals("data_error")) {
					
					return Response.status(Response.Status.BAD_REQUEST).entity("requires proper data for registration")
							.build();
				
				} else if (status.trim().equals("false")) {
					
					return Response.status(Response.Status.BAD_REQUEST).entity("enter proper user details").build();
				
				} else if(status.trim().equals("exception")) {
					
					return Response.status(Response.Status.BAD_REQUEST).entity("exception").build();
					
				} else {
					
					return Response.ok("user registered successfully").build();
				
				}
				
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			return Response.status(Response.Status.BAD_REQUEST).entity("Error message:" + e.getMessage()).build();

		}
		// add user allowed=true

	}
//
//	@POST
//	@Path("/addDetails")
//	public Response adduser(User u) {
//
//		String content;
//
//		String[] words = null;
//		boolean status = false;
//		User user = null;
//
//		UserDao check = new UserDao();
//
//		// Creating a file object
//		String path = "C:\\Users\\akash_anbalagan\\Desktop";
//		String fileName = "StatusCheck.txt";
//		File f = new File(path + "\\" + fileName);
//
//		// Checking the existence on file
//		if (!f.exists()) {
//			return Response.status(Response.Status.BAD_REQUEST).entity("Error Message: file does not exists").build();
//		}
//
//		try {
//
//			// Reading the file contents and checking for status
//			content = FileUtils.readFileToString(f);
//			words = content.split("=");
//			for (String word : words) {
//				if (word.equals("false")) {
//					status = true;
//					break;
//				}
//			}
//
//			// Fetching the user details
//			if (status == true) {
//				user = check.GetUserDetails(u.getUser_id());
//				user = null;
//			} else {
//				user = check.GetUserDetails(u.getUser_id());
//			}
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			return Response.status(Response.Status.BAD_REQUEST).entity("Error Message: " + e.getMessage()).build();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			return Response.status(Response.Status.BAD_REQUEST).entity("Error Message: " + e.getMessage()).build();
//		}
//
//		// Inserting the user details if not present
//		if (user != null) {
//			return Response.ok(user).build();
//		} else {
//			user = check.addUser(u);
//			if (user != null) {
//				return Response.ok("successfull registered").build();
//			} else {
//				return Response.status(Response.Status.BAD_REQUEST).entity("Error Message: Enter valid Details")
//						.build();
//			}
//
//		}
//	}
//

	public String PropertyStatusOfUserRegistration(User u) {
		
		String content;
		String[] words = null, lines = null;
		Map<String, String> hmap = new HashMap<>();
		
		User user = new User();
		UserDao userdao = new UserDao();
		
		String status = "";
		
		
		// Creating a file object
		String path = "C:\\Users\\akash_anbalagan\\Desktop";
		String fileName = "properties.txt";
		File f = new File(path + "\\" + fileName);

		// Checking the existence on file
		if (!f.exists()) {
			System.out.println("file does not exists");
		}

		try {
			
			// Reading the file contents and checking for status
			content = FileUtils.readFileToString(f);
			
			//extracting lines and words
			lines = content.split("\n");
			for (String line : lines) {
				words = line.split("=");
				hmap.put(words[0].trim(), words[1].trim());
			}
			
			if (hmap.containsValue("true")) {
				user.setUser_id(u.getUser_id());
				user.setPassword(u.getPassword());
				user.setFull_name(hmap.get("defaultFullName"));
				user.setUser_name(hmap.get("defaultUserName"));
				user.setDate_of_birth(hmap.get("defaultDateOfBirth"));
				user = userdao.addUser(user);
				
				if (user != null) {
					status = "true";
				} else {
					status = "data_error";
				}
			
			} else if (hmap.containsValue("false")) {	
				status = "false";
			}
			return status;
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			
			status="exception";
			
			return status;
		}
	}

}
