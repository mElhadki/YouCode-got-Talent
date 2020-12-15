package com.company.Controller;

import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;
import java.sql.PreparedStatement;

import com.company.Configs.Config;
import com.company.models.User;


public class UserController {
	
	Config config;
	
	Scanner scanner = new Scanner(System.in);
	
	User user = new User();
	public UserController() throws SQLException, ClassNotFoundException{
		config = new Config("jdbc:mysql://localhost/yc-talents","root", "");
	}
	
	public void addUser(User user) throws SQLException {
		
		System.out.println("add id:");
		long id = scanner.nextLong();
		
		System.out.println("add first name :");
		String firstName = scanner.next();
		
		System.out.println("add last name :");
		String lastName = scanner.next();
		
		System.out.println("add email :");
		String email = scanner.next();
		
		System.out.println("add phone : ");
		String phone = scanner.next();
		
		String query = "INSERT INTO user(id, first_name, last_name, email) values(?,?,?,?)";
		
		PreparedStatement statement = config.connection().prepareStatement(query);
		statement.setLong(1, id);
		statement.setString(2, firstName);
		statement.setString(3, lastName);
		statement.setString(4, email);
		statement.setString(5, phone);
		
		statement.executeLargeUpdate();
		
		System.out.println("user added");
		
	}
}
