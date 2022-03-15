package com.revature.main;

public class Student2 {

    //instance variables
    int id;
    String name;
}

//Creating object and class: Initializing through REFERENCE VARIABLE
class TestStudent2{
    public static void main(String [] args){
        Student s1 = new Student();
        //reference variables
        s1.id = 101;
        s1.name = "Buhari";

        System.out.println(s1.id + " " + s1.name);

        //creating multiple variables and store info in it.
        //creating objects
        Student s2 = new Student();
        Student s3 = new Student();

        s2.id = 102;
        s2.name = "Maiwada";
        s3.id = 103;
        s3.name = "Nasir";
        //printing data
        System.out.println(s2.id+ " " +s2.name);
        System.out.println(s3.id+ " "+s3.name);
    }
}
