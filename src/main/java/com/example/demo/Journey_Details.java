package com.example.demo;

import javafx.scene.control.ChoiceBox;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.PrimitiveIterator;
import java.util.ResourceBundle;

public class Journey_Details {

    String name1;
    String age;
    String phoneno;
    String email;


    @FXML
    private Button BackButton;

    @FXML
    public TextField FromLabel;

    @FXML
    private TextField ToLabel;



    @FXML
    private DatePicker DateLabel;

    @FXML
    private Button nextButton;

    @FXML
    private Label nodetailsLabel;

LocalDate time;

@FXML
private TextField nooftickets;
@FXML

public void getDate(ActionEvent event)
{
    try {
        time = DateLabel.getValue();
    }
    catch (Exception e)
    {
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Invalid Date");
        alert.show();
    }
}

//
//@FXML
//private ChoiceBox<String> myChoiceBox;
//
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        myChoiceBox.getItems().add("First");
//    }
    public void backButtonOnAction(ActionEvent e) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("page.fxml"));
        Stage window=(Stage) BackButton.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load(), 600, 400));
    }

    public void nextButtonOnAction(ActionEvent e) throws Exception
    {
        try {
            if (FromLabel.getText().isBlank() == false && ToLabel.getText().isBlank() == false && time != null && nooftickets.getText().isBlank() == false){

                String databaseName = "airline";
                String databaseUser = "root";
                String databasePassword = "root";
                String url = "jdbc:mysql://localhost/" + databaseName;

                Class.forName("com.mysql.cj.jdbc.Driver");

                Connection con = DriverManager.getConnection(url, databaseUser, databasePassword);


                Statement statement = con.createStatement();


                String details = "INSERT INTO airline.journeydetails (Fromplace,Toplace,Date,nooftickets) VALUES ('" + FromLabel.getText() + "','" + ToLabel.getText()+ "','" + time  + "','" + nooftickets.getText() + "');";

                statement.executeUpdate(details);



                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("submit.fxml"));
                Stage window = (Stage) nextButton.getScene().getWindow();
                window.setScene(new Scene(fxmlLoader.load(), 600, 400));
            } else {
                nodetailsLabel.setText("Please enter the Details");

                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please fill the details");
                alert.show();
            }
        }
        catch (Exception ae)
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid entry");
            alert.show();

        }
    }




}

