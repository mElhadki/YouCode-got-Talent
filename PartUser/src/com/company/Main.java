package com.company;

import com.company.Controller.ParticipantController;
import com.company.Controller.UserController;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws Exception {
    	
	// write your code here
    	
    	Scanner scanner = new Scanner(System.in);
    	UserController userController = new UserController();
    	ParticipantController participantcontroller = new ParticipantController();
    
    	
    	
    	 boolean var = true;

         while(var) {
        	 

             System.out.println("========== Menu de les gestion programmes ============");
             System.out.println("(1) User");
             System.out.println("(2) Admin");


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
                            	 
                            	 userController.add();
                                       
                                 break;
                                 
                             case 2: 
                            	 
                            	 userController.searchById();
                            	 break;
                            	 
                             case 3: 
                            	 
                            	 userController.update();
                            	 break;
                            	 
                             case 4:
                            	 
                            	 participantcontroller.addParticipant();
                            	 break;

                             case 5:
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

                     
                     //Part Admin
                     
                     
                 case 2:
                     boolean variable2 = true;
                     while(variable2) {
                         System.out.println("(1) Connect Admin");
                         System.out.println("(2) find All User");
                         System.out.println("(3) find participation");
                         System.out.println("(4) find particiapation by user email");
                         System.out.println("(5) validate particiapation");
                         System.out.println("(6) Loug out Admin");
                         
                         
                         System.out.println(" !!!!!!   Obliger Connecter Admin !!!!!!!!!!!");
                         System.out.println("Choix : ");
                         int choixAdmin = scanner.nextInt();
                         
                         switch(choixAdmin){
                             case 1:
                                 break;
                                 
                             default:
                                 break;
                         }
                     }
                     break;
                     
                 case 3 :
                     System.out.println("========== END ============");
                     System.out.println(0);
                     break;
                     
                 default:
                     System.out.println("Choix invalid !!!!!!!!!");
                     break;
             }
         }

     }
    	
    	

    }
