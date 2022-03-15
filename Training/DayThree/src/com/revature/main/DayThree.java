package com.revature.main;

import java.lang.reflect.Field;

public class DayThree {

    public static void main (String[] args) throws NoSuchFieldException, IllegalAccessException{
        String myString = "Hello World!";
        System.out.println(myString);

        System.out.println("Hi, there.");

        myString = null;

        String myString2 = new String( "Hello World");

        String myString3 = ("Hello world");
        String myString4 = ("Hello worl");

//        String s = "";
//        for (int i = 0; i < myString4.length(); i++){
//           s = s + myString4.charAt(i);
//        }

        /*
                Java Reflection: changing the internal value of a String
        */
        String h = "Hello World";
        Field f = String.class.getDeclaredField("value");
        f.setAccessible(true);
        f.set(h, "bcdedit".toCharArray());

        System.out.println("Hello World");
        System.out.println("Hello World");
        System.out.println("Hello World");
        System.out.println("Hello World");

        /*
                String API methods
        */

    }

}
