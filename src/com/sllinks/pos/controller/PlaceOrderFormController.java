package com.sllinks.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PlaceOrderFormController {
    public AnchorPane placeOrderFormContext;

    public void btnBackToDashboard(ActionEvent actionEvent) throws IOException {
        setUi("Dashboard","Dashboard");
    }

    private void setUi(String location, String title) throws IOException {
        Stage window = (Stage) placeOrderFormContext.getScene().getWindow();
        window.setScene( new Scene(FXMLLoader.load(getClass().getResource("../views/"+location+".fxml"))));
        window.setTitle(title);
    }
}
