package com.company.Controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import com.company.Configs.Config;
import com.company.models.Participant;
import com.company.Controller.UserController;
import com.company.Enum.EnumError;



public class ParticipantController {
	
	
	Config config;
	Scanner scanner;
	Participant participant;
	UserController userController;
	
	public ParticipantController() {
		
		config = new Config("jdbc:mysql://localhost/yc-talents","root","");
		scanner = new Scanner(System.in);
		participant = new Participant();
		userController = new UserController();
	}
	
	//Method pour valid a date 
	
	public Boolean timeChecker(String time)
	{
	     SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");

		try {
            format.parse(time);
            return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}

	
	
	public void addParticipant() throws SQLException {
		System.out.println("Enter Your id");
		participant.setId_user(scanner.nextLong());
		if(userController.idCheck(participant.getId_user()) == true) {
			String idCheckString = "SELECT * FROM category";
			Statement stmt = config.connection().createStatement();
			ResultSet resultSet = stmt.executeQuery(idCheckString);
			String leftAlignFormat = "| %-15s | %-25s |%n";
			System.out.format("+-----------------+---------------------------+%n");
			System.out.format("|       ID        |         Category          |%n");
			System.out.format("+-----------------+---------------------------+%n");
			while(resultSet.next()) {
				
				System.out.format(leftAlignFormat, resultSet.getLong("id"), resultSet.getString("name"));
			}
			System.out.format("+-----------------+---------------------------+%n");
			
			System.out.println("please choose a category with id Number");
			participant.setId_category(scanner.nextLong());
			
			System.out.println("please Enter a description");
			participant.setDescription(scanner.next());
			
			
			System.out.println("please put show start time Ex: (2020-12-01)");
			String timeString = scanner.next() + " 00:00:00.000000";
			if(timeChecker(timeString) == true) {
				participant.setShow_start_time(Timestamp.valueOf(timeString));
				System.out.println("please put show end time Ex: (2020-12-01)");
				participant.setShow_end_time(Timestamp.valueOf(scanner.next() + " 00:00:00.000000"));
				System.out.println("please put the file path");
				participant.setAttached_file(scanner.next());
				participant.setIs_accepted(false);
				  String sqlString = "INSERT into participation (id_user, id_category, description, show_start_time, show_end_time, attached_file, is_accepted)" + " values(?,?,?,?,?,?,?)";
				  java.sql.PreparedStatement statement = config.connection().prepareStatement(sqlString);
					statement.setLong(1, participant.getId_user());
					statement.setLong(2, participant.getId_category());
					statement.setString(3, participant.getDescription());
					statement.setTimestamp(4, participant.getShow_start_time());
					statement.setTimestamp(5, participant.getShow_end_time());
					statement.setString(6, participant.getAttached_file());
					statement.setBoolean(7, participant.getIs_accepted());

					statement.executeUpdate();
				 
				System.out.println(EnumError.ADDPARTICIPATION);
			}
			else {
				
				System.out.println("error date");
				addParticipant();
			}
			
			
		}
		else {
			if(userController.idCheck(participant.getId_user()) == false) {
				System.out.println(EnumError.IDUSEREXIST);
				addParticipant();
			}
		}
		
	}
}