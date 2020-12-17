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
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class AdminController {
	


  Config config;
   Scanner scanner = new Scanner(System.in);
    public AdminController() throws SQLException {
        config = new Config("jdbc:mysql://localhost/yc-talents", "root", "");
    }
    
    public ArrayList<User> findAllUsers() throws SQLException {
        ArrayList<User> userArrayList = new ArrayList<>();

        String query = "SELECT * FROM user";
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
        statement.close();
        System.out.println("login admin is success");
    }

    public void adminLoggedOut() throws SQLException {
    String query = "UPDATE adminsession SET is_connected= FALSE WHERE id = 15970010";
    PreparedStatement statement = config.connection().prepareStatement(query);
    statement.executeUpdate();
    statement.close();
    System.out.println("Admin is logged out !!!!");
    }

    public List<Participant> findParticipant() throws SQLException {
        String query = "SELECT * FROM participation";
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
            String query = "SELECT prt.id_user FROM participation prt user us WHERE prt.id_user = us.id_user AND us.email = '" + email + "'";
            PreparedStatement prestmt = config.connection().prepareStatement(query);
            ResultSet resultSet = prestmt.executeQuery(query);

            while (resultSet.next()) {
                System.out.println(resultSet.getString("id_user"));
                System.out.println(resultSet.getString("description"));
            }
            
           
        } catch (Exception ex) {
            ex.getMessage();
        }

        return null;

    }

 

   public static void acceptedParticipation(String recepient) throws MessagingException{
   
    	Properties properties = new Properties();

       properties.put("mail.smtp.auth", "true");
       properties.put("mail.smtp.starttls.enable", "true");
       properties.put("mail.smtp.auth", "true");
       properties.put("mail.smtp.host", "smtp.gmail.com");
       properties.put("mail.smtp.port", "587");

    	
    	String myAccountEmail = "ayoubmousa30@gmail.com";
    	String password = "ayoubgermany@@--@@";
    	
    	Session session = Session.getInstance(properties, new Authenticator() {
    		@Override
    		protected PasswordAuthentication getPasswordAuthentication() {
    			return new PasswordAuthentication(myAccountEmail, password);
    		}
    	});
    	
    	
		Message message = prepareMessage(session, myAccountEmail, recepient);
    	
    	Transport.send(message);
    	System.out.println("Message sent successfully");

    }
    
    
 
    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
		// TODO Auto-generated method stub
    	
    	try {
    		Message message = new MimeMessage(session);
    		message.setFrom(new InternetAddress(myAccountEmail));
    		message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
    		message.setSubject("My First Email from Java App");
    		message.setText("Hey there, \n Look my email !");
    		return message;
    	}catch(Exception ex) {
    		Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
    	}
    	
    	return null;
	}


   /* public Participant validateParicipant(int UserId) {
    	
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
	*/
    

    public void validateParticipation(long id_user, long id_category) throws SQLException, MessagingException {

        // Check if id exist in table

        String query = "select * from participation  WHERE id_user = '" + id_user + "' AND id_category = '" + id_category
                + "'AND is_accepted = false ";
        PreparedStatement statement = config.connection().prepareStatement(query);
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.isBeforeFirst()) {

            String query2 = "UPDATE participation SET is_accepted = true WHERE id_user ='" + id_user + "'";
            PreparedStatement statement2 = config.connection().prepareStatement(query2);
            statement2.executeUpdate();
            System.out.println("User Accepted \n");

            // Get the user Email :D

            String query3 = "select * from user where id = '" + id_user + "' ";

            // Get user id from user table using the email

            PreparedStatement statement3 = config.connection().prepareStatement(query3);
            ResultSet resultSet3 = statement3.executeQuery(query3);
            String email = "";
            while (resultSet3.next()) {
                email = resultSet3.getString("email");

            }

            // Send mail to user

           
            acceptedParticipation(email);
        }

    }
	 
}