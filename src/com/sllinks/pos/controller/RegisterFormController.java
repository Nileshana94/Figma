package com.sllinks.pos.controller;

import com.sllinks.pos.model.User;
import db.Database;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javax.jws.soap.SOAPBinding;

public class RegisterFormController {
    public TextField txtUserName;
    public PasswordField txtPassword;
    public AnchorPane registerFormContext;
    public PasswordField txtReEnterPassword;
    public TextField txtEmail;
    public TextField txtContact;

    public void signUpOnAction(ActionEvent actionEvent) {
        String realPwd=txtPassword.getText().trim();
        String matchPwd=txtReEnterPassword.getText().trim();

        if(!realPwd.equals(matchPwd)){
            new Alert(Alert.AlertType.WARNING,"Each password are not matched!").show();
            return;
        }

        User user = new User(txtEmail.getText().trim(),txtUserName.getText().trim(),txtContact.getText().trim(),realPwd);

        if(saveUser(user)){
            new Alert(Alert.AlertType.CONFIRMATION,"User has been Registered Safely!").show();
            clearFields();
        }
        else {
            new Alert(Alert.AlertType.WARNING,"Try Again! Error Code-> 404").show();
        }
    }
    private boolean saveUser(User usr){
        return Database.userTable.add(usr);
    }

    private void clearFields(){
        txtContact.clear();
        txtPassword.clear();
        txtUserName.clear();
        txtEmail.clear();
        txtReEnterPassword.clear();
    }
}
