module c482.inventorymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens c482.inventorymanagementsystem to javafx.fxml;
    exports c482.inventorymanagementsystem;
    exports c482.inventorymanagementsystem.Controller;
    opens c482.inventorymanagementsystem.Controller to javafx.fxml;
    exports c482.inventorymanagementsystem.Model;
    opens c482.inventorymanagementsystem.Model to javafx.fxml;
}