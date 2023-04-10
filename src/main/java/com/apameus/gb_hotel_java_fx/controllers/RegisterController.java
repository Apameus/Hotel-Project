package com.apameus.gb_hotel_java_fx.controllers;

import com.apameus.gb_hotel_java_fx.util.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {

    @FXML
    private Button backButton;

    @FXML
    private Button confirmButton;

    @FXML
    private PasswordField keyField;

    @FXML
    private Label notificationLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    void confirm(ActionEvent event) {

    }

    @FXML
    void goBack(ActionEvent event) {
        Util.changeScene("Login.fxml", backButton);
    }

}
