package com.example.bankmanagementsystem;

public class BalanceSheetSet{ // BALANCE SHEET  DATA NODE SET TO HOLD DEPOSIT, WITHDRAW AMOUNT FOR STATEMENTS
    private String amount; // USED TO HOLD AMOUNT WILL PARSE LATER TO SUM UP BALANCE FOR ACCOUNTS
    private String description; // DEPOSIT OR WITHDRAW DESCRIPTION
    BalanceSheetSet(String newAmount, String newDescription){
        amount = newAmount;
        description = newDescription;
    }
    BalanceSheetSet(){
        amount = "";
        description = "";
    }
        // GETTER AND SETTERS FOR BALANCE SHEET SET CLASS
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}