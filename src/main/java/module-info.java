module com.example.bankmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bankmanagementsystem to javafx.fxml;
    exports com.example.bankmanagementsystem;
}