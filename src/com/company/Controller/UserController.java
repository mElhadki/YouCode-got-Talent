package com.company.Controller;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.company.Configs.Config;
import com.company.models.User;

public class UserController  {

    Config config;
    Scanner scanner;
    User user;

    public UserController() {
        config = new Config("jdbc:mysql://localhost/yc-talents","root","");
        scanner = new Scanner(System.in);
        user = new User();
    }

    public Boolean idCheck(Long id) throws SQLException {
        Long idCheckerLong = null;
        Boolean errorIdString = false;
        String idCheckString = "SELECT * FROM user WHERE id="+id+"";
        Statement stmt = config.connection().createStatement();
        ResultSet resultSet = stmt.executeQuery(idCheckString);
        while(resultSet.next()) {
            idCheckerLong = resultSet.getLong("id");
        }
        if(idCheckerLong != null) {
            errorIdString = true;
        }
        return errorIdString;
    }

    public ArrayList<String> validator() throws SQLException {

        ArrayList<String> validators = new ArrayList<String>();


        String regexEmail = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$";
        Pattern patternEmail = Pattern.compile(regexEmail);
        Matcher matcherEmail = patternEmail.matcher(user.getEmail());

        String regexPhone = "(\\+212|0)([ \\-_/]*)(\\d[ \\-_/]*){9}";
        Pattern patternPhone = Pattern.compile(regexPhone);
        Matcher matcherPhone= patternPhone.matcher(user.getPhone());





        if(user.getId().toString().length() < 5) {
            validators.add("the id is too short\n");
        }
        if(user.getFirst_name().length() < 5) {
            validators.add("first name is too short\n");

        }
        if(user.getLast_name().length() < 5) {
            validators.add("the last name is too short\n");

        }
        if(user.getEmail().length() < 10) {
            validators.add("the email is too short\n");
        }
        else if(matcherEmail.matches() == false) {
            validators.add("email format invalid\n");
        }
        if(user.getPhone().length() < 10) {
            validators.add("phone number is too short\n");
        }
        else if(matcherPhone.matches() == false) {
            validators.add("phone format invalid\n");
        }
        return validators;
    }


    public void add() throws SQLException {
        System.out.println("Enter your id");
        user.setId(scanner.nextLong());
        if(idCheck(user.getId()) == false ) {
            System.out.println("Enter your first Name");
            user.setFirst_name(scanner.next());
            System.out.println("Enter your last name");
            user.setLast_name(scanner.next());
            System.out.println("Enter your email");
            user.setEmail(scanner.next());
            System.out.println("Enter your phone");
            user.setPhone(scanner.next());

            if (validator().size() == 0) {
                String sqlString = "INSERT into user (id, first_name, last_name, email, phone)" + " values(?,?,?,?,?)";
                java.sql.PreparedStatement statement = config.connection().prepareStatement(sqlString);
                statement.setLong(1, user.getId());
                statement.setString(2, user.getFirst_name());
                statement.setString(3, user.getLast_name());
                statement.setString(4, user.getEmail());
                statement.setString(5, user.getPhone());
                statement.executeUpdate();

                System.out.println("You are added");
            }
            else {
                for(int i = 0; i < validator().size(); i++)
                {
                    System.out.println(validator().get(i));

                }
                add();
            }
        }

        else {
            // to not print null in console if id not exist
            if(idCheck(user.getId()) == true) {
                System.out.println("the id already exists\n");
                add();
            }



        }

    }

    public void update() throws SQLException {

        System.out.println("Enter your id");
        user.setId(scanner.nextLong());
        if(idCheck(user.getId()) == true) {
            System.out.println("Enter your first Name");
            user.setFirst_name(scanner.next());
            System.out.println("Enter your last name");
            user.setLast_name(scanner.next());
            System.out.println("Enter your email");
            user.setEmail(scanner.next());
            System.out.println("Enter your phone");
            user.setPhone(scanner.next());
            if(validator().size() == 0) {
                String sqlString = "UPDATE user"+" SET first_name=?, last_name=?, email=?, phone=?" + "WHERE id=?"; // create the Sql query
                java.sql.PreparedStatement stmt = config.connection().prepareStatement(sqlString);
                stmt.setString(1, user.getFirst_name());
                stmt.setString(2, user.getLast_name());
                stmt.setString(3, user.getEmail());
                stmt.setString(4, user.getPhone());
                stmt.setLong(5, user.getId());
                stmt.executeUpdate();
                System.out.println("You are updated user "+user.getFirst_name()+" "+user.getLast_name());

            }
            else {
                if(validator().size() > 0) {
                    for(int i = 0; i < validator().size(); i++)
                    {
                        System.out.println(validator().get(i));
                    }
                    update();
                }
            }
        }


        else {

            if(idCheck(user.getId()) == false) {
                System.out.println("the id is not exist in DB");
                update();
            }



        }

    }

    public void searchById() throws SQLException {

        System.out.println("Enter Your Id");
        user.setId(scanner.nextLong());
        if(idCheck(user.getId())== false) {

            System.out.println("The id doesn't exist");
            searchById();
        }

        else {

            String requete = "SELECT * FROM user WHERE id="+user.getId()+"";
            Statement stmt = config.connection().createStatement();
            ResultSet resultSet = stmt.executeQuery(requete);

            while(resultSet.next()) {

                user.setId(resultSet.getLong("id"));
                user.setFirst_name(resultSet.getString("first_name"));
                user.setLast_name(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));

            }

            System.out.println(user.toString());

        }


    }

}