package org.example;
import java.util.Scanner;
public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        System.out.println("\nHi! Let's play rock, paper, scissors!");

        System.out.print("\nDo you want to see the instructions? (y/n): ");
        String yesNoInput = scanner.nextLine().toUpperCase();

        boolean validInput = validateYesOrNo(yesNoInput);
        if(!validInput){
            showInstructions();
        } else if(yesNoInput.equals("Y") || yesNoInput.equals("YES")){
            showInstructions();
        }

        GameLogic.runGame();
    }

    //validates if user entered yes or no
    public static boolean validateYesOrNo(String response){
        switch(response){
            case "Y":
            case "YES":
            case "N":
            case "NO":
                return true;
            default:
                return false;
        }
    }

    //prints instructions
    public static void showInstructions(){
        System.out.println("\n-All options are case insensitive.");
        System.out.println("-You can enter 'Y' or 'Yes'.");
        System.out.println("-You can enter 'N' or 'No'.");
        System.out.println("-You can enter 'R' or 'Rock'.");
        System.out.println("-You can enter 'P' or 'Paper'.");
        System.out.println("-You can enter 'S' or 'Scissors'.");
        System.out.println("-Again, all options are not case sensitive");
    }
}