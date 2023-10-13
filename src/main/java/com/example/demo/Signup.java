package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.PrimitiveIterator;
public class Signup {

    @FXML
    private TextField FirstnameTextField;

    @FXML
    private TextField LastnameTextField;

    @FXML
    private TextField UsernameTextField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Button SignupButton;

    private Button LoginButton;

    @FXML
    private Label MessageLabel;

    //public String verifylogin;

    public void SignupButtonOnAction(ActionEvent e) throws Exception
    {

        if ( FirstnameTextField.getText().isBlank() == false && LastnameTextField.getText().isBlank() == false  && UsernameTextField.getText().isBlank() == false && PasswordField.getText().isBlank() == false) {

             validdataSignup();

        }
        else
        {
                  MessageLabel.setText("Please enter the given details");


            System.out.println("Please fill all Info");
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in all Info to Sign up!");
            alert.show();
        }

    }

    public void LoginButtonOnAction(ActionEvent e) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Stage window = (Stage) SignupButton.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load(), 600, 400));
    }
    public Connection databaseLink;
    public void validdataSignup() throws Exception
    {
//        DatabaseConnection connectNow=new DatabaseConnection();
//        Connection connectDB = connectNow.getConnection();


        try {

            String databaseName="airline";
            String databaseUser="root";
            String databasePassword="root";
            String url="jdbc:mysql://localhost/"+databaseName;

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con= DriverManager.getConnection(url,databaseUser,databasePassword);
           // databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);

            Statement statement = con.createStatement();
           // ResultSet queryResult = statement.executeQuery(verifyLogin);

            String verifyLogin ="INSERT INTO airline.useraccounts (firstname,lastname,username,password) VALUES ('"+FirstnameTextField.getText()+"','"+LastnameTextField.getText()+"','"+UsernameTextField.getText()+"','"+PasswordField.getText()+"');";


            statement.executeUpdate(verifyLogin);

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("page.fxml"));
            Stage window = (Stage) SignupButton.getScene().getWindow();
            window.setScene(new Scene(fxmlLoader.load(), 600, 400));
        }
        catch(Exception e)
        {
            MessageLabel.setText("Invalid Username");

            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid Username!");
            alert.show();

        }
    }
}
