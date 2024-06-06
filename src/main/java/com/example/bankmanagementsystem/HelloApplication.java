// GABRIEL R. CORTEZ
/// 06.04.2024
//// CS 244
//// FINAL PROJECT 4
///// BANK MANAGEMENT SYSTEM
/////////////////////////////////////////
package com.example.bankmanagementsystem;
import javafx.animation.PauseTransition;
import javafx.application.Application;
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
import javafx.util.Duration;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

// 1) new deposits/withdrawals write to balance sheet
// 2) COMMENT/ORGANIZE ALL CODE
// 3) CREATE OWN LINKED LIST
// 4) FIND A WAY TO SAVE STATEMENTS FOR EACH USER
public class HelloApplication extends Application {
    Scene bankView; // AFTER LOGIN GUI SCENE
    Scene mainMenu; // OPENING GUI SCENE TO LOGIN INTO ACCOUNT
    Database myDatabase; // DATABASE OBJECT TO HANDLE READ/WRITE OF .TXT AND CLIENT OBJS
    DecimalFormat df; // DECIMAL FORMATER FOR LABELS
    @Override
    public void start(Stage stage) throws IOException {
        df = new DecimalFormat("0.00");
        myDatabase = new Database();
        bankView = setBankView(stage, mainMenu);
        mainMenu = setMainMenu(stage);
        stage.setScene(mainMenu);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
    private Scene setMainMenu(Stage stage){ // SETTING MAIN LOGIN SCREEN GUI

                // CREATING ALL SCENE OBJECTS
        Image image = new Image("bms.png");
        ImageView logo = new ImageView(image);
        BorderPane root = new BorderPane();
        Scene mainMenu = new Scene(root, 800, 500);
        TextField user = new TextField();
        TextField pass = new TextField();
        Button login = new Button("LOGIN");
        Button createAcc = new Button("CREATE ACCOUNT");
        VBox v = new VBox();
        TextInputDialog newAccDialog = new TextInputDialog();

                // ADJUSTING OBJECT SETTINGS
        newAccDialog.setHeaderText("Enter new account name: ");
        newAccDialog.setTitle("CREATE ACCOUNT");
        login.setMaxSize(200,200);
        login.setTranslateY(-20);
        login.setDefaultButton(true);
        login.setId("landing-buttons");
        createAcc.setMaxSize(200,200);
        createAcc.setTranslateY(-20);
        createAcc.setId("red-button");
        user.setMaxSize(200,200);
        pass.setMaxSize(200,200);
        user.setTranslateY(-20);
        pass.setTranslateY(-20);
        user.setPromptText("Enter Username");
        pass.setPromptText("Enter Password");
        user.getStylesheets().add("style.css");
        v.setSpacing(10);
        v.getChildren().addAll(logo,user, pass, login, createAcc);
        v.setAlignment(Pos.CENTER);
        root.setCenter(v);

                // SETTING BUTTON ACTIONS
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                            // USING TEXT FIELDS TO GET INFO FROM USER
                String userInfo = user.getText();
                String passInfo = pass.getText();
                System.out.println("Button String: " + userInfo); // DEBUG BUTTON
                user.setText("");
                pass.setText("");
                            // VERIFY LOGIN FUNCTION
                if (myDatabase.hasUsername(userInfo) && passInfo.equals("CS244")){
                    myDatabase.setCurrentClient(userInfo);
                    if ( myDatabase.getCurrentClient().getChecking().getBalanceSheet().isEmpty() ){
                        myDatabase.loadBalanceSheet();
                    }
                    myDatabase.listViewSetUp();
                    Scene temp = setBankView(stage, mainMenu);
                    stage.setScene(temp);
                }else{
                            // LOGIN FAILED FUNCTION
                    Label label = new Label("You're Username & Password don't Match!");
                    PauseTransition pauseTransition = new PauseTransition(Duration.seconds(1));
                    label.setTextFill(Color.RED);
                    Popup popup = new Popup();
                    popup.getContent().add(label);
                    label.setMinSize(100,100);
                    label.setTranslateY(400);
                    popup.setAutoHide(true);
                    popup.show(stage);
                    pauseTransition.setOnFinished(ActionEvent -> {
                        popup.hide();
                            });
                    pauseTransition.play();
                }
            }
        });
        createAcc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                newAccDialog.showAndWait();
                if ( !newAccDialog.getEditor().getText().isEmpty() ){
                    myDatabase.createNewUser(newAccDialog.getEditor().getText());
                }
                newAccDialog.getEditor().setText("");
            }
        });

                // RETURNING MAIN SCENE AND SETTING WINDOW TO "UTILITY"
        root.getStylesheets().add("style.css");
        stage.initStyle(StageStyle.UTILITY);
        stage.setResizable(false);
        stage.setTitle("-- BMS --");
        return mainMenu;
    }
    private Scene setBankView(Stage stage, Scene mainMenuTemp){
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
        Label balanceLabel = new Label("Checking Balance: $" + df.format(myDatabase.getCurrentClient().getChecking().getBalance()));
        Label totalSpentLabel = new Label("Total Spent: $" + df.format(myDatabase.getCurrentClient().getChecking().getTotalSpent()));
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
        Label currName = new Label("Welcome back, " + myDatabase.getCurrentClient().getUsername());
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
        logout.setId("red-button");
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
        amountDialog.setHeaderText("Enter Amount");
        amountDialog.setTitle("Amount");
        descriptionDialog.setHeaderText("Enter Description");
        descriptionDialog.setTitle("Description");


        // SETTING BUTTON FUNCTIONS FOR LEFT SIDE MENU
        checking.setOnAction(ActionEvent-> {
            landingRoot.setCenter(landingMain);
            balanceLabel.setText("Checking Balance: $" + df.format(myDatabase.getCurrentClient().getChecking().getBalance()));
            totalSpentLabel.setText("Total Spent: $" + df.format(myDatabase.getCurrentClient().getChecking().getTotalSpent()));
        });
        savings.setOnAction(ActionEvent -> {
            landingRoot.setCenter(landingMain);
            balanceLabel.setText("Savings Balance: $" + df.format(myDatabase.getCurrentClient().getSavings().getBalance()));
            totalSpentLabel.setText("Total Spent: $" + df.format(myDatabase.getCurrentClient().getSavings().getTotalSpent()));
        });

        statementBtn.setOnAction(ActionEvent -> {
            landingRoot.setCenter(myDatabase.getStatementList());
        });
        logout.setOnAction(new EventHandler<ActionEvent>() {
                               @Override
                               public void handle(ActionEvent actionEvent) {
                                   stage.setScene(mainMenuTemp);
                               }
                           });

                // SETTING BUTTON FUNCTIONS FOR BOTTOM MENU
                depositBtn.setOnAction(ActionEvent -> {
                    String amountD = "+";
                    String amt;
                    amountDialog.showAndWait();
                    if (!amountDialog.getEditor().getText().isEmpty()) {
                        amt = amountDialog.getEditor().getText();

                    } else {
                        amt = "0";
                    }
                    amountD += amt;
                    descriptionDialog.showAndWait();
                    String descriptionD = descriptionDialog.getEditor().getText();
                    if (descriptionDialog.getEditor().getText().isEmpty()) {
                        descriptionD = "Unknown";
                    }
                    System.out.println(amountD + " : " + descriptionD);
                    myDatabase.addToListView(amountD + " : " + descriptionD);
                    amountDialog.getEditor().setText("");
                    descriptionDialog.getEditor().setText("");

                    if (balanceLabel.getText().contains("Checking")) {
                        double temp = myDatabase.getCurrentClient().getChecking().getBalance();
                        temp += Double.parseDouble(amt);
                        myDatabase.getCurrentClient().getChecking().setBalance(temp);
                        balanceLabel.setText("Checking Balance: $" + df.format(myDatabase.getCurrentClient().getChecking().getBalance()));
                        myDatabase.getCurrentClient().getChecking().addToBalanceSheet(amountD, descriptionD);
                    } else {
                        double temp = myDatabase.getCurrentClient().getSavings().getBalance();
                        temp += Double.parseDouble(amt);
                        myDatabase.getCurrentClient().getSavings().setBalance(temp);
                        balanceLabel.setText("Savings Balance: $" + df.format(myDatabase.getCurrentClient().getSavings().getBalance()));
                        myDatabase.getCurrentClient().getChecking().addToBalanceSheet(amountD, descriptionD);
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
            myDatabase.addToListView(amountD + " : " + descriptionD);
            System.out.println(amountD + " : " + descriptionD);
            amountDialog.getEditor().setText("");
            descriptionDialog.getEditor().setText("");
            // add popup for amount of money with description
            if (balanceLabel.getText().contains("Checking") ){
                double temp = myDatabase.getCurrentClient().getChecking().getBalance();
                temp -= Double.parseDouble(amt);
                myDatabase.getCurrentClient().getChecking().setBalance(temp);
                double sTemp = myDatabase.getCurrentClient().getChecking().getTotalSpent();
                sTemp += Double.parseDouble(amt);
                myDatabase.getCurrentClient().getChecking().setTotalSpent(sTemp);
                balanceLabel.setText("Checking Balance: $" + df.format(myDatabase.getCurrentClient().getChecking().getBalance()));
                totalSpentLabel.setText("Total Spent: $" + df.format(myDatabase.getCurrentClient().getChecking().getTotalSpent()));
                myDatabase.getCurrentClient().getChecking().addToBalanceSheet(amountD, descriptionD);
            }else{
                double temp = myDatabase.getCurrentClient().getSavings().getBalance();
                temp -= Double.parseDouble(amt);
                myDatabase.getCurrentClient().getSavings().setBalance(temp);
                double sTemp = myDatabase.getCurrentClient().getSavings().getTotalSpent();
                sTemp += Double.parseDouble(amt);
                myDatabase.getCurrentClient().getSavings().setTotalSpent(sTemp);
                balanceLabel.setText("Savings Balance: $" + df.format(myDatabase.getCurrentClient().getSavings().getBalance()));
                totalSpentLabel.setText("Total Spent: $" + df.format(myDatabase.getCurrentClient().getSavings().getTotalSpent()));
                myDatabase.getCurrentClient().getChecking().addToBalanceSheet(amountD, descriptionD);
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
        mainMenu = new Scene(landingRoot, 800,500);
        mainMenu.getStylesheets().add("style.css");
        return mainMenu;
    }
}