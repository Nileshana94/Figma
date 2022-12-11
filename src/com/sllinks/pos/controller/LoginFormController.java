package com.sllinks.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.sllinks.pos.model.User;
import db.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class LoginFormController {
    public JFXButton btnCreateAnAccount;
    public AnchorPane loginFormContext;
    public TextField txtUserName;
    public PasswordField txtPassword;


    public void btnCreateAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUi("RegisterForm","Sign Up Form");
    }

    private void setUi(String location,String title) throws IOException {
        Stage window = (Stage) loginFormContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/"+location+".fxml"))));
        window.setTitle(title);
    }


    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        String usrName = txtUserName.getText().trim();
        String pswrd = txtPassword.getText().trim();

        for(User u: Database.userTable){
            if(u.getEmail().equals(usrName)){

                if(u.getPassword().equals(pswrd)){
                    setUi("Dashboard","Account: "+u.getEmail());
                }
                else {
                    new Alert(Alert.AlertType.WARNING,"Password is incorrect").show();
                    txtPassword.clear();
                }

                return;
            }
        }
        new Alert(Alert.AlertType.CONFIRMATION,"Can't find user details with this username.").show();

    }
}
