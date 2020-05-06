//Tyler Perkins
//2 or 1 Player, Have To Guess The Number

import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {

    public static void main(String[] args) {

        int[][] guessWidth = {{0, 100}};
        int totalGuesses = 0;
        String userYoN;
        int totalGuessesP2 = 0;
        boolean playAgainORNOT = true;
        Scanner playerAmount = new Scanner(System.in);

        //Creating loop for method OutOfScope() if they do or don't want to play again.
        while (playAgainORNOT) {
            //Calls to the function oneOrTwoPlayer() where they decide if they want to play 1 or 2-Player.
            oneOrTwoPlayer();

            int singleOrTwo = playerAmount.nextInt();
            //Depending on if the user wants to play single or 2-player.
            switch (singleOrTwo) {
                //User inputs 1, drops them into onePlayer() method.
                case 1:
                    onePlayer(guessWidth, totalGuesses);
                    break;
                //User inputs 2, drops them into the twoPlayer() method.
                case 2:
                    twoPlayer(guessWidth, totalGuesses, totalGuessesP2);
                    break;
                //User inputs anything less than or greater than 1 or 2 drops them into the outOfScope() method in which they choose what they want to do.
                default:
                    //Taking the return out of scope and implementing it here to decide if they want to play again or not.
                    //Get the return value from user
                    userYoN = outOfScope();
                    if (userYoN.toUpperCase().equals("N")) playAgainORNOT = false;
            }
        }
    }

    //Player is prompted to pick between one or two players.
    public static void oneOrTwoPlayer() {
        System.out.print("Please choose either 1-Player or 2-Player(Input 1 or 2): ");

    }

    public static void onePlayer(int[][] guessWidth, int totalGuesses) {
        int unknownAnswer, yourGuess;
        Scanner userGuess = new Scanner(System.in);
        Scanner goAgain = new Scanner(System.in);
        Random rand = new Random();
        String userAnswer;
        boolean playing = true;

        //playing is true until user selects "N" to close the console operation.
        while (playing) {
            boolean playerWin = false;
            totalGuesses = 0;
            unknownAnswer = rand.nextInt((guessWidth[0][1] - guessWidth[0][0]) + 1) + guessWidth[0][0];
            //playerWin is used to reset guess counter each time they want to play again, so it doesn't simultaneously go up.
            while (!playerWin) {
                System.out.print("Guess a number between 0 and 100: ");
                yourGuess = userGuess.nextInt();
                totalGuesses++;

                while (yourGuess < unknownAnswer || yourGuess > unknownAnswer) {
                    if ((yourGuess > 100 || yourGuess < 0)) { //Validating that the user actually inputs a valid number. If they go higher than 100 or lower than 0, they have to re-enter a valid number.
                        System.out.print("Invalid guess. Please guess a number between 0 and 100: ");
                        yourGuess = userGuess.nextInt();
                    } else {
                        //If the guess is greater than the unknown number, prompts the user to guess lower.
                        if (yourGuess > unknownAnswer) {
                            System.out.print("You need to guess lower! Guess Again: ");
                            yourGuess = userGuess.nextInt();
                            totalGuesses++;
                        } else if (yourGuess < unknownAnswer) {
                            //If the guess is less than the unknown number, prompts the user to guess higher.
                            System.out.print("You need to guess higher! Guess Again: ");
                            yourGuess = userGuess.nextInt();
                            totalGuesses++;
                        }
                        if (yourGuess == unknownAnswer) {
                            playerWin = true;
                            System.out.println("You have guessed the right number! The unknown number was " + unknownAnswer + ".");
                            System.out.printf("You guessed the number in %d, guess(es)!\n", totalGuesses);
                            //Asking user if they want to play again or not.
                            System.out.print("Would you like to play again. Type 'Y' for YES or 'N' for NO: ");
                            userAnswer = goAgain.next();
                            //If user input N or n, console closes. They input Y or y, game replays.
                            if (userAnswer.toUpperCase().equals("N")) {
                                playing = false;
                                System.exit(0);
                            }
                            break;
                        }


                    }
                }

            }
        }

    }

    public static void twoPlayer(int[][] guessWidth, int totalGuesses, int totalGuessesP2) {
        Random randPlayerOne = new Random();
        Random randPlayerTwo = new Random();
        Scanner playerGuessing = new Scanner(System.in);
        Scanner goAgain = new Scanner(System.in);
        String userAnswer;
        int unknownNumberPOne, unknownNumberPTwo, playerOneGuess, playerTwoGuess;
        int x = 0;
        int y = 0;
        int[][] gettingNumbers = new int[10][10];
        boolean playing = true;


        while (playing) {
            boolean guessReset = false;
            unknownNumberPOne = randPlayerOne.nextInt((guessWidth[0][1] - guessWidth[0][0]) + 1) + guessWidth[0][0];
            unknownNumberPTwo = randPlayerTwo.nextInt((guessWidth[0][1] - guessWidth[0][0]) + 1) + guessWidth[0][0];
            while (!guessReset) {
                totalGuesses = 0;
                System.out.println("Getting a random number for player 1, please wait.");
                //Setting bounds for player 1 of 2d array and falsely getting a random number from a 10 row 10 column grid (100).
                for (int i = 0; i < gettingNumbers.length; i++) {
                    for (int j = 0; j < gettingNumbers.length; j++) {
                        gettingNumbers[i][j] = x;
                        x++;
                        System.out.print(x + ", ");
                    }
                }
                System.out.println("\nGetting a random number for player 2, please wait.");
                //Setting bounds for player 2 of 2d array and falsely getting a random number from a 10 row 10 column grid (100).
                for (int f = 0; f < gettingNumbers.length; f++) {
                    for (int g = 0; g < gettingNumbers.length; g++) {
                        gettingNumbers[f][g] = y;
                        y++;
                        System.out.print(y + ", ");
                    }
                }
                System.out.print("\nRandom Numbers have been selected. Player 1 please guess an integer between 0 and 100: ");
                playerOneGuess = playerGuessing.nextInt();
                totalGuesses++;
                //Player 1 is trying to guess the number is as few tries as possible.
                while (playerOneGuess != unknownNumberPOne) {
                    if (playerOneGuess < 0 || playerOneGuess > 100) {
                        System.out.print("Invalid guess. Please guess a number between 0 and 100: ");
                        playerOneGuess = playerGuessing.nextInt();
                    } else {
                        if (playerOneGuess < unknownNumberPOne) {
                            System.out.print("You need to guess higher! Guess again: ");
                            playerOneGuess = playerGuessing.nextInt();
                            totalGuesses++;
                        } else if (playerOneGuess > unknownNumberPOne) {
                            System.out.print("You need to lower! Guess again: ");
                            playerOneGuess = playerGuessing.nextInt();
                            totalGuesses++;
                        }
                        if (playerOneGuess == unknownNumberPOne) {
                            guessReset = true;
                            System.out.println("You have guessed the right number! The unknown number was " + unknownNumberPOne + ".");
                            System.out.printf("You guessed the number in %d, guess(es)!\n", totalGuesses);
                            break;
                        }
                    }
                }
                //Have to initalize totalGuessesP2 to make sure counter isn't at its own number carried over from Player 1.
                totalGuessesP2 = 0;
                //Player 2's turn to guess in as few tries as possible to beat Player 1.
                System.out.print("Player 2, please guess a number between 0 and 100: ");
                playerTwoGuess = playerGuessing.nextInt();
                totalGuessesP2++;
                while (playerTwoGuess != unknownNumberPTwo) {
                    if (playerTwoGuess < 0 || playerTwoGuess > 100) {
                        System.out.print("Invalid guess. Please guess a number between 0 and 100: ");
                        playerTwoGuess = playerGuessing.nextInt();
                    } else {
                        if (playerTwoGuess < unknownNumberPTwo) {
                            System.out.print("You need to guess higher! Guess again: ");
                            playerTwoGuess = playerGuessing.nextInt();
                            totalGuessesP2++;
                        } else if (playerTwoGuess > unknownNumberPTwo) {
                            System.out.print("You need to lower! Guess again: ");
                            playerTwoGuess = playerGuessing.nextInt();
                            totalGuessesP2++;
                        }
                        //When player 2 guesses the right answer console will display how many guesses each player took and figures out who
                        if (playerTwoGuess == unknownNumberPTwo) {
                            guessReset = true;
                            System.out.println("You have guessed the right number! The unknown number was " + unknownNumberPOne + ".");
                            System.out.printf("You guessed the number in %d, guess(es)!\n", totalGuessesP2);
                            if (totalGuesses < totalGuessesP2) {
                                System.out.printf("PLAYER 1 WINS! Player 1 guessed their number in %d trie(s) while Player 2 guessed their number in %d trie(s)", totalGuesses, totalGuessesP2);
                            }
                            if (totalGuesses > totalGuessesP2) {
                                System.out.printf("PLAYER 2 WINS! Player 2 guessed their number in %d trie(s) while Player 1 guessed their number in %d trie(s)!", totalGuessesP2, totalGuesses);
                            }
                            break;
                        }
                    }
                }
            }

            System.out.print("\nWould you like to play again. Type 'Y' for YES or 'N' for NO: ");
            userAnswer = goAgain.next();
            if (userAnswer.toUpperCase().equals("N")) {
                playing = false;
                System.exit(0);
            }
        }
    }
    //User input integer out of switch function so, they are in here. Returns value of what the user decides into the main() method.
    public static String outOfScope() {
        Scanner playingAgain = new Scanner(System.in);
        String userYoN;

        System.out.print("You input an integer greater than 2 or less than 1: Try again? 'Y' for YES, 'N' for NO: ");
        userYoN = playingAgain.next();

        //returns user input value to main
        return userYoN;

    }

}

