package com.revature.main;

public class Testing {

    public static void main(String [] args){
        for (byte i = 0; i < 5; i++){
            System.out.println(i);
        }
    int i = 0;
        while (i != 4 ){
            switch(i){
                case 0:
                    System.out.println(0);
                    break;
                case 1:
                    System.out.println(1);
                default:
                    System.out.println("x");
                    break;
            }
            i++;
        }
    }
}
