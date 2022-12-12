package com.sllinks.pos.controller;

import com.sllinks.pos.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ProductFormController {


    public AnchorPane productFormContext;

    private void setUi(String location, String title) throws IOException {
        Stage window = (Stage) productFormContext.getScene().getWindow();
        window.setScene( new Scene(FXMLLoader.load(getClass().getResource("../views/"+location+".fxml"))));
        window.setTitle(title);
    }

    public void btnBackToDashboard(ActionEvent actionEvent) throws IOException {
        setUi("Dashboard","Dashboard");
    }
}
