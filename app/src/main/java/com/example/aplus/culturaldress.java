package com.example.aplus;

public class culturaldress {
    private static String firstName = "null";
    private static String lastName = "null";
    private  static String email = "null";
    private static int phoneNumber = 0;
    private static String userName = "null";
    private static String password = "null";


    public void setFirstName(String name){
        firstName = name;
    }
    public static String getFirstName(){
        return firstName;
    }
    public void setLastName(String name){
        lastName = name;
    }
    public static String getLastName(){
        return lastName;
    }
    public void setEmail(String emails){
        email = emails;
    }
    public static String getEmail(){
        return email;
    }
    public void setPhoneNumber(int number){
        phoneNumber = number;
    }
    public static int getPhoneNumber(){
        return phoneNumber;
    }
    public void setPassword(String psw){
        password = psw;
    }
    public static String getPassword(){
        return password;
    }
    public void setUserName(String username){
        userName = username;
    }
    public static String getUserName(){
        return userName;
    }


    public void createAccount(String first, String last,String mail,String username,int phonenumber,String pass){
        this.setFirstName(first);
        this.setLastName(last);
        this.setEmail(mail);
        this.setUserName(username);
        this.setPhoneNumber(phonenumber);
        this.setPassword(pass);
        //direct to costumer profile homepage
        //Send verification email with listed username and password

    }





}
