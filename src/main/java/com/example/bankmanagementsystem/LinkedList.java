package com.example.bankmanagementsystem;

public class LinkedList<T>{
    class Node{
        String username;
        String password;
        Node next;

        Node(){
            next = null;
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
    Node head;
    int size;
    LinkedList(){
        head = null;
    }

    public void add(String newUser, String newPass){
        if ( head.next == null){
            Node temp = new Node(newUser, newPass);
            head.next = temp;
            size++;
            return;
        }

    }
    public int size() {
        return size;
    }
    public String get(int index){
        Node target = head.next;
        for ( int i = 0; i < index; i++){
            target = target.next;
        }
        return target.username;
    }








}
