package com.example.bankmanagementsystem;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

// READ USERNAME FILE INTO A DATA STRUCTURE, DE LIMITER IS "," // DONE
// PASSWORD - CS244 // DONE
// LOGIN BUTTON DOES POPUP IF THEY DONT MATCH, SCENE SWITCH IF THEY DO // DONE

// LOGOUT MENU BUTTON GOES BACK T0 MAIN MENU
// DISPLAY WELCOME NAME, AND SHOW BALANCES


// CREATE OBJECTS BASED OFF NAME AND SAVE TO FILE WITH THERE 'STATEMENTS'

// DEPOSIT BUTTON ADDS TO TOTAL BALANCE, WITH SMALL DESCRIPTION
// WITHDRAW BUTTON REMOVES FROM BALANCE. WITH SMALL DESCRIPTION

// STATEMENTS READS DEPOSITS AND WITHDRAWALS FROM FILE INTO LINKED LIST.



public class HelloApplication extends Application {
    Scene landingPage;
    Scene mainMenu;
    Database myDatabase;
    @Override
    public void start(Stage stage) throws IOException {
        myDatabase = new Database();
        stage.setAlwaysOnTop(true);
        landingPage = setLandingPage(stage);
        mainMenu = setMainMenu(stage);
        stage.setScene(mainMenu);
        stage.setScene(landingPage); // DEBUG LANDING PAGE LINE
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
    private Scene setMainMenu(Stage stage){
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
        login.setDefaultButton(true);
        user.setPromptText("Enter Username");
        pass.setPromptText("Enter Password");
        user.getStylesheets().add("style.css");

        // MORE PANE SETTINGS/MERGING PANES
        v.setSpacing(10);
        v.getChildren().addAll(logo,user, pass, login);
        v.setAlignment(Pos.CENTER);
        root.setCenter(v);
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String userInfo = user.getText();
                String passInfo = pass.getText();
                System.out.println("Button String: " + userInfo); // DEBUG BUTTON

                user.setText("");
                pass.setText("");

                if (myDatabase.hasUsername(userInfo) && passInfo.equals("CS244")){
                    stage.setScene(landingPage);
                }else{
                    Label label = new Label("You're Username & Password don't Match!");
                    label.setTextFill(Color.RED);
                    Popup popup = new Popup();
                    popup.getContent().add(label);
                    label.setMinSize(100,100);
                    label.setTranslateY(400);
                    popup.setAutoHide(true);
                    popup.show(stage);
                }
            }
        });

        root.getStylesheets().add("style.css");
        stage.initStyle(StageStyle.UTILITY);
        stage.setResizable(false);
        stage.setTitle("-- BMS --");
        return mainMenu;
    }
    private Scene setLandingPage(Stage stage){
        // STARTING POINT FOR WINDOW
        BorderPane landingRoot = new BorderPane();
        landingRoot.getStylesheets().add("style.css");

        Button checking = new Button("Checking");
        Button savings = new Button("Savings");
        Button logout = new Button("Logout");
        Button depositBtn = new Button("Deposit");
        Button withdrawBtn = new Button("Withdraw");
        Button statementBtn = new Button("Statements");
        VBox options = new VBox();
        options.setPrefSize(140,500);
        Group g = new Group();
        g.getChildren().add(options);

        logout.setOnAction(ActionEvent -> {
            stage.setScene(mainMenu);
        });


        logout.setId("logout-button");
        savings.setId("landing-buttons");
        checking.setId("landing-buttons");
        checking.setPrefSize(140,50);
        savings.setPrefSize(140,50);
        logout.setPrefSize(140,50);
        options.setSpacing(100);
        options.setAlignment(Pos.CENTER);

        options.getChildren().addAll(checking,savings,logout);

//        landingRoot.setTop(menuBar);
        landingRoot.setLeft(g);

        landingPage = new Scene(landingRoot, 800,500);
        landingPage.getStylesheets().add("style.css");
        return landingPage;
    }
}