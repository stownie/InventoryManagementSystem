package c482.inventorymanagementsystem.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class AddModifyPartController {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    @FXML
    protected RadioButton inHouse, outsourced;
    @FXML
    protected Label machineCoName;
    @FXML
    protected Button save;
    @FXML
    protected TextField idField;
    @FXML
    protected TextField nameField;
    @FXML
    protected TextField inventoryField;
    @FXML
    protected TextField priceField;
    @FXML
    protected TextField maxField;
    @FXML
    protected TextField minField;
    @FXML
    protected TextField machineIDField;

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
    public void onSaveButtonClick(){

    }

}
