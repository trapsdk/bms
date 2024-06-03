package com.example.bankmanagementsystem;
import java.io.File;
import java.net.URL;
import java.util.Scanner;
public class Database {
    LinkedList<String> accountList;
    ClassLoader classLoader;
    Scanner fileInput;
    String currentAccountName;
    Account checking;
    Account savings;

    class Account{
        double balance;
        float interest;
        Account(){
            balance = 0;
            interest = 0;
        }
    }
    Database() {   // OPEN USERNAMES.TXT AND ADD TO LINKED LIST EVERYTIME PROJECT IS OPENED
        accountList = new LinkedList<>();
        checking = new Account();
        savings = new Account();
        currentAccountName = null;
        classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("usernames.txt");

        try {
            File file = new File("C:\\Users\\gabe3\\IdeaProjects\\bank-management-system\\src\\main\\resources\\com\\example\\bankmanagementsystem\\usernames.txt");
            fileInput = new Scanner(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        initiateDatabase();
    }
    public void initiateDatabase(){ // GRABBING USERNAMES AND STORING THEM IN LINKED LIST OF DATA NODES
        while( fileInput.hasNext() ){
            String tempLine = fileInput.nextLine();
            accountList.add(tempLine, "CS244");
        }
        fileInput.close();
        printDatabase();
    }
    public void printDatabase(){  // PRINTING USERNAME DATABASE
        System.out.println("-- Username Database --");
        for ( int i = 0; i < accountList.size(); i++){
            System.out.println("[" + accountList.getUser(i) + "]");
        }
    }
    public boolean hasUsername(String target){  // ADD FUNCTION TO SEARCH LIST FOR USERNAME AND PASSWORD MATCHES
        for (int i = 0; i < accountList.size(); i++){
            if (target.equals(accountList.getUser(i))){
                return true;
            }
        }
        return false;
    }

    public void setAccountName(String name){
        currentAccountName = name;
    }
    public String getAccountName(){
        return currentAccountName;
    }


    // SINCE I DON'T HAVE A CREATE ACCOUNT FUNCTION THAT ALLOWS USERS TO CREATE PASSWORDS,
    // A LINKED LIST OF STRINGS WOULD BE BETTER HERE OR EVEN AN ARRAY OF STRINGS, HOWEVER,
    // USING DATA NODES IT ALLOWS DYNAMICALLY TO ADD PASSWORD CREATION IN THE FUTURE
    // IF I HAVE THE TIME I WILL CREATE THIS.
    // IF EXTRA TIME, WRITE FUNCTION TO CREATE ACCOUNTS ( WRITE TO USERNAMES .TXT)

}
