package org.example;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.*;

public class GameLogic extends Main{
    static Scanner scanner = new Scanner(System.in);

    //Entry point method that runs the game
    public static void runGame(){
        while(true){
            System.out.print("\nWhats your name?: ");
            String name = scanner.nextLine();
            if(name.matches(".*[a-z,A-Z].*")) {
                Player.setName(name.trim());
                break;
            }
            System.out.println("Name must contain at least 1 letter.");
        }

        while(true){
            String computerChoice = computerSelection();
            String playerChoice = playerSelection();
            calculateWinner(computerChoice, playerChoice);

            String continuePlaying;
            while(true){
                System.out.print("\nPlay again? (y/n): ");
                continuePlaying = scanner.nextLine().trim().toUpperCase();
                boolean validInput = validateYesOrNo(continuePlaying);
                if(validInput) break;
                System.out.printf("\nSorry, %s is not a valid option\n", continuePlaying);
                Main.showInstructions();
            }

            boolean playAgain = willYouPlayAgain(continuePlaying);

            if(!playAgain) break;
        }
    }

    //takes user input and returns if they will play again
    public static boolean willYouPlayAgain(String continuePlaying){
        if(continuePlaying.equals("N") || continuePlaying.equals("NO")){
            String formattedDateTime = getFormattedDateTime();

            System.out.println("\nScore board: " + formattedDateTime);
            System.out.println("--------------------------------------");
            System.out.println("Computer wins: " + Computer.getWins());
            System.out.println(Player.getName() + "'s wins: " + Player.getWins());
            System.out.println("Ties: " + Player.getTies());

            System.out.printf("\nThank you for playing %s! Goodbye.\n", Player.getName());
            System.out.println("--------------------------------------");
            sleep(1000);
            return false;
        }
        return true;
    }

    //gets local date time and returns formatted date time string
    public static String getFormattedDateTime(){
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
        return dateTime.format(dateFormat);
    }

    //randomly selects an option for the computer-returns selection
    public static String computerSelection(){
        String [] choices = {"ROCK", "PAPER", "SCISSORS"};
        Random random = new Random();
        int randomNumber = random.nextInt(3);
        return choices[randomNumber];
    }

    //prompts user to choose an option-returns selection
    public static String playerSelection(){
        System.out.print("\nOkay " + Player.getName() + ", enter Rock, Paper, or Scissors: ");

        while(true){
            String playerChoice = scanner.nextLine().toUpperCase();
            switch(playerChoice){
                case "ROCK":
                case "PAPER":
                case "SCISSORS":
                    return playerChoice;
                case "R":
                    return "ROCK";
                case "P":
                    return "PAPER";
                case "S":
                    return "SCISSORS";
            }

            System.out.println("\n"+playerChoice + " is not a valid option.");
            Main.showInstructions();
            System.out.print("\nRock, Paper, or Scissors: ");
        }
    }

    //Cals winner using HashMap!
    public static void calculateWinner(String computer, String player){
        displayCountdown();
        System.out.printf("\nComputer picked: %s\nYou picked: %s\n", computer, player);

        HashMap<String, Integer> rpsMap = new HashMap<>();
        rpsMap.put("ROCK", 0);
        rpsMap.put("PAPER", 1);
        rpsMap.put("SCISSORS", 2);

        int result = rpsMap.get(player) - rpsMap.get(computer);
        switch(result){
            case 0: displayWinLossMsg(false, true);
            break;
            case -2:
            case 1: displayWinLossMsg(true, false);
            break;
            default: displayWinLossMsg(false, false);
        }
    }

    //takes in the winner or tie condition, displays result and increments score board
    public static void displayWinLossMsg(boolean playerWon, boolean itWasTied){
        if(itWasTied){
            Player.incrementTies();
            System.out.println("\nIts a TIE!!");
        } else if(playerWon){
            Player.incrementWins();
            System.out.println("\nYOU WIN!");
        } else {
            Computer.incrementWins();
            System.out.println("\nSORRY, you lost...");
        }
        System.out.println("----------------------");
    }

    public static void displayCountdown(){
        System.out.println("\nROCK...");
        sleep(500);
        System.out.println("PAPER...");
        sleep(500);
        System.out.println("SCISSORS...");
        sleep(500);
        System.out.println("SHOOT!");
        sleep(500);
    }

    public static void sleep(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}