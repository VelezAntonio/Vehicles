package com.example.vehicles;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class VehicleController implements Initializable {
    protected static String year;
    protected static String make;
    protected static String model;
    protected static String type;
    protected static int axle;
    protected static String colors;
    protected static String fuelpwr;
    protected static int cyls;
    protected static int wheel;

    private ObservableList<String>color= FXCollections.observableArrayList("RED","ORANGE","YELLOW","BLUE","GREEN","PURPLE","WHITE","BLACK","BROWN","SILVER","UNKNOWN");
    private ObservableList<String>vtype= FXCollections.observableArrayList("PASSENGER","MOTORCYCLE","TRAILER","SKATEBOARD","CYCLE","CARRIAGE","SCOOTER");
    private ObservableList<Integer>cyl= FXCollections.observableArrayList(2,4,5,6,8,10,12);
    
    private ObservableList<Integer>axles= FXCollections.observableArrayList();
    private ObservableList<Integer>vYear= FXCollections.observableArrayList();


    private ObservableList<String>fuel= FXCollections.observableArrayList("GAS","DIESEL","ELECTRIC","HUMAN","ANIMAL");
    @FXML
    private ComboBox<String> typeCB;
    @FXML
    private ComboBox<String> colorCB;
    @FXML
    private ComboBox<String> fuelCB;
    @FXML
    private ComboBox<Integer> wheelCB;
    @FXML
    private ComboBox<Integer> cylCB;
    @FXML
    private Label cylLabel;

    @FXML
    protected TextField yearTF;

    @FXML
    private ComboBox<Integer>axleCB;
    @FXML
    private Label axleLabel;

    @FXML
    protected TextField makeTF;
    @FXML
    protected TextField modelTF;
    @FXML
    private Button createBtn;
    @FXML
    private Button nextBtn;

    @FXML
    private Label yearErrorLabel;



    private int curYear = Calendar.getInstance().get(Calendar.YEAR) + 1;//current year+1 for next model year.



    @FXML
    protected void Select(ActionEvent event) {
        List<String>fuelExceptions= Arrays.asList("GAS","DIESEL");
        List<String>typeExceptions = Arrays.asList("MOTORCYCLE","PASSENGER","TRAILER");
        if (typeCB.getSelectionModel().getSelectedItem() == "TRAILER") {
            axleCB.setVisible(true);
            axleLabel.setVisible(true);
        }
        else{
            axleCB.setVisible(false);
            axleLabel.setVisible(false);
        }
        if (fuelExceptions.contains(fuelCB.getSelectionModel().getSelectedItem())){
            cylCB.setVisible(true);
            cylLabel.setVisible(true);
        }
        else{
            cylCB.setVisible(false);
            cylLabel.setVisible(false);
        }




    }



    @FXML
    protected void onNextBtnClick() throws IOException {
        for (int i = 0; i < curYear; i++) {
            vYear.add(i);
        }



        try {
            if ((yearTF.getText().isBlank() == true) || (makeTF.getText().isBlank() == true) || (modelTF.getText().isBlank() == true) || checkType() == "-1" ||
                    checkAxle() == -1 || Objects.equals(wheelCB.getSelectionModel().getSelectedItem(), null) || checkCyl() == -1 ||
                    Objects.equals(fuelCB.getSelectionModel().getSelectedItem(), null) || Objects.equals(colorCB.getSelectionModel().getSelectedItem(), null)) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("ERROR!");
                alert.setContentText("Ensure all fields are filled out!");
                alert.showAndWait();

            } else if (!vYear.contains(Integer.parseInt(yearTF.getText()))) {
                yearErrorLabel.setText("Year not valid! (0 - " + curYear + ')');

            } else {

                year = yearTF.getText();
                make = makeTF.getText();
                model = modelTF.getText();
                type = typeCB.getSelectionModel().getSelectedItem();


                colors = colorCB.getSelectionModel().getSelectedItem();
                fuelpwr = fuelCB.getSelectionModel().getSelectedItem();

                wheel = wheelCB.getSelectionModel().getSelectedItem();
                printCar();
                Stage stage = (Stage) nextBtn.getScene().getWindow();
                stage.close();

            }
        }catch (NumberFormatException e){
            yearErrorLabel.setText("Year not valid! (0 - " + curYear + ')');

        }

    }

    private int checkCyl() {
        if (Objects.equals(fuelCB.getSelectionModel().getSelectedItem(),"GAS")&&Objects.equals(cylCB.getSelectionModel().getSelectedItem(),null)||Objects.equals(fuelCB.getSelectionModel().getSelectedItem(),"DIESEL")&&
        Objects.equals(cylCB.getSelectionModel().getSelectedItem(),null)){
            cyls=-1;
        }
        else if (Objects.equals(typeCB.getSelectionModel().getSelectedItem(),"ELECTRIC")||Objects.equals(typeCB.getSelectionModel().getSelectedItem(),"HUMAN")||
                Objects.equals(typeCB.getSelectionModel().getSelectedItem(),"ANIMAL")&&cylCB.isVisible()==false){
            cyls=-2;
        }
        else if (cylCB.isVisible()==false){
            cyls=-2;
        }
        else {
            cyls=cylCB.getSelectionModel().getSelectedItem();
        }

        return cyls;
    }

    private String checkType() {
        if (Objects.equals(typeCB.getSelectionModel().getSelectedItem(),"TRAILER" ) && Objects.equals(axleCB.getSelectionModel().getSelectedItem(),null)){
            type="-1";
        }

        else{
            type=typeCB.getSelectionModel().getSelectedItem();
        }
        return type;
    }


    private int checkAxle() {
        if (axleCB.isVisible()) {
            axle=axleCB.getSelectionModel().getSelectedItem();
        }

        else if (axleCB.isVisible()&&Objects.equals(axleCB.getSelectionModel().getSelectedItem(),null)){
            axle=-1;
        }
            else{
            axle=-2;
        }
        return axle;
    }


    private void printCar() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VehicleApplication.class.getResource("VehiclePrint.fxml"));
        Stage printStage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(),500,400);
        printStage.setScene(scene);
        printStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeCB.setItems(vtype);
        colorCB.setItems(color);
        fuelCB.setItems(fuel);
        cylCB.setItems(cyl);
        ObservableList<Integer> wheels = FXCollections.observableArrayList();
        for (int i = 1; i < 19; i++) {
            wheels.add(i);
        }
        wheelCB.setItems(wheels);
        for (int i = 1; i < 7; i++) {
            axles.add(i);
            }
        axleCB.setItems(axles);



        }

    }

