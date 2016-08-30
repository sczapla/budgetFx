/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sczapla.budgetfx;

import com.sczapla.budgetfx.model.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tomek
 */
public class AplikacjaController implements Initializable {

    @FXML
    private DatePicker dateFrom;
    @FXML
    private DatePicker dateTo;
    @FXML
    private Label lbUser;
    @FXML
    private Label lbRole;
    @FXML
    private Button btLogout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    public void initUser(User user){
        lbUser.setText(user.getUserName());
        lbRole.setText(user.getUserRoles().get(0).getUserRolePK().getRole().name());
    }

    @FXML
    private void logoutAction(ActionEvent event) {
        Stage stage = null;
            Parent root = null;
            try {
                stage = (Stage) btLogout.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
                root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println("Nie można załadować sceny.");
            }
    }

    @FXML
    private void filterAction(ActionEvent event) {
    }
    
}
