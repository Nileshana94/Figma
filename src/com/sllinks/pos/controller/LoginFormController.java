package com.sllinks.pos.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class LoginFormController {
    public JFXButton btnCreateAnAccount;
    public AnchorPane loginFormContext;


    public void btnCreateAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUi("RegisterForm");
    }

    private void setUi(String location) throws IOException {
        Stage window = (Stage) loginFormContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/"+location+".fxml"))));
    }



}
