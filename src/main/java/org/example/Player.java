package org.example;

public class Player{
    private static String name;
    private static int numOfWins = 0;
    private static int numOfTies = 0;

    public static void setName(String nameInput){
        name = nameInput;
    }
    public static String getName(){
        return name;
    }
    public static void incrementWins(){
        numOfWins++;
    }
    public static int getWins(){
        return numOfWins;
    }
    public static void incrementTies(){
        numOfTies++;
    }
    public static int getTies(){
        return numOfTies;
    }
}