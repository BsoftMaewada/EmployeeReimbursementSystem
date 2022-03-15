package com.revature.main;

//Object and class: Through Method
public class Student3 {
    //instance variables
    int rollno;
    String name;

    void insertRecord(int r, String n){
        rollno = r;
        name = n;
    }
    void displayInfo(){
        System.out.println(rollno + " " +name);
    }
}
    class TestStudent4{
        public static void main(String args[]){
            Student3 s1 = new Student3();
            Student3 s2 = new Student3();
            s1.insertRecord(111, "Karan");
            s2.insertRecord(222, "Ayan");
            s1.displayInfo();
            s2.displayInfo();
        }
}
