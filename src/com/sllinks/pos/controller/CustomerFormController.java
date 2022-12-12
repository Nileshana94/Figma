package com.sllinks.pos.controller;

import com.sllinks.pos.model.Customer;
import com.sllinks.pos.views.tm.CustomerTM;
import db.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;


public class CustomerFormController {
    public AnchorPane customerFormContext;
    public TextField txtCustomerID;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtSearchBar;
    public TextField txtCustomerSalary;
    public TableView<CustomerTM> tblCustomer;
    public TableColumn colCustomerID;
    public TableColumn colCustomerName;
    public TableColumn colCustomerAddress;
    public TableColumn colCustomerSalary;
    public TableColumn colOption;

    public void initialize(){
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCustomerSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        setTableData();
        setCustomerID();    }

    public void btnBackToDashboard(ActionEvent actionEvent) throws IOException {
        setUi("Dashboard","Dashboard");
    }
    private void setUi(String location, String title) throws IOException {
        Stage window = (Stage) customerFormContext.getScene().getWindow();
        window.setScene( new Scene(FXMLLoader.load(getClass().getResource("../views/"+location+".fxml"))));
        window.setTitle(title);
    }

    public void btnNewCustomerOnAction(ActionEvent actionEvent) {

    }

    public void saveUpdateOnAction(ActionEvent actionEvent) {
        Customer customer =new Customer(
                txtCustomerID.getText(),
                txtName.getText(),
                txtAddress.getText(),
                Double.parseDouble(txtCustomerSalary.getText())


        );
        if ( Database.customerTable.add(customer)){
            new Alert(Alert.AlertType.CONFIRMATION,"Customer Saved").show();
            setTableData();
            setCustomerID();
            clearFields();
        } else
            { new Alert(Alert.AlertType.CONFIRMATION,"Try Again").show();}

    }

    private void setTableData(){
        ArrayList<Customer> customerList =Database.customerTable;
        ObservableList<CustomerTM> obList= FXCollections.observableArrayList();

        for (Customer c:customerList
             ) {
            Button btn =new Button("Delete");
            CustomerTM customerTM = new CustomerTM(c.getId(),c.getName(),c.getAddress(),c.getSalary(),btn);
            obList.add(customerTM);
        }
        tblCustomer.setItems(obList);

    }

    private void clearFields(){
        txtName.clear();
        txtAddress.clear();
        txtCustomerSalary.clear();
    }

    private void setCustomerID(){
        if (!Database.customerTable.isEmpty()) {
            Customer c=Database.customerTable.get(Database.customerTable.size()-1);
            String id = c.getId();
            String dataArray[] = id.split("-");//["C","0001"]
            id =dataArray[1];//0001
            int oldNumber =Integer.parseInt(id);//1 remove zeros
            oldNumber++;

            if(oldNumber<9){
                txtCustomerID.setText("C-000"+oldNumber);
            }else if(oldNumber<99){
                txtCustomerID.setText("C-00"+oldNumber);
            }else if(oldNumber<999){
                txtCustomerID.setText("C-0"+oldNumber);
            }else {
                txtCustomerID.setText("C-"+oldNumber);
            }
        }else{
            txtCustomerID.setText("C-0001");
        }

    }
}
