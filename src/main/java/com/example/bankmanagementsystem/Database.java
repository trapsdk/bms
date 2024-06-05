package com.example.bankmanagementsystem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
public class Database { // MAIN DATABASE CLASS
    LinkedList<Client> accountList;
    ClassLoader classLoader;
    Scanner fileInput;
    Client currentClient;

    Database() {    // CREATING DATA BASE OBJECT, CREATING NEW ACCOUNT LIST, CREATING NEW DEFAULT CURR CLIENT, AND THEN LOADING FILES
        accountList = new LinkedList<>();
        currentClient = new Client();
        loadUsers();
    }
    public void loadUsers(){ /// LOADING USERNAMES INTO ACCOUNT LIST
        classLoader = getClass().getClassLoader();
        URL resource = null;
        resource = classLoader.getResource("bankmanagementsystem/usernames.txt");
        try {
            File file = new File("/Users/gabrielcortez/IdeaProjects/CS244-FinalProject/src/main/resources/com/example/bankmanagementsystem/usernames.txt");
            fileInput = new Scanner(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        while( fileInput.hasNext() ){
            String tempName = fileInput.nextLine();
            accountList.add(new Client(tempName, "CS244"));
        }
        fileInput.close();
        printDatabase();
    }


    // NEED TO CREATE WAY TO STORE ACCOUNTS BALANCE SHEETS WITH WRITING NEW FILES // kinda done, fixing buttons rn
    public void loadBalanceSheet(){ // LOADING BALANCE SHEET
        classLoader = getClass().getClassLoader();
        URL resource = null;
        resource = classLoader.getResource("bankmanagementsystem/usernames.txt");
        try {
            File file = new File("/Users/gabrielcortez/IdeaProjects/CS244-FinalProject/src/main/resources/com/example/bankmanagementsystem/balancesheet.txt");
            fileInput = new Scanner(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        while ( fileInput.hasNext() ){ // STORING BALANCE SHEET INTO CLIENT OBJECT
            String tempAmount = fileInput.nextLine();
            String tempDescription = fileInput.nextLine();
            System.out.println("[" + tempAmount + " : " + tempDescription + "]"); // debug line
            currentClient.checking.balanceSheet.add(new BalanceSheetSet(tempAmount, tempDescription));
        }
        double balanceSum = 0;
        BalanceSheetSet temp;
        for ( int i = 0; i < currentClient.checking.balanceSheet.size(); i++){
            temp = currentClient.checking.balanceSheet.get(i);
            char sign = temp.amount.charAt(0);
            String amountParse = temp.amount.substring(1);
            double balanceParse = Double.parseDouble(amountParse);
            if ( sign == '+' ){
                balanceSum += balanceParse;
            }else{
                balanceSum -= balanceParse;
            }
        }
        currentClient.checking.balance = balanceSum;
    }
    public ListView<String> listViewSetUp(){
        List<String> y = new ArrayList<>();
        for( int i = 0; i < currentClient.checking.balanceSheet.size(); i++){
            y.add(currentClient.checking.balanceSheet.get(i).amount + " : "
            + currentClient.checking.balanceSheet.get(i).description);
        }
        ObservableList<String> temp = FXCollections.observableList(y);
        return new ListView<>(temp);
    }
    public void printDatabase(){  // PRINTING USERNAMES IN  DATABASE
        System.out.println("-- Username Database --");
        for (Client client : accountList) {
            System.out.println("[" + client.username + "]");
        }
    }
    public boolean hasUsername(String target){  // ADD FUNCTION TO SEARCH LIST FOR USERNAME AND PASSWORD MATCHES
        for (Client client : accountList) {
            if (target.equals(client.username)) {
                return true;
            }
        }
        return false;
    }
    public void setCurrentClient(String target){    // FINDING CLIENT IN ACCOUNT LIST AND SETTING AS CURRENT
        for (Client curr : accountList){
            if ( curr.username.equals(target)){
                currentClient = curr;
            }
        }
    }
    public Client getCurrentClient(){   // RETURN CLIENT
        return currentClient;
    }

    // SINCE I DON'T HAVE A CREATE ACCOUNT FUNCTION THAT ALLOWS USERS TO CREATE PASSWORDS,
    // A LINKED LIST OF STRINGS WOULD BE BETTER HERE OR EVEN AN ARRAY OF STRINGS, HOWEVER,
    // USING DATA NODES IT ALLOWS DYNAMICALLY TO ADD PASSWORD CREATION IN THE FUTURE
    // IF I HAVE THE TIME I WILL CREATE THIS.
    // IF EXTRA TIME, WRITE FUNCTION TO CREATE ACCOUNTS ( WRITE TO USERNAMES .TXT)
    // NEED TO LOAD USERS AGAIN AFTER CREATING ACCOUNT

}
