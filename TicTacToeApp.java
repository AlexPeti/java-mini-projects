package gr.aueb.cf.projects;

/**
 * Tic-tac-toe game that prints a 3 x 3 board and is played by 2 players.
 * It prompts each of them to input a number between 1 and 9 in order to
 * choose a cell, and prints X and O on the respective cell, according to
 * their choice.
 * The players take turns to play until one of them is declared a winner
 * or the game ends in a draw. The application prohibits the players from
 * choosing an already occupied cell and prompts them to pick a new one
 * instead.
 *
 * @author Alexandros Peteinaris
 */

import java.util.Scanner;

public class TicTacToeApp {

    public static void main(String[] args) {
            playTicTacToe();
    }
    /**
     * Starts the Tic Tac Toe game,it also checks
     * if the user has inserted an integer and if not,
     * it prompts the user to do so, so that the application
     * does not throw an exception.
     */
    public static void playTicTacToe() {
        String[][] ticTacToe = { {"|_", "|_", "|_|"},
                {"|_", "|_", "|_|"},
                {"|_", "|_", "|_|"} };
        Scanner in = new Scanner(System.in);
        boolean player1Turn = true;
        int player1Choice = 0;
        int player2Choice = 0;

        System.out.println("\n Welcome to my Tic Tac Toe game! Each player will take " +
                "alternating turns to print X or O on the board, use numbers from 1-9 to select \n the cell you wish " +
                "to print on,like so!\n \n 1 | 2 | 3 \n 4 | 5 | 6 \n 7 | 8 | 9 \n \nLet's go!\n");
        while (true) {
            printBoard(ticTacToe);
            if (player1Turn) {
                System.out.println("Player 1 turn!");
                if (in.hasNextInt()) {
                        player1Choice = in.nextInt();
                        updateBoard(ticTacToe, player1Choice, "X");
                } else {
                    System.out.println("Please enter a number between 1 and 9.");
                    in.next();
                    continue;
                }
            } else {
                System.out.println("Player 2, it's your turn!");
                if (in.hasNextInt()) {
                    player2Choice = in.nextInt();
                    updateBoard(ticTacToe, player2Choice, "O");
                } else {
                    System.out.println("Please enter a number between 1 and 9.");
                    in.next();
                    continue;
                }
            }
            String result = checkResult(ticTacToe);
            if (result.equals("Player X wins!") || result.equals("Player O wins!") || result.equals("It's a Draw!")) {
                printBoard(ticTacToe);
                System.out.println(result);
                break;
            }
            player1Turn = !player1Turn;
        }
    }

    /**
     * Printing the Tic Tac Toe board.
     * @param ticTacToe the Tic Tac Toe board
     */
    public static void printBoard(String[][] ticTacToe) {
        for (int i = 0; i < ticTacToe.length; i++) {
            for (int j = 0; j < ticTacToe[i].length; j++) {
                System.out.print(ticTacToe[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * This method updates the Tic Tac Toe board after each
     * player's move. The method also checks if the input is valid
     * (numbers between 1 and 9) and if a cell
     * is already occupied by using a boolean flag. It then
     * prompts the player who picked the occupied cell,
     * to choose a new one.
     *
     * @param ticTacToe the Tic Tac Toe board
     * @param choice    the player's choice (1-9)
     * @param player    player X or O
     */
    public static void updateBoard(String[][] ticTacToe, int choice, String player) {
        Scanner in = new Scanner(System.in);
        boolean cellOccupied = true;
        while (cellOccupied) {
            if (choice < 1 || choice > 9) {
                System.out.println("Please input a number between 1 and 9.");
                choice = in.nextInt();
                continue;
            }
            switch (choice) {
                case 1:
                    if (ticTacToe[0][0].equals("|_")) {
                        ticTacToe[0][0] = "|" + player;
                        cellOccupied = false;
                    }
                    break;
                case 2:
                    if (ticTacToe[0][1].equals("|_")) {
                        ticTacToe[0][1] = "|" + player;
                        cellOccupied = false;
                    }
                    break;
                case 3:
                    if (ticTacToe[0][2].equals("|_|")) {
                        ticTacToe[0][2] = "|" + player;
                        cellOccupied = false;
                    }
                    break;
                case 4:
                    if (ticTacToe[1][0].equals("|_")) {
                        ticTacToe[1][0] = "|" + player;
                        cellOccupied = false;
                    }
                    break;
                case 5:
                    if (ticTacToe[1][1].equals("|_")) {
                        ticTacToe[1][1] = "|" + player;
                        cellOccupied = false;
                    }
                    break;
                case 6:
                    if (ticTacToe[1][2].equals("|_|")) {
                        ticTacToe[1][2] = "|" + player;
                        cellOccupied = false;
                    }
                    break;
                case 7:
                    if (ticTacToe[2][0].equals("|_")) {
                        ticTacToe[2][0] = "|" + player;
                        cellOccupied = false;
                    }
                    break;
                case 8:
                    if (ticTacToe[2][1].equals("|_")) {
                        ticTacToe[2][1] = "|" + player;
                        cellOccupied = false;
                    }
                    break;
                case 9:
                    if (ticTacToe[2][2].equals("|_|")) {
                        ticTacToe[2][2] = "|" + player;
                        cellOccupied = false;
                    }
                    break;
            }
            if (cellOccupied) {
                //Prompting the player to select a different cell if he selected an occupied one
                System.out.println("Cell is already occupied. Please select a different cell!");
                choice = in.nextInt();
            }
        }
    }

    /**
     * Method that checks if Player X or Player O wins,
     * or if game is a draw.
     * @param ticTacToe the Tic-Tac-Toe board
     * @return
     *      game result (X wins, O wins, or draw)
     */
    public static String checkResult(String[][] ticTacToe) {
        //Creating a new array and populating it with the cells of the
        //possible winning combinations of the Tic-Tac-Toe board
        String[] combination = new String[8];
        combination[0] = ticTacToe[0][0] + ticTacToe[0][1] + ticTacToe[0][2];
        combination[1] = ticTacToe[1][0] + ticTacToe[1][1] + ticTacToe[1][2];
        combination[2] = ticTacToe[2][0] + ticTacToe[2][1] + ticTacToe[2][2];
        combination[3] = ticTacToe[0][0] + ticTacToe[1][0] + ticTacToe[2][0];
        combination[4] = ticTacToe[0][1] + ticTacToe[1][1] + ticTacToe[2][1];
        combination[5] = ticTacToe[0][2] + ticTacToe[1][2] + ticTacToe[2][2];
        combination[6] = ticTacToe[0][0] + ticTacToe[1][1] + ticTacToe[2][2];
        combination[7] = ticTacToe[2][0] + ticTacToe[1][1] + ticTacToe[0][2];

        //Iterating over elements in array and checking if they are equal to |X|X|X or |O|O|O
        for (String line : combination) {
            if (line.equals("|X|X|X")) {
                return "Player X wins!";
            } else if (line.equals("|O|O|O")) {
                return "Player O wins!";
            }
        }
        //Checking if the game is a draw by
        for (String[] row : ticTacToe) {
            for (String cell : row) {
                if (cell.equals("|_")) {
                    return "";
                }
            }
        }
        return "It's a Draw!";
    }
}
