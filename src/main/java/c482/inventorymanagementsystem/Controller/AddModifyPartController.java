package c482.inventorymanagementsystem.Controller;

import c482.inventorymanagementsystem.InventoryApplication;
import c482.inventorymanagementsystem.Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddModifyPartController implements Initializable {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    private int machineID;
    private String coName;
    @FXML
    public RadioButton inHouse, outsourced;
    @FXML
    public Label machineCoName;
    @FXML
    public Button cancel;
    @FXML
    public Button save;
    @FXML
    public TextField idField;
    @FXML
    public TextField nameField;
    @FXML
    public TextField inventoryField;
    @FXML
    public TextField priceField;
    @FXML
    public TextField maxField;
    @FXML
    public TextField minField;
    @FXML
    public TextField machineIDField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
    }
    @FXML
    public void onRadioSelection(){
        if(inHouse.isSelected()){
            machineCoName.setText("Machine ID");
        }
        else if(outsourced.isSelected()){
            machineCoName.setText("Company Name");
        }
    }

    @FXML
    public void onCancelButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(InventoryApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 400);
        stage.setTitle("Inventory Management Application");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onSaveButtonClick(ActionEvent event) throws IOException {
        id = Integer.parseInt(idField.getText());
        name = nameField.getText();
        price = Double.parseDouble(priceField.getText());
        stock = Integer.parseInt(inventoryField.getText());
        max = Integer.parseInt(maxField.getText());
        min = Integer.parseInt(minField.getText());
        if(max >= min && stock >= min && stock <= max ) {
            if (inHouse.isSelected()) {
                machineID = Integer.parseInt(machineIDField.getText());
                InHousePart part = new InHousePart(id, name, price, stock, max, min, machineID);
                if (Inventory.lookupPart(id) == null) {
                    Inventory.addPart(part);
                } else {
                    Inventory.updatePart(id, part);
                }
            } else if (outsourced.isSelected()) {
                coName = machineIDField.getText();
                OutsourcePart part = new OutsourcePart(id, name, price, stock, max, min, coName);
                if (Inventory.lookupPart(id) == null) {
                    Inventory.addPart(part);
                } else {
                    Inventory.updatePart(id, part);
                }
            }
            Stage stage = (Stage) save.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(InventoryApplication.class.getResource("main-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 400);
            stage.setTitle("Inventory Management Application");
            stage.setScene(scene);
            stage.show();
        }
        else if(min > max){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("User input error");
            alert.setHeaderText("Min/Max Error");
            alert.setContentText("Max inventory must be greater than min inventory.");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("User input error");
            alert.setHeaderText("Stock Error");
            alert.setContentText("Your inventory level must be between min and max inventory.");
            alert.showAndWait();
        }
    }
    public void sendPart(Part part){
        idField.setText(String.valueOf(part.getId()));
        nameField.setText(part.getName());
        inventoryField.setText(String.valueOf(part.getStock()));
        priceField.setText(String.valueOf(part.getPrice()));
        maxField.setText(String.valueOf(part.getMax()));
        minField.setText(String.valueOf(part.getMin()));
        if (part instanceof InHousePart){
            inHouse.setSelected(true);
            machineIDField.setText(String.valueOf(((InHousePart) part).getMachineID()));
        }
        else if(part instanceof OutsourcePart){
            outsourced.setSelected(true);
            machineIDField.setText(String.valueOf(((OutsourcePart) part).getCompanyName()));
        }
    }
    protected void autoGenerateID(){
        int i = 1;
        while (Inventory.lookupPart(i) != null){
            ++i;
        }
        idField.setText(String.valueOf(i));
    }
}


