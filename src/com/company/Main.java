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
    	 System.out.println("(2) Connect Admin");
    	  
    	  
    	  int choix = scanner.nextInt();
    	  
    	  switch(choix) {
    	  case 1:
    		  boolean variable1 = true;
    		  while(variable1) {
    			  System.out.println("(1) Add User");
    			  System.out.println("(2) find User by Id");
    			  System.out.println("(3) update User");
    			  System.out.println("(4) create participation the user");
    			  System.out.println("(5) End");
    			  System.out.println("(6) Choix invalid");
    			  
    			  
    			  System.out.println("Choix : ");
    			  
    			  int choixUser = scanner.nextInt();
    			  
    			  switch(choixUser) {
    			  case 1:
    				 
    				  
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
    		  break;
    		  
    	  case 2:
    		  boolean variable2 = true;
    		  while(variable2) {
    			  System.out.println("(1) Connect Admin");
    			  System.out.println("(2) find All User");
    			  System.out.println("(3) find participation");
    			  System.out.println("(4) find particiapation by user email");
    			  System.out.println("(5) validate particiapation");
    			  
    			  
    			  System.out.println("Choix :");
    			  
    			  int choixAdmin = scanner.nextInt();
    			  
    			  switch(choixAdmin) {
    			  case 1:
    				  break;
    				  
    				  
    			  }
    		  }
    	  }
      }
    }
}