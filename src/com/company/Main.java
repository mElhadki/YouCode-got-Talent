package com.company;

import com.company.Controller.AdminController;
import com.company.Controller.UserController;
import com.company.Controller.ParticipantController;

import javax.mail.MessagingException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws SQLException, MessagingException {
        UserController userController = new UserController();
        AdminController adminController = new AdminController();
        ParticipantController participantController = new ParticipantController();
        
        
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
                       	 
                       	 userController.add();
                                  
                            break;
                            
                        case 2: 
                       	 
                       	 userController.searchById();
                       	 break;
                       	 
                        case 3: 
                       	 
                       	 userController.update();
                       	 break;
                       	 
                        case 4:
                       	 
                        	participantController.addParticipant();
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

                case 2:
                    boolean variable2 = true;
                    System.out.println(" !!!!!!   Obliger Connecter Admin !!!!!!!!!!!");
                    System.out.println("+++++++++++ Log in +++++++++++");
                    
                    System.out.println("Entrer votre Email :");
                    String email = scanner.next();
                    
                    System.out.println("Entrer votre password :");
                    String password = scanner.next();
                    

                    // Check If The Admin is Logged
                    if(email.equals("ayoubmousa30@gmail.com") && password.equals("ayoubgermany@@--@@")) {
                    	try {
                    		adminController.adminConnect();
                    	}catch(SQLException ex) {
                    		ex.printStackTrace();
                    	}
                    	
                    	 while(variable2) {
                             System.out.println("(1) find All User");
                             System.out.println("(2) find participation");
                             System.out.println("(3) find particiapation by user email");
                             System.out.println("(4) validate particiapation");
                             System.out.println("(5) Loug out Admin");
                             System.out.println("(6) End");
                             System.out.println("(7) Choix invalid");


                             System.out.println("Choix : ");
                             int choixAdmin = scanner.nextInt();

                             switch(choixAdmin){
                                 case 1:
                                	 try {
                                		 adminController.findAllUsers();
                                	 }catch(SQLException ex) {
                                		 ex.printStackTrace();
                                	 }
                                     break;
                                     
                                
                                	 
                                 case 2:
                                	try {
                                		 adminController.findParticipant();
                                	}catch(SQLException ex2) {
                                      ex2.printStackTrace();                                		
                                	}
                                	 break;
                                	 
                                 case 3:
								     adminController.findParticipantByUserEmail();
                                	 break;
                                 case 4:
                                     try {
                                             System.out.println("Please enter User Id \n");
                                             long userId = scanner.nextLong();
                                             System.out.println("Please enter Category Id \n");
                                             long idCategory = scanner.nextLong();

                                             adminController.validateParticipation(userId, idCategory);

                                       
                                     } catch (SQLException e) {
                                         e.printStackTrace();
                                     }
                                     break;
                                 case 5 :
                                	 adminController.adminLoggedOut();
                                	 break;
                                 case 6:
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