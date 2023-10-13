package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SubmitController {


    String name;
    String age;
    String phoneno;
    String email;

    @FXML
    private  Label nameLabel;

    @FXML
    private Button cancelButton;



    @FXML
    private Button seedetails;



    public void CancelButtonOnAction(ActionEvent e) throws Exception
    {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void SeeDetailsOnAction(ActionEvent e) throws Exception
    {

             nameLabel.setText(name);
    }
}
