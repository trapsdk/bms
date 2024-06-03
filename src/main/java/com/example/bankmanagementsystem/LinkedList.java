package com.example.bankmanagementsystem;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

public class LinkedList<T>{
    ClassLoader classLoader; // GET URL OF BALANCE SHEET
    Scanner fileInput;  // USED TO LOAD BALANCE SHEET TO CORRESPONDING USERNAMES
    class Account{  // ACCOUNT DATA CLASS
        java.util.LinkedList<HashMap<Integer, String>> balanceSheet = new java.util.LinkedList<>();  // LINKED LIST OF HASHMAP TO STORE AMOUNT WITH DESCRIPTION FOR STATEMENT BUTTON LATER ON
        double balance; // BALANCE OF EACH ACCOUNT FOR EACH USER
        double totalSpent;  // TOTAL SPENT METRIC FOR EACH ACCOUNT FOR EACH USER
        Account(){  // DEFAULT CONSTRUCTOR
            balance = 0.0;
            totalSpent = 0.0;
            balanceSheet = new java.util.LinkedList<>();
        }
    }
    class Node{ // OUR ACCOUNT DATA NODES
        String username; // USERNAME
        String password; // PASSWORD
        Account checking;   // ACCOUNT DATA OBJECT
        Account savings;    // ACCOUNT DATA OBJECT
        Node next; // POINTER TO NEXT ACCOUNT DATA NODE
        Node(){ // EMPTY CONSTRUCTOR OF NODE
            next = null;
            username = null;
            password = null;
            checking = new Account();
            savings = new Account();
        }
        Node(String user, String pass){ // CONSTRUCTOR OF NODE WITH USER AND PASS
            username = user;
            password = pass;
            next = null;
            checking = new Account();
            savings = new Account();
        }
        public String getUsername(){
            return username;
        }
        public String getPassword(){
            return password;
        }
    }

    Node head;  // STARTING HEAD FOR LINKED LIST OF ACCOUNT DATA NODES
    int size; // SIZE OF LINKED LIST OF DATA NODES
    LinkedList(){ // LINKED LIST CONSTRUCTOR
        head = new Node();
        openBalanceSheet();
    }
    public void add(String newUser, String newPass){ // ADD FUNCTION METHOD TO ADD INFO TO ACCOUNT DATA NODE
        Node temp = new Node(newUser, newPass);
        if ( head.next == null){
            head.next = temp;
            size++;
            return;
        }else{
            Node curr = head;
            while ( curr.next != null ) {
                curr = curr.next;
            }
            curr.next = temp;
            size++;
            return;
        }

    }
    public int size() { // RETURN SIZE OF LINKED LIST (TOTAL NUMBER OF ACCOUNT DATA NODES)
        return size;
    }
    public String getUser(int index){   // RETURNING USERNAME AT INT INDEX FROM ACCOUNT DATA NODE
        Node curr = head.next;
        for ( int i = 0; i < index; i++){
            curr = curr.next;
        }
        return curr.username;
    }
    public Node get(int index){   // RETURNING ACCOUNT DATA NODE AT INT INDEX FROM ACCOUNT DATA NODE
        Node curr = head.next;
        for ( int i = 0; i < index; i++){
            curr = curr.next;
        }
        return curr;
    }
    public void openBalanceSheet(){
        classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("balancesheet.txt");
        try {
            File file = new File(resource.toURI());
            fileInput = new Scanner(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // FIX THIS
    public void updateBalanceSheet(){
        while( fileInput.hasNext() ){
            String tempLine = fileInput.nextLine();

        }
        fileInput.close();
    }

}
