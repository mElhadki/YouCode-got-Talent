package com.company.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.company.Configs.Config;

import java.sql.SQLException;
import java.util.List;

public class Admin {

    Config config;

    public Admin() {
        config = new Config("jdbc:mysql://localhost/yc-talents", "root", "");
    }


    public List<User> findAllUsers() throws SQLException {

        String query = "SELECT * FROM yc-talents";
        Statement statement = config.connection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {

            System.out.println(resultSet.getString(""));

        }


        return null;

    }
}