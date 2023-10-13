package com.example.demo;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloController  {

    @FXML
    private Button cancelButton;

    public void cancelButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private Button backButton;

    @FXML
    private Button loginButton;

    @FXML
    private Button SignupButton;
    @FXML
    private TextField NameTextField;

    @FXML
    private TextField AgeTextField;

    @FXML
    private TextField PhonenoTextField;

    @FXML
    private Label DetailsLabel;

    @FXML
    private TextField emailTextField;

    //static public String name=null;
    public void loginButtonOnAction(ActionEvent e) throws Exception {
        try {

            if (usernameTextField.getText().isBlank() == false && passwordPasswordField.getText().isBlank() == false) {
                // loginMessageLabel.setText("You try to login");
                //name=usernameTextField.getText();
                validataLogin();

            } else {
               // loginMessageLabel.setText("Please enter username and password");

                System.out.println("Please fill all Info");
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please fill username and password to Login!");
                alert.show();
            }
        } catch (Exception er) {
            er.printStackTrace();
        }
    }

    public void validataLogin() throws Exception {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM airline.useraccounts WHERE username = '" + usernameTextField.getText() + "' AND password = '" + passwordPasswordField.getText() + "'";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    //loginMessageLabel.setText("Welcome");
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("page.fxml"));
                    Stage window = (Stage) loginButton.getScene().getWindow();
                    window.setScene(new Scene(fxmlLoader.load(), 600, 400));
                } else {
                   // loginMessageLabel.setText("Invalid login");

                   // System.out.println("Please fill all Info");
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please enter the correct username and password!");
                    alert.show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void backButtonOnAction(ActionEvent e) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load(), 600, 400));
    }

    @FXML
    private Button nextButton;

    @FXML
    private Label invalidphoneno;

    @FXML
    private Label invalidname;

    String name;
    String age;
    String phoneno;
    String email;

    public void nextButtonOnAction(ActionEvent e) throws Exception {


        if (NameTextField.getText().isBlank() == false && AgeTextField.getText().isBlank() == false && PhonenoTextField.getText().isBlank() == false && emailTextField.getText().isBlank() == false) {

            try {
                name = NameTextField.getText();
                age = AgeTextField.getText();
                int age1 = Integer.parseInt(age);
                phoneno = PhonenoTextField.getText();
                long phoneno1=Long.parseLong(phoneno);
                email = emailTextField.getText();
                int c = phoneno.length();
                try {
                    if (c == 10) {
                        String databaseName = "airline";
                        String databaseUser = "root";
                        String databasePassword = "root";
                        String url = "jdbc:mysql://localhost/" + databaseName;

                        Class.forName("com.mysql.cj.jdbc.Driver");

                        Connection con = DriverManager.getConnection(url, databaseUser, databasePassword);


                        Statement statement = con.createStatement();


                        String details = "INSERT INTO airline.coustomer_details (Name,Age,Phoneno,Email) VALUES ('" + NameTextField.getText() + "','" + AgeTextField.getText() + "','" + PhonenoTextField.getText() + "','" + emailTextField.getText() + "');";

                        statement.executeUpdate(details);



                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Journey_Details.fxml"));
                        Stage window = (Stage) nextButton.getScene().getWindow();
                        window.setScene(new Scene(fxmlLoader.load(), 600, 400));

                    } else {
                        invalidphoneno.setText("Invalid Phoneno");

                        Alert alert=new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Invalid Phone number,please fill it correctly!");
                        alert.show();

                    }
                }
                catch (Exception ze)
                {
                    ze.printStackTrace();
                }
            }
            catch (Exception ae) {

                DetailsLabel.setText("Invalid entry");

                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid Entry!");
                alert.show();
            }
        }
         else
            {
                DetailsLabel.setText("Please enter the Details");

                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please fill the Details!");
                alert.show();
            }


    }











    public void SignupButtonOnAction(ActionEvent e) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Signup.fxml"));
        Stage window = (Stage) SignupButton.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load(), 600, 400));
    }




}