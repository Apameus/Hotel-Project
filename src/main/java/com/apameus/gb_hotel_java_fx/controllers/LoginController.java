package com.apameus.gb_hotel_java_fx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public final class LoginController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label notificationLabel;


    public void login() throws IOException {
        if (usernameField.getText().equals("Apameus") && passwordField.getText().equals("Apameus123")){

            notificationLabel.setText("Log-in Successfully!");

            root = FXMLLoader.load(getClass().getResource("employee2.fxml"));
            stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
        else if (usernameField.getText().isEmpty() || passwordField.getText().isBlank()){
            notificationLabel.setText("Both fields must be fulfilled!");
        }
        else {
            notificationLabel.setText("Wrong info!");
        }
    }

    public void register(){
    }
}
