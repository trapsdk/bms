package com.example.bankmanagementsystem;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
public class Database {
    LinkedList<Node> accountList;
    class Node{
        String username;
        String password;
        Node(){
            username = null;
            password = null;
        }
        Node(String user, String pass){
            username = user;
            password = pass;
        }
        public String getUsername(){
            return username;
        }
        public String getPassword(){
            return password;
        }
    }
    Scanner fileInput;

    Database() {   // OPEN USERNAMES.TXT AND ADD TO LINKED LIST EVERYTIME PROJECT IS OPENED
        accountList = new LinkedList<>();
        try {
            File file = new File("/Users/gabrielcortez/IdeaProjects/CS244-FinalProject/src/main/resources/usernames.txt");
            fileInput = new Scanner(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        initiateDatabase();
    }
    public void initiateDatabase(){ // GRABBING USERNAMES AND STORING THEM IN LINKED LIST OF DATA NODES
        while( fileInput.hasNext() ){
            String tempLine = fileInput.nextLine();
            Node temp = new Node(tempLine, "CS244");
            accountList.add(temp);
        }
        fileInput.close();
        printDatabase();
    }
    public void printDatabase(){  // PRINTING USERNAME DATABASE
        System.out.println("-- Username Database --");
        for ( int i = 0; i < accountList.size(); i++){
            System.out.println("[" + accountList.get(i).getUsername() + "]");
        }
    }

    public boolean hasUsername(String target){  // ADD FUNCTION TO SEARCH LIST FOR USERNAME AND PASSWORD MATCHES
        for (Node node : accountList) {
            if (node.username.equalsIgnoreCase(target)) {
                return true;
            }
        }
        return false;
    }

    // SINCE I DON'T HAVE A CREATE ACCOUNT FUNCTION THAT ALLOWS USERS TO CREATE PASSWORDS,
    // A LINKED LIST OF STRINGS WOULD BE BETTER HERE OR EVEN AN ARRAY OF STRINGS, HOWEVER,
    // USING DATA NODES IT ALLOWS DYNAMICALLY TO ADD PASSWORD CREATION IN THE FUTURE
    // IF I HAVE THE TIME I WILL CREATE THIS.
    // IF EXTRA TIME, WRITE FUNCTION TO CREATE ACCOUNTS ( WRITE TO USERNAMES .TXT)

}
