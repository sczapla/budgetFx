package com.sczapla.budgetfx;

import com.sczapla.budgetfx.service.UserService;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btLoginAction(ActionEvent event) {
        boolean isLogin = userService.login(txLogin.getText(), txPass.getText());
        if (isLogin) {
            Stage stage = null;
            Parent root = null;
            try {
                stage = (Stage) btLogin.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("/fxml/aplikacja.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println("Nie można załadować sceny.");
            }

        } else {
            lbInfo.setText("Nieprawidłowa nazwa użytkownika lub hasło");
        }
    }

}
