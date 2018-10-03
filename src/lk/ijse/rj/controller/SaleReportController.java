/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.controller;

import com.jfoenix.controls.JFXComboBox;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.rj.main.AppInitializer;

/**
 *
 * @author Ilman Iqbal
 */
public class SaleReportController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private Label lblSaleNewSales;
    @FXML
    private Label lblSaleManageSales;
    @FXML
    private Label lblHome;
    @FXML
    private JFXComboBox<String> cmbMonth;
    @FXML
    private Label lblSaleReports;

    @FXML
    private void label_OnMouseClicked(MouseEvent event) {
        if (event.getSource() instanceof Label) {

            Label label = (Label) event.getSource();
            String id = label.getId();

            AppInitializer.navigateToSection((Stage) root.getScene().getWindow(), id);

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbMonth.getItems().add("January");
        cmbMonth.getItems().add("February");
        cmbMonth.getItems().add("March");
        cmbMonth.getItems().add("April");
        cmbMonth.getItems().add("May");
        cmbMonth.getItems().add("June");
        cmbMonth.getItems().add("July");
        cmbMonth.getItems().add("August");
        cmbMonth.getItems().add("Septeber");
        cmbMonth.getItems().add("October");
        cmbMonth.getItems().add("November");
        cmbMonth.getItems().add("December");

        cmbMonth.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                try {
//                    InputStream report = getClass().getResourceAsStream("/lk/ijse/rj/report/SaleReport.jasper");
//                    HashMap map = new HashMap();
//                    map.put("monthName", newValue);
//                    JasperPrint print = JasperFillManager.fillReport(report, map, DBConnection.getInstance().getConnection());
//                    JasperViewer.viewReport(print, false);
//                } catch (ClassNotFoundException | SQLException | JRException ex) {
//                    Logger.getLogger(OrderReportController.class.getName()).log(Level.SEVERE, null, ex);
//                }
            }
        });
    }

}
