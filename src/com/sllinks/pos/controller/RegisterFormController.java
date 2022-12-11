package com.sllinks.pos.controller;

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

import javax.jws.soap.SOAPBinding;
import java.io.IOException;

public class RegisterFormController {
    public TextField txtUserName;
    public PasswordField txtPassword;
    public AnchorPane registerFormContext;
    public PasswordField txtReEnterPassword;
    public TextField txtEmail;
    public TextField txtContact;

    public void signUpOnAction(ActionEvent actionEvent) throws InterruptedException, IOException {
        String realPwd=txtPassword.getText().trim();
        String matchPwd=txtReEnterPassword.getText().trim();
        if(realPwd.isEmpty()&&matchPwd.isEmpty()){
            new Alert(Alert.AlertType.WARNING,"Fill the empty password field!").show();
            return;
        }
        if(!realPwd.equals(matchPwd)){
            new Alert(Alert.AlertType.WARNING,"Each password are not matched!").show();
            txtPassword.clear();
            txtReEnterPassword.clear();
            return;
        }

        User user = new User(txtEmail.getText().trim(),txtUserName.getText().trim(),txtContact.getText().trim(),realPwd);

        if(saveUser(user)){
            new Alert(Alert.AlertType.CONFIRMATION,"Already Registered!").show();
            clearFields();
            Thread.sleep(2000);

             }
        else {
            new Alert(Alert.AlertType.WARNING,"Email has been registered Try Again!").show();
            txtEmail.clear();
            }
    }

    private boolean saveUser(User usr) {

        for (User tempUser:Database.userTable){
            if(tempUser.getEmail().equals(usr.getEmail())){
                return false;
            }

        }
        return Database.userTable.add(usr);

    }

    private void clearFields(){
        txtContact.clear();
        txtPassword.clear();
        txtUserName.clear();
        txtEmail.clear();
        txtReEnterPassword.clear();
    }

    public void btnAlreadyHaveAnAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUi("LoginForm","Login Form");
    }

    private void setUi(String location,String title) throws IOException {
        Stage window = (Stage) registerFormContext.getScene().getWindow();
        window.setScene( new Scene(FXMLLoader.load(getClass().getResource("../views/"+location+".fxml"))));
        window.setTitle(title);
    }
}
