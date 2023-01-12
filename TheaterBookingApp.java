package gr.aueb.cf.projects;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A Theater booking app that prints a menu with choices for the user.
 * The user can choose to book a seat or cancel a previously booked seat
 * by entering the column and the row.
 *
 * @author Alexandros Peteinaris
 */
public class TheaterBookingApp {

    public static void main(String[] args) {
        runTheaterApp();
    }

    public static void runTheaterApp() {
        boolean[][] seats = new boolean[30][12];
        Scanner in = new Scanner(System.in);
        int choice = 0;
        do {
            printTheaterMenu();
            choice = selectFromMenu(seats, in);
        } while(choice != 3);
    }

    /**
     * Selects a choice from the Menu and either books or cancels a seat.
     * @param seats The array of seats.
     * @param in    The user input.
     * @return
     *      the user's choice
     */
    public static int selectFromMenu(boolean[][] seats, Scanner in) {
        int choice = 0;
        int row = 0;
        char col;
        try {
            choice = in.nextInt();
            if (choice < 1 || choice > 3) {
                System.out.println("Please insert a valid choice.");
                in.nextLine();
                choice = getUserInput();
            }
            switch(choice) {
                case 1:
                    System.out.println("Enter a column between A - L, and a row between 1-30.");
                    col = in.next().charAt(0);
                    row = in.nextInt();
                    if (col < 'A' || col > 'L' || row < 1 || row > 30) {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    book(seats, col, row);
                    break;
                case 2:
                    System.out.println("Cancel a booked seat.");
                    col = in.next().charAt(0);
                    row = in.nextInt();
                    if (col < 'A' || col > 'L' || row < 1 || row > 30) {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    cancel(seats, col, row);
                    break;
                case 3:
                    System.out.println("Thank you for using our program.");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Please insert a valid choice.");
            in.nextLine();
            printTheaterMenu();
            choice = getUserInput();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please insert a valid choice, between A-L for column and 1-30 for row.");
            in.nextLine();
            printTheaterMenu();
            choice = getUserInput();
        }
        return choice;
    }

    /**
     * Gets the user's input.
     * @return
     *      the user's input
     */
    public static int getUserInput() {
        Scanner in = new Scanner(System.in);
        if (in.hasNextInt()) {
            return in.nextInt();
        }else {
            System.out.println("Enter a number between 1 and 5");
            in.nextLine();
        }
        return in.nextInt();
    }

    /**
     * Prints the Theater menu
     */
    public static void printTheaterMenu() {
        System.out.println("Welcome to our Theater! Please choose an option:");
        System.out.println("1. Book a seat.");
        System.out.println("2. Cancel a booking.");
        System.out.println("3. Exit.");
    }

    /**
     * Books a seat by referencing its column and row number from a range
     * of available unbooked seats.
     * @param seats  The theater seats.
     * @param column The column of choice.
     * @param row    The row number.
     */
    public static void book(boolean[][]seats, char column, int row) {
        try {
            /*
             Offset rows by -1 in order to be able to book seats starting from number 1 to 30,
             and also offset columns by -65, in order to be able to use chars A through L.
             I did the same for the cancel method below.
            */
            if (seats[row - 1][column - 65]) {
                System.out.printf("Seat %c%d is already booked.\n",column,row);

            } else {
                seats[row -1 ][column - 65] = true;
                System.out.printf("Booked a reservation for seat %c%d. \n",column,row);
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Cancels a previously booked seat.
     *
     * @param seats  The theater seats.
     * @param column The column of choice.
     * @param row    The row number.
     */
    public static void cancel(boolean[][]seats, char column, int row) {
        if (seats[row - 1][column - 65]) {
            seats[row - 1][column - 65] = false;
            System.out.printf("Cancelled reservation for seat %c%d.\n",column,row);
        } else {
            System.out.printf("Seat %c%d is not booked.\n",column,row);
        }
    }
}
