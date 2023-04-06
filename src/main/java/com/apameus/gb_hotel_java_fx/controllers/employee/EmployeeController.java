package com.apameus.gb_hotel_java_fx.controllers.employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public final class EmployeeController {

    public Button newOrderButton;
    public Label dailyOrdersNumber;
    public Label TotalOrdersNumber;
    public Label pointsNumber;
    public Button logOutButton;
    public ImageView personImage;
    private Stage stage;
    private Parent root;

    public void newOrder(ActionEvent actionEvent) throws IOException {
        changeScene("newOrder.fxml");
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        changeScene("login.fxml");
    }

    private void changeScene(String name) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(name)));
        stage = (Stage) logOutButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
