package com.example.bankmanagementsystem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
public class Database { // MAIN DATABASE CLASS
    LinkedList<Client> accountList;
    ClassLoader classLoader;
    Scanner fileInput;
    private Client currentClient;
    private ObservableList<String> statementsObs;
    private ListView<String> statementListView;
    Database() {    // CREATING DATA BASE OBJECT, CREATING NEW ACCOUNT LIST, CREATING NEW DEFAULT CURR CLIENT, AND THEN LOADING FILES
        accountList = new LinkedList<>();
        currentClient = new Client();
        loadUsers();
    }
    public void createNewUser(String newUser){
        PrintWriter out = null;
        classLoader = getClass().getClassLoader();
        URL resource = null;
        resource = classLoader.getResource("usernames.txt");
        try {
            File file = new File(resource.getPath());
            FileWriter fileWriter = new FileWriter(file, true);
            out = new PrintWriter(fileWriter);
            out.println();
            out.write(newUser);
            out.close();
        }
        catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        accountList.add(new Client(newUser, "CS244"));
    }
    public void loadUsers(){ /// LOADING USERNAMES INTO ACCOUNT LIST
        classLoader = getClass().getClassLoader();
        URL resource = null;
        resource = classLoader.getResource("usernames.txt");
        try {
            File file = new File(resource.getPath());
            System.out.println(file.getAbsolutePath());
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
    public void loadBalanceSheet(){ // LOADING BALANCE SHEET
        classLoader = getClass().getClassLoader();
        URL resource = null;
        resource = classLoader.getResource("balancesheet.txt");
        try {
            File file = new File(resource.getPath());
            fileInput = new Scanner(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        while ( fileInput.hasNext() ){ // STORING BALANCE SHEET INTO CLIENT OBJECT
            String tempAmount = fileInput.nextLine();
            String tempDescription = fileInput.nextLine();
            System.out.println("[" + tempAmount + " : " + tempDescription + "]"); // debug line
            currentClient.getChecking().balanceSheet.add(new BalanceSheetSet(tempAmount, tempDescription));
        }
        double balanceSum = 0;
        BalanceSheetSet temp;

        // GETTING SUM OF BALANCE SHEET
        for ( int i = 0; i < currentClient.getChecking().balanceSheet.size(); i++){
            temp = currentClient.getChecking().balanceSheet.get(i);
            char sign = temp.getAmount().charAt(0);
            String amountParse = temp.getAmount().substring(1);
            double balanceParse = Double.parseDouble(amountParse);
            if ( sign == '+' ){
                balanceSum += balanceParse;
            }else{
                balanceSum -= balanceParse;
            }
        }
        currentClient.getChecking().setBalance(balanceSum);
    }
    public void listViewSetUp(){
        List<String> y = new ArrayList<>();
        for( int i = 0; i < currentClient.getChecking().balanceSheet.size(); i++){
            y.add(currentClient.getChecking().balanceSheet.get(i).getAmount() + " : "
            + currentClient.getChecking().balanceSheet.get(i).getDescription());
        }
        statementsObs = FXCollections.observableList(y);
        statementListView = new ListView<>(statementsObs);
    }
    public void addToListView(String input){
        statementsObs.add(input);
    }
    public ListView<String> getStatementList(){
        return statementListView;
    }
    public void printDatabase(){  // PRINTING USERNAMES IN  DATABASE
        System.out.println("-- Username Database --");
        for (Client client : accountList) {
            System.out.println("[" + client.getUsername() + "]");
        }
    }
    public void printBalanceSheet(){
        for (String statementsOb : statementsObs) {
            System.out.println(statementsOb);
        }
    }
    public boolean hasUsername(String target){  // ADD FUNCTION TO SEARCH LIST FOR USERNAME AND PASSWORD MATCHES
        for (Client client : accountList) {
            if (target.equals(client.getUsername())) {
                return true;
            }
        }
        return false;
    }
    public void setCurrentClient(String target){    // FINDING CLIENT IN ACCOUNT LIST AND SETTING AS CURRENT
        for (Client curr : accountList){
            if ( curr.getUsername().equals(target)){
                currentClient = curr;
            }
        }
    }
    public Client getCurrentClient(){   // RETURN CLIENT
        return currentClient;
    }

}
