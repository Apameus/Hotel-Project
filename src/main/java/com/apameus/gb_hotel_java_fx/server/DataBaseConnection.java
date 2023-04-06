package com.apameus.gb_hotel_java_fx.server;

import javafx.scene.control.Alert;

import java.sql.*;

public class DataBaseConnection {
    public Connection dataBaseLink;


    public Connection getConnection(){
        String dataBaseName = "";
        String dataBaseUser = "";
        String dataBasePassword = "";
        String url = "jdbc:mysql://localhost:3306/" + dataBaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dataBaseLink = DriverManager.getConnection(url, dataBaseUser, dataBasePassword);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return dataBaseLink;
    }


    /**
     * An example of successful connection with the database
     */
    public static void trialConnection(){

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "apameus123");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from employees");

            while (resultSet.next()){
                System.out.println(resultSet.getString("firstname"));
            }
        }catch (Exception e)  {
            e.printStackTrace();
        }
    }

    /**
     * Adds an employee to the database, if his username doesn't exist.
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     */
    public static void addEmployeeToDB(String username, String password, String firstName, String lastName){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement ps_CheckIfUsernameExist = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "apameus123");
            ps_CheckIfUsernameExist = connection.prepareStatement("SELECT * FROM employees WHERE username = ?");
            ps_CheckIfUsernameExist.setString(1, username);
            resultSet = ps_CheckIfUsernameExist.executeQuery();

            if (resultSet.isBeforeFirst()){
                System.err.println("Username already exist!");
                //
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You are not allowed to use this username!");
                alert.show();
            }
            else {
                preparedStatement = connection.prepareStatement("INSERT INTO employees (username, password, firstName, lastName) VALUES (?, ?, ?, ?)");
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, firstName);
                preparedStatement.setString(4, lastName);

                preparedStatement.executeQuery();
            }

        }catch (SQLException e){
            e.printStackTrace();
        } finally { // close the connection so you don't get memory leak
            // bad written code
            ifNullThenClose(resultSet);
            ifNullThenClose(ps_CheckIfUsernameExist);
            ifNullThenClose(preparedStatement);
            ifNullThenClose(connection);
        }
    }

    private static <T extends AutoCloseable> void ifNullThenClose(T type) {
        if (type != null){
            try {
                type.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
