package com.sczapla.budgetfx;

import com.sczapla.budgetfx.service.UserService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLController implements Initializable {
    
    private Label label;
    @FXML
    private Label lbInfo;
    @FXML
    private TextField txLogin;
    @FXML
    private TextField txPass;
    @FXML
    private Button btLogin;
    
    private UserService userService = new UserService();
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 

    @FXML
    private void btLoginAction(ActionEvent event) {
        userService.login(txLogin.getText(), txPass.getText());
    }
    
}
