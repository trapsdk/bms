package com.example.bankmanagementsystem;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


//
// DEPOSIT BUTTON ADDS TO TOTAL BALANCE, WITH SMALL DESCRIPTION
// WITHDRAW BUTTON REMOVES FROM BALANCE. WITH SMALL DESCRIPTION

public class HelloApplication extends Application {
    Scene landingPage;
    Scene mainMenu;
    Database myDatabase;
    @Override
    public void start(Stage stage) throws IOException {
        myDatabase = new Database();
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
        // CREATING LOGIN UI/BUTTONS
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
        login.setId("landing-buttons");
        user.setPromptText("Enter Username");
        pass.setPromptText("Enter Password");
        user.getStylesheets().add("style.css");
        // MORE PANE SETTINGS/MERGING PANES
        v.setSpacing(10);
        v.getChildren().addAll(logo,user, pass, login);
        v.setAlignment(Pos.CENTER);
        root.setCenter(v);
        // SETTING BUTTON ACTION
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String userInfo = user.getText();
                String passInfo = pass.getText();
                System.out.println("Button String: " + userInfo); // DEBUG BUTTON

                user.setText("");
                pass.setText("");

                if (myDatabase.hasUsername(userInfo) && passInfo.equals("CS244")){
                    myDatabase.setCurrentClient(userInfo);
                    myDatabase.loadBalanceSheet();
                    Scene temp = setLandingPage(stage);
                    stage.setScene(temp);
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

        // RETURNING MAIN SCENE AND SETTING WINDOW TO "UTILITY"
        root.getStylesheets().add("style.css");
        stage.initStyle(StageStyle.UTILITY);
        stage.setResizable(false);
        stage.setTitle("-- BMS --");
        return mainMenu;
    }
    private Scene setLandingPage(Stage stage){
        // STARTING ROOT FOR WINDOW
        BorderPane landingRoot = new BorderPane();
        landingRoot.getStylesheets().add("style.css");

                    // CENTER PANE CODE FOR DATE, NAME, BALANCES, AND BUTTONS
        // CREATING PANES AND BUTTONS FOR CENTER GRIDPANE
        GridPane landingMain = new GridPane();
//        landingMain.setGridLinesVisible(true);
        String currentDate = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
        Label dateLabel = new Label("Today's Date: " + currentDate);
        dateLabel.setFont(Font.font(20));

            // TEMP LABELS FOR MIDDLE CURRENTLY
            // WILL ADD BALANCE INFO HERE AND SPENDING METRIC
        Label balanceLabel = new Label("Checking Balance: $" + myDatabase.currentClient.checking.balance);
        Label totalSpentLabel = new Label("Total Spent: $" + myDatabase.currentClient.checking.totalSpent);
        balanceLabel.setAlignment(Pos.CENTER);
        balanceLabel.setFont(Font.font(20));
        balanceLabel.setPrefSize(330,300);
        totalSpentLabel.setAlignment(Pos.CENTER);
        totalSpentLabel.setFont(Font.font(20));
        totalSpentLabel.setPrefSize(330,300);
        landingMain.add(balanceLabel, 0,1);
        landingMain.add(totalSpentLabel, 1,1);

        // THIRD ROW OF GRID PANE
        Button depositBtn = new Button("Deposit");
        Button withdrawBtn = new Button("Withdraw");
        depositBtn.setId("landing-buttons");
        withdrawBtn.setId("landing-buttons");
        depositBtn.setTranslateX(70);
        withdrawBtn.setTranslateX(70);
        depositBtn.setPrefSize(200,80);
        withdrawBtn.setPrefSize(200,80);
        depositBtn.setFont(Font.font(20));
        withdrawBtn.setFont(Font.font(20));
        landingMain.add(depositBtn,0,2);
        landingMain.add(withdrawBtn, 1,2);

        // NAME RETRIEVAL FOR WELCOME TEXT
        Label currName = new Label("Welcome back, " + myDatabase.getCurrentClient().username);
        currName.setFont(Font.font(20));
        currName.setPrefSize(330,100);
        currName.setMaxSize(330,100);
        currName.setAlignment(Pos.CENTER);
        landingMain.add(currName, 0,0);
        landingMain.add(dateLabel, 1,0);

                    // LEFT MENU SIDE CODE
        // CREATING LEFT MENU BUTTONS
        VBox options = new VBox();
        options.setPrefSize(140,500);
        Group vboxGroup = new Group();
        Button checking = new Button("Checking");
        Button savings = new Button("Savings");
        Button statementBtn = new Button("Statements");
        Button logout = new Button("Logout");
        // EDITING LEFT MENU BUTTONS AND SIZE
        statementBtn.setId("landing-buttons");
        logout.setId("logout-button");
        savings.setId("landing-buttons");
        checking.setId("landing-buttons");
        statementBtn.setPrefSize(140,50);
        checking.setPrefSize(140,50);
        savings.setPrefSize(140,50);
        logout.setPrefSize(140,50);
        options.setSpacing(50);
        options.setAlignment(Pos.CENTER);


        TextInputDialog amountDialog = new TextInputDialog();
        TextInputDialog descriptionDialog = new TextInputDialog();
        amountDialog.setX(600);
        amountDialog.setY(250);
        amountDialog.setHeaderText("Enter Amount");
        amountDialog.setTitle("Amount");
        descriptionDialog.setHeaderText("Enter Description");
        descriptionDialog.setTitle("Description");


        // SETTING BUTTON FUNCTIONS FOR LEFT SIDE MENU
        checking.setOnAction(ActionEvent-> {
            landingRoot.setCenter(landingMain);
            balanceLabel.setText("Checking Balance: $" + myDatabase.currentClient.checking.balance);
            totalSpentLabel.setText("Total Spent: $" + myDatabase.currentClient.checking.totalSpent);
        });
        savings.setOnAction(ActionEvent -> {
            landingRoot.setCenter(landingMain);
            balanceLabel.setText("Savings Balance: $" + myDatabase.currentClient.savings.balance);
            totalSpentLabel.setText("Total Spent: $" + myDatabase.currentClient.savings.totalSpent);
        });
        statementBtn.setOnAction(ActionEvent -> {
            ListView<String> temp = myDatabase.listViewSetUp();
            temp.setMaxSize(650, 450);
            landingRoot.setCenter(temp);
        });
        logout.setOnAction(ActionEvent -> {
            stage.setScene(mainMenu);
        });
        // SETTING BUTTON FUNCTIONS FOR BOTTOM MENU
        depositBtn.setOnAction(ActionEvent -> {
            String amountD = "+";
            String amt;
            amountDialog.showAndWait();
            if ( !amountDialog.getEditor().getText().isEmpty()  ){
                amt = amountDialog.getEditor().getText();

            }
            else{
                amt = "0";
            }
            amountD += amt;
            descriptionDialog.showAndWait();
            String descriptionD = descriptionDialog.getEditor().getText();
            if ( descriptionDialog.getEditor().getText().isEmpty()  ){
                descriptionD = "Unknown";
            }

            System.out.println(amountD + " : " + descriptionD);

            // ADD TO BALANCE SHEET OF CLIENT OBJ
            // WRITE TO .TXT
            // PUT TWO DECIMALS FOR LABEL
            // put restriction for dialog input
            if (balanceLabel.getText().contains("Checking") ){
                myDatabase.currentClient.checking.balance += Double.parseDouble(amt);
                balanceLabel.setText("Checking Balance: $" + myDatabase.currentClient.checking.balance);
            }else{
                myDatabase.currentClient.savings.balance += 500;
                balanceLabel.setText("Savings Balance: $" + myDatabase.currentClient.savings.balance);
            }
        });

        withdrawBtn.setOnAction(ActionEvent -> {
            String amountD = "-";
            String amt;
            amountDialog.showAndWait();
            if ( !amountDialog.getEditor().getText().isEmpty()  ){
                amt = amountDialog.getEditor().getText();

            }
            else{
                amt = "0";
            }
            amountD += amt;
            descriptionDialog.showAndWait();
            String descriptionD = descriptionDialog.getEditor().getText();
            if ( descriptionDialog.getEditor().getText().isEmpty()  ){
                descriptionD = "Unknown";
            }

            System.out.println(amountD + " : " + descriptionD);

            // add popup for amount of money with description
            if (balanceLabel.getText().contains("Checking") ){
                myDatabase.currentClient.checking.balance -= Double.parseDouble(amt);
                myDatabase.currentClient.checking.totalSpent += 500;
                balanceLabel.setText("Checking Balance: $" + myDatabase.currentClient.checking.balance);
                totalSpentLabel.setText("Total Spent: $" + myDatabase.currentClient.checking.totalSpent);
            }else{
                myDatabase.currentClient.savings.balance -= 500;
                myDatabase.currentClient.savings.totalSpent += 500;
                balanceLabel.setText("Savings Balance: $" + myDatabase.currentClient.savings.balance);
                totalSpentLabel.setText("Total Spent: $" + myDatabase.currentClient.savings.totalSpent);
            }
        });

        // GRID PANE GROUP TO ADD TO SCENE LATER
        Group gridPaneGroup = new Group();
        gridPaneGroup.setTranslateY(0);
        gridPaneGroup.getChildren().addAll(landingMain);

        // ADDING ALL CHILDREN TO THEIR RESPECTIVE PANES/SIDES
        vboxGroup.getChildren().add(options);
        options.getChildren().addAll(checking,savings,statementBtn,logout);
        landingRoot.setLeft(vboxGroup);
        landingRoot.setCenter(gridPaneGroup);
        // CREATING SCENE AND RETURNING THE WINDOW
        landingPage = new Scene(landingRoot, 800,500);
        landingPage.getStylesheets().add("style.css");
        return landingPage;
    }
}