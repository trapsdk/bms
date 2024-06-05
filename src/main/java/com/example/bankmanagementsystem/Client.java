package com.example.bankmanagementsystem;

import java.util.LinkedList;    // PREBUILT LINKED LIST DATA STRUCTURE
public class Client{ // CLIENT CLASS OBJECT TO HOLD USERNAME AND PASSWORDS ALONG WITH ACCOUNT OBJS
    String username;
    String password;
    Account checking;
    Account savings;
    public class Account{ // NESTED CLASS HOLDING EACH ACCOUNT BALANCE AND TOTAL SPENT
        double balance;
        int totalSpent;
        LinkedList<BalanceSheetSet> balanceSheet; // LINKED LIST OF BALANCE SHEET SET NODES, HOLDS AMOUNT ALONG WITH DESC
        Account(){
            balance = 0;
            totalSpent = 0;
            balanceSheet = new LinkedList<>();
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

}
