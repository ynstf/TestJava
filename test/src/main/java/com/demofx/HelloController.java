package com.demofx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


import java.util.List;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Label name;


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        name.setText("youness atif");

    }
}