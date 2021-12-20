package com.example.vehicles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VehiclePrintController extends VehicleController implements Initializable {

    @FXML
    protected Label yearPrint;
    @FXML
    private Label makePrint;
    @FXML
    private Label modelPrint;
    @FXML
    private Label typePrint;
    @FXML
    private Label axlePrint;
    @FXML
    private Label colorPrint;
    @FXML
    private Label fuelPrint;
    @FXML
    private Label cylPrint;
    @FXML
    private Label wheelsPrint;
    @FXML
    private Button createNewBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        yearPrint.setText(String.valueOf(VehicleController.year));
        makePrint.setText(String.valueOf(VehicleController.make));
        modelPrint.setText(String.valueOf(VehicleController.model));
        typePrint.setText(type);
        if(axle == -2){
            axlePrint.setText("N/A");
        }else {
            axlePrint.setText(String.valueOf(axle));
        }
        if (cyls==-2){
            cylPrint.setText("N/A");}
        else{
            cylPrint.setText(String.valueOf(cyls));

        }
        colorPrint.setText(colors);
        fuelPrint.setText(fuelpwr);
        wheelsPrint.setText(String.valueOf(wheel));

    }

    public void createNewBtnOnAction(ActionEvent event) throws IOException {
        newVehicle();
        Stage stage=(Stage)  createNewBtn.getScene().getWindow();
        stage.close();
    }

    private void newVehicle() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VehicleController.class.getResource("VehicleApplication.fxml"));
        Stage registerStage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 520, 400);
        registerStage.setScene(scene);
        registerStage.show();
    }


}
