package com.company.Controller;

import com.company.Configs.Config;
import com.company.models.Participant;
import com.company.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminController {
    Config config;
   Scanner scanner = new Scanner(System.in);
    public AdminController() throws SQLException {
        config = new Config("jdbc:mysql://localhost/yc-talents", "root", "");
    }


    public ArrayList<User> findAllUsers() throws SQLException {
        ArrayList<User> userArrayList = new ArrayList<>();

        String query = "SELECT * FROM admin";
        PreparedStatement preparedStatement = config.connection().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
           User user = new User(resultSet.getLong("id"), resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("phone")) ;
           userArrayList.add(user);
        }
        for(User list: userArrayList){
            System.out.println(list.toString());
        }

        return userArrayList;

    }

    public void adminConnect() throws SQLException {
        String query = "Update adminsession SET is_connected = true WHERE id_admin=15970010";
        PreparedStatement statement = config.connection().prepareStatement(query);
        statement.executeUpdate();
        System.out.println("login admin is success");
    }

    public List<Participant> findParticipant() throws SQLException {
        String query = "SELECT * FROM participant";
        Statement statement = config.connection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while(resultSet.next()){
            System.out.println(resultSet.getString("id_user"));
            System.out.println(resultSet.getString("id_category"));
            System.out.println(resultSet.getString("description"));
            System.out.println(resultSet.getString("show_start_time"));
        }

        return null;
    }

    public Participant findParticipantByUserEmail() {
        System.out.println("Email :");
        String email = scanner.next();
        Participant participant = new Participant();

        try {
            String query = "SELECT prt.id_user FROM participant prt user us WHERE prt.id_user = us.id_user AND us.email = '" + email + "'";
            PreparedStatement prestmt = config.connection().prepareStatement(query);
            ResultSet resultSet = prestmt.executeQuery(query);

            while (resultSet.next()) {
                System.out.println(resultSet.getString("id_user"));
            }
            
           
        } catch (Exception ex) {
            ex.getMessage();
        }

        return null;

    }
    
    public Participant validateParicipant(int UserId) {
    	
        System.out.println("Enter id paricipation :");
        long user_id = scanner.nextLong();
        
    	try {
    	String query = "UPDATE participation SET is_accepted=TRUE WHERE user_id =?";
    	PreparedStatement statement = config.connection().prepareStatement(query);
    	statement.setLong(1, UserId);
    	 System.out.println("this participation is accepted");
    	statement.execute();
    	statement.close();
    	
    }catch(Exception e) {
    	System.out.println("Got an exception !");
    	System.out.println(e.getMessage());
    	
    }
 
    	return null;
    }
}