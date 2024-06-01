package com.example.bankmanagementsystem;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

// READ USERNAME FILE INTO A DATA STRUCTURE, DE LIMITER IS ","
// PASSWORD - CS244
// LOGIN BUTTON DOES POPUP IF THEY DONT MATCH, SCENE SWITCH IF THEY DO

public class HelloApplication extends Application {
    Scene landingPage;
    @Override
    public void start(Stage stage) throws IOException {
        Database myDatabase = new Database();
        stage.setAlwaysOnTop(true);
        landingPage = setLandingPage();
        setMainMenu(stage);

//        stage.setScene(landingPage);
//        stage.show();
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
        VBox v = new VBox();

        // SETTING UP BUTTON AND TEXT FIELD SETTINGS
        user.setMaxSize(200,200);
        pass.setMaxSize(200,200);
        login.setMaxSize(200,200);
        user.setTranslateY(-20);
        pass.setTranslateY(-20);
        login.setTranslateY(-20);
        user.setPromptText("Enter Username");
        pass.setPromptText("Enter Password");
        user.getStylesheets().add("style.css");

        // MORE PANE SETTINGS/MERGING PANES
        v.setSpacing(10);
        v.getChildren().addAll(logo,user, pass, login);
        v.setAlignment(Pos.CENTER);
        root.setCenter(v);
        login.setOnAction(ActionEvent ->

                stage.setScene(landingPage));


        root.getStylesheets().add("style.css");
        stage.initStyle(StageStyle.UTILITY);
        stage.setResizable(false);
        stage.setTitle("-- BMS --");
        stage.setScene(mainMenu);
        stage.show();
    }
    private Scene setLandingPage(){
        // STARTING POINT FOR WINDOW
        BorderPane landingRoot = new BorderPane();
        Menu checking = new Menu("Checking");
        Menu savings = new Menu("Savings");
        Menu logout = new Menu("Logout");
        MenuBar menuBar = new MenuBar();
        Button depositBtn = new Button("Deposit");
        Button withdrawBtn = new Button("Withdraw");
        Button statementBtn = new Button("Statements");
        VBox options = new VBox();


        // SET BUTTN STYLE.
        // WIDEN VBOX SOME HOW





        options.setSpacing(30);
        options.setAlignment(Pos.CENTER);
        options.prefWidth(1);


        options.getChildren().addAll(depositBtn,withdrawBtn,statementBtn);
        menuBar.getMenus().addAll(checking,savings,logout);


        landingRoot.setTop(menuBar);
        landingRoot.setLeft(options);


        landingPage = new Scene(landingRoot, 800,500);
        landingPage.getStylesheets().add("style.css");
        return landingPage;
    }
}