package com.example.bankmanagementsystem;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        setMainMenu(stage);
    }
    public static void main(String[] args) {
        launch();
    }
    private void setMainMenu(Stage stage){
        // CREATING LOGIN UI
        Image image = new Image("bms.png");
        ImageView logo = new ImageView(image);
        BorderPane root = new BorderPane();
        Scene mainMenu = new Scene(root, 800, 500);
        TextField user = new TextField();
        TextField pass = new TextField();
        Button login = new Button("LOGIN");
        user.setMaxSize(200,200);
        pass.setMaxSize(200,200);
        login.setMaxSize(200,200);
        user.setTranslateY(-20);
        pass.setTranslateY(-20);
        login.setTranslateY(-20);
        user.setPromptText("Enter Username");
        pass.setPromptText("Enter Password");
        user.getStylesheets().add("style.css");
        VBox v = new VBox();
        v.setSpacing(10);
        v.getChildren().addAll(logo,user, pass, login);
        v.setAlignment(Pos.CENTER);
        root.setCenter(v);
        root.getStylesheets().add("style.css");
        stage.initStyle(StageStyle.UTILITY);
        stage.setResizable(false);
        stage.setTitle("-- BMS --");
        stage.setScene(mainMenu);
        stage.show();
    }
}