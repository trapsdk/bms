package com.example.bankmanagementsystem;

public class BalanceSheetSet{ // BALANCE SHEET  DATA NODE SET TO HOLD DEPOSIT, WITHDRAW AMOUNT FOR STATEMENTS
    String amount; // USED TO HOLD AMOUNT WILL PARSE LATER TO SUM UP BALANCE FOR ACCOUNTS
    String description;
    BalanceSheetSet(String newAmount, String newDescription){
        amount = newAmount;
        description = newDescription;
    }
    BalanceSheetSet(){
        amount = "";
        description = "";
    }
}