package com.company;

import java.sql.SQLException;
import java.util.Scanner;

import com.company.Controller.UserController;
import com.company.models.User;

public class Main {

    public static void main(String[] args) throws Exception {
	// write your code here

      UserController userController = new UserController();
      Scanner scanner = new Scanner(System.in);
      
      boolean var = true;
      
      while(var) {
    	  /* Depart de l'application
    	   * ------------ Menu Principal --------------
    	   * --->> la gestion User
    	   * --->> la gestion Admin
    	   * --->> la gestion Participant
    	   * */
    	  
    	  System.out.println("========== Menu de les gestion programmes ============");
    	  System.out.println("(1) Gestion User");
    	  
    	  
    	  int choix = scanner.nextInt();
    	  
    	  switch(choix) {
    	  case 1:
    		  boolean variable1 = true;
    		  while(variable1) {
    			  System.out.println("(1) Add User");
    			  System.out.println("(2) End");
    			  System.out.println("(2) Choix invalid");
    			  
    			  
    			  System.out.println("Choix : ");
    			  
    			  int choixUser = scanner.nextInt();
    			  
    			  switch(choixUser) {
    			  case 1:
    				  System.out.println("Add id :");
    				  long id = scanner.nextLong();
    				  
    				  System.out.println("Add first name :");
    				  String first_name = scanner.next();
    				  
    				  System.out.println("Add last name :");
    				  String last_name = scanner.next();
    				  
    				  System.out.println("Add email :");
    				  String email = scanner.next();
    				  
    				  System.out.println("Add phone :");
    				  String phone = scanner.next();
    				  
    				  try {
    					  userController.addUser(new User(id,first_name, last_name, email, phone));
    				  }catch(SQLException ex) {
    					  ex.printStackTrace();
    				  }
    				  
    				  break;
    				  
    			  case 2:
    				  System.out.println("End");
    				  variable1 = false;
    				  var = true;
    				  break;
    				  
    				  default: 
    					  System.out.println("Choix Invalid !!!!");
    					  break;
  
    			  }
    		  }
    	  }
      }
    }
}