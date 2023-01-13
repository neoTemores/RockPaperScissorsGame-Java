package org.example;

public class Computer{
    private static int numOfWins = 0;

    public static void incrementWins(){
        numOfWins++;
    }
    public static int getWins(){
        return numOfWins;
    }
}