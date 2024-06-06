package com.example.bankmanagementsystem;

import java.util.LinkedList;    // PREBUILT LINKED LIST DATA STRUCTURE
public class Client{ // CLIENT CLASS OBJECT TO HOLD USERNAME AND PASSWORDS ALONG WITH ACCOUNT OBJS
    private String username;
    private String password;
    private Account checking;
    private Account savings;
    public class Account{ // NESTED CLASS HOLDING EACH ACCOUNT BALANCE AND TOTAL SPENT
        private double balance;
        private double totalSpent;
        LinkedList<BalanceSheetSet> balanceSheet; // LINKED LIST OF BALANCE SHEET SET NODES, HOLDS AMOUNT ALONG WITH DESC
        Account(){
            balance = 0;
            totalSpent = 0;
            balanceSheet = new LinkedList<>();
        }
            // GETTERS AND SETTERS FOR ACCOUNT NESTED CLASS
        public double getBalance(){
            return balance;
        }
        public double getTotalSpent(){
            return totalSpent;
        }
        public LinkedList<BalanceSheetSet> getBalanceSheet() {
            return balanceSheet;
        }
        public void setBalance(double newBalance){
            balance = newBalance;
        }
        public void setTotalSpent(double newTotalSpent){
            totalSpent = newTotalSpent;
        }
        public void setBalanceSheet(LinkedList<BalanceSheetSet> newSheet){
            balanceSheet = newSheet;
        }
        public void addToBalanceSheet(String amount, String desc){
            balanceSheet.add(new BalanceSheetSet(amount, desc));
        }

    }
    Client(){   // DEFAULT CONSTRUCTOR FOR A CLIENT
        username = "";
        password = "";
        checking = new Account();
        savings = new Account();
    }
    Client(String newUser, String newPass){ // CONSTRUCTOR FOR CREATING NEW CLIENTS
        username = newUser;
        password = newPass;
        checking = new Account();
        savings = new Account();
    }

        // GETTERS AND SETTERS FOR CLIENT CLASS
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public Account getChecking(){
        return checking;
    }
    public Account getSavings(){
        return savings;
    }
    public void setUsername(String newUser){
        username = newUser;
    }
    public void setPassword(String newPass){
        password = newPass;
    }
    public void setChecking(Account newAcc){
        checking = newAcc;
    }
    public void setSavings(Account newAcc){
        savings = newAcc;
    }
}
