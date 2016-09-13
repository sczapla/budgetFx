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
import com.sczapla.budgetfx.service.ResourcesService;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    @FXML
    private TableView<?> tvOutcome;
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
    private PieChart pieChart;
    @FXML
    private TextField txExpense;
    @FXML
    private TextField txIncome;
    @FXML
    private Button btLogout21;
    @FXML
    private Button btLogout11;
    @FXML
    private ComboBox<?> cbRole;
    @FXML
    private TextField txEmail;
    @FXML
    private TextField txLoginNewUser;
    @FXML
    private PasswordField txPassNewUser;
    @FXML
    private ComboBox<?> cbType11;
    @FXML
    private TextField txCategoryDesc;
    @FXML
    private TextField txCategoryName;
    @FXML
    private Button btLogout211;
    @FXML
    private Button btLogout111;

    private ResourcesService resourceService = new ResourcesService();
    private User user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbType.setItems(FXCollections.observableArrayList(TransactionType.values()));
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
        /*ObservableList<PieChart.Data> chartList = resourceService.getExpenseCategoryChart(getDate(dateFrom.getValue()), getDate(dateTo.getValue()));
        pieChart.setData(chartList);
        txIncome.setText(resourceService.getSumExpense(getDate(dateFrom.getValue()), getDate(dateTo.getValue())).toPlainString());
        txExpense.setText(resourceService.getSumExpense(getDate(dateFrom.getValue()), getDate(dateTo.getValue())).toPlainString());*/
    }

    @FXML
    private void clearAction(ActionEvent event) {
    }

    @FXML
    private void addAction(ActionEvent event) {
        Resources resource = new Resources();
        resource.setAmount(BigDecimal.valueOf(Double.valueOf(txPrice.getText())));
        resource.setDate(getDate(dpDateIncome.getValue()));
        resource.setName(txDesc.getText());
        resource.setCategory((ResourceCategory) cbCategory.getValue());
        resource.setUser(user);
        resource.setTransactionType(cbType.getValue());
        resourceService.saveResource(resource);
    }

    @FXML
    private void addUserAction(ActionEvent event) {
    }

    @FXML
    private void clearUserAction(ActionEvent event) {
    }

    @FXML
    private void addCategoryAction(ActionEvent event) {
    }

    @FXML
    private void clearCategoryAction(ActionEvent event) {
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
