package com.company.Controller;

import com.company.Configs.Config;
import com.company.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AdminController {
    Config config;

    public AdminController() {
        config = new Config("jdbc:mysql://localhost/yc-talents", "root", "");
    }


    public List<User> findAllUsers() throws SQLException {

        String query = "SELECT * FROM admin";
        Statement statement = config.connection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {

            System.out.println(resultSet.getString("password"));

        }


        return null;

    }

}