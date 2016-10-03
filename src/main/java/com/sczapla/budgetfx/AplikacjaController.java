/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sczapla.budgetfx;

import com.sczapla.budgetfx.enums.TransactionType;
import com.sczapla.budgetfx.model.ResourceCategory;
import com.sczapla.budgetfx.model.Resources;
import com.sczapla.budgetfx.model.User;
import com.sczapla.budgetfx.model.UserRoles;
import com.sczapla.budgetfx.model.UserRolesPK;
import com.sczapla.budgetfx.model.enums.UserRole;
import com.sczapla.budgetfx.service.ResourcesService;
import com.sczapla.budgetfx.service.UserService;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

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
    @FXML
    private TableView<Resources> tvOutcome;
    @FXML
    private ComboBox<TransactionType> cbType;
    @FXML
    private DatePicker dpDateIncome;
    @FXML
    private ComboBox<ResourceCategory> cbCategory;
    @FXML
    private TextField txPrice;
    @FXML
    private TextField txDesc;
    @FXML
    private Button btLogout1;
    @FXML
    private Button btLogout2;
    @FXML
    private Button btLogout21;
    @FXML
    private Button btLogout11;
    @FXML
    private ComboBox<UserRole> cbRole;
    @FXML
    private TextField txEmail;
    @FXML
    private TextField txLoginNewUser;
    @FXML
    private PasswordField txPassNewUser;
    @FXML
    private ComboBox<TransactionType> cbType11;
    @FXML
    private TextField txCategoryDesc;
    @FXML
    private TextField txCategoryName;
    @FXML
    private Button btLogout211;
    @FXML
    private Button btLogout111;

    private ResourcesService resourceService = new ResourcesService();
    private UserService userService = new UserService();
    private User user;
    @FXML
    private PieChart pieChartOut;
    @FXML
    private PieChart pieChartIn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbType.setItems(FXCollections.observableArrayList(TransactionType.values()));
        cbType11.setItems(FXCollections.observableArrayList(TransactionType.values()));
        cbRole.setItems(FXCollections.observableArrayList(UserRole.values()));
    }

    public void initUser(User user) {
        this.user = user;
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
        tvOutcome.getItems().clear();;
        ObservableList<PieChart.Data> chartListOut = resourceService.getResourceCategoryChart(
                getDate(dateFrom.getValue()), getDate(dateTo.getValue()), TransactionType.EXPENSE);
        pieChartOut.setData(chartListOut);
        ObservableList<PieChart.Data> chartListIn = resourceService.getResourceCategoryChart(
                getDate(dateFrom.getValue()), getDate(dateTo.getValue()), TransactionType.INCOME);
        pieChartIn.setData(chartListIn);
        tvOutcome.getItems().addAll(resourceService.getResources(getDate(dateFrom.getValue()), getDate(dateTo.getValue())));
    }

    @FXML
    private void clearAction(ActionEvent event) {
        txPrice.setText("");
        dpDateIncome.setValue(null);
        txDesc.setText("");
    }

    @FXML
    private void addAction(ActionEvent event) {
        Resources resource = new Resources();
        resource.setAmount(BigDecimal.valueOf(Double.valueOf(txPrice.getText())));
        resource.setDate(getDate(dpDateIncome.getValue()));
        resource.setName(txDesc.getText());
        resource.setCategory((ResourceCategory) cbCategory.getValue());
        resource.setUser(user);
        resourceService.saveResource(resource);
        Alert alert = new Alert(AlertType.INFORMATION, "Dodano zasób", ButtonType.OK);
        alert.showAndWait();
        clearAction(null);
    }

    @FXML
    private void addUserAction(ActionEvent event) {
        User newUser = new User();
        newUser.setEmail(txEmail.getText());
        newUser.setEnabled(Boolean.TRUE);
        String hashPass = BCrypt.hashpw(txPassNewUser.getText(), BCrypt.gensalt());
        newUser.setPassword(hashPass);
        newUser.setUserName(txLoginNewUser.getText());
        UserRolesPK urPK = new UserRolesPK();
        urPK.setRole(cbRole.getValue());
        urPK.setUserName(txLoginNewUser.getText());
        UserRoles ur = new UserRoles();
        ur.setUserRolePK(urPK);
        newUser.setUserRoles(Arrays.asList(ur));
        userService.saveNewUser(newUser);
        Alert alert = new Alert(AlertType.INFORMATION, "Dodano użytkownika", ButtonType.OK);
        alert.showAndWait();
        clearUserAction(null);
    }

    @FXML
    private void clearUserAction(ActionEvent event) {
        txLoginNewUser.setText("");
        txPassNewUser.setText("");
        txEmail.setText("");
    }

    @FXML
    private void addCategoryAction(ActionEvent event) {
        ResourceCategory category = new ResourceCategory();
        category.setName(txCategoryName.getText());
        category.setDescription(txCategoryDesc.getText());
        category.setTransactionType(cbType11.getValue());
        category.setUser(user);
        resourceService.saveResourceCategory(category);
        Alert alert = new Alert(AlertType.INFORMATION, "Dodano kategorie", ButtonType.OK);
        alert.showAndWait();
        clearCategoryAction(event);
    }

    @FXML
    private void clearCategoryAction(ActionEvent event) {
        txCategoryName.setText("");
        txCategoryDesc.setText("");
    }

    private Date getDate(LocalDate date) {
        Instant instant = Instant.from(date.atStartOfDay(ZoneId.systemDefault()));
        return Date.from(instant);
    }

    @FXML
    private void cbTypeChanged(ActionEvent event) {
        if (cbType.getValue() == TransactionType.EXPENSE) {
            cbCategory.setItems(resourceService.getAllResourceType(TransactionType.EXPENSE));
        } else if (cbType.getValue() == TransactionType.INCOME) {
            cbCategory.setItems(resourceService.getAllResourceType(TransactionType.INCOME));
        }
    }

}
