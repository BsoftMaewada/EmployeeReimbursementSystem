package com.revature.main;


// Creating Class and Object
public class Student {

// defining fields
    int id; //field or data member or instance
    String name;

    //creating main method inside the Student class
    public static void main(String [] args){

        //creating an object or instance
        Student s1 = new Student(); //creating an object Student

        //printing values of the object
        System.out.println(s1.id); //accessing memeber through a reference variable
        System.out.println(s1.name);


    }

}
