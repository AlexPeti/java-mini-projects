package gr.aueb.cf.projects;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Develop a program that reads integers from a file
 * (file must contain between 6 and 49 numbers)
 * with values from 1 to 49. It then enters these numbers into an
 * array, which it sorts (for example with Arrays.sort()). Then the program
 * produces all possible combinations consisting of 6 numbers. Simultaneously and immediately
 * after producing each combination it 'filters' each one to meet the
 * following criteria: 1) Contain at most 4 even numbers, 2) contain at most 4
 * odd numbers, 3) contain at most 2 consecutive numbers, 4) contain at most 3 identical
 * ending numbers, 5) contain at most 3 numbers in the same ten.
 * Finally, it prints the final 6-number combinations to a .txt file with a name of your choice.
 *
 * @author Alexandros Peteinaris
 */
public class CombinationsSixApp {

    public static void main(String[] args) throws IOException {
        try {
            createCombinations();
        } catch (IOException e) {
            System.out.println("Invalid input,check if the source file path is correct and retry.");
        }
    }

    public static void createCombinations() throws IOException {
        try {
            //Declaration and initialization of final variables used for filtering the created arrays
            final int THRESHOLD = 4;
            final int ENDING = 3;
            final int CONTIGUOUS = 2;
            final int TEN = 3;
            final int N = 6;

            //Source file
            File file = new File("C:/Temporary/project-numbers.txt");
            //Created file
            File newFile = new File("C:/Temporary/combinations-hex.txt");
            Scanner in = new Scanner(file);
            PrintStream ps = new PrintStream(newFile);

            //Declaration and initialization of the source array which holds all the numbers from the file
            int[] arr = new int[49];
            //Declaration and initialization of the array which will hold the 6-number combinations
            int[] hexArray = new int[6];
            //Insertion of numbers from the source file to the source array
            for(int i = 0; i < arr.length; i++) {
                arr[i] = in.nextInt();
            }
            //sorting the source array
            Arrays.sort(arr);

            for (int i = 0; i <= arr.length - N; i++) {
                for (int j = i + 1; j <= arr.length - N + 1; j++) {
                    for (int k = j + 1; k <= arr.length - N + 2; k++) {
                        for (int m = k + 1; m <= arr.length - N + 3; m++) {
                            for (int n = m + 1; n <= arr.length - N + 4; n++) {
                                for(int o = n + 1; o <= arr.length - N + 5; o++) {
                                    //Populating the new arrays
                                    hexArray[0] = arr[i];
                                    hexArray[1] = arr[j];
                                    hexArray[2] = arr[k];
                                    hexArray[3] = arr[m];
                                    hexArray[4] = arr[n];
                                    hexArray[5] = arr[o];

                                    //Filtering the created arrays
                                    if((hasEven(hexArray,THRESHOLD)) &&
                                            (hasOdd(hexArray,THRESHOLD)) && (hasContiguous(hexArray,CONTIGUOUS))
                                            && (hasSameEnding(hexArray,ENDING)) && (inSameTen(hexArray,TEN))) {
                                        ps.println(hexArray[0] + " " + hexArray[1] +
                                                " " + hexArray[2] + " " + hexArray[3] +
                                                " " + hexArray[4] + " " + hexArray[5]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            ps.close();

        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Filters the array by a threshold number in order to
     * prevent it from having more even numbers than the threshold.
     *
     * @param arr          the array to be filtered
     * @param threshold    the max number of even numbers it can contain
     * @return
     */
    public static boolean hasEven(int[] arr, int threshold) {
        int count = 0;

        for(int element : arr) {
            if (element % 2 == 0) count++;
        }
        return count <= threshold;
    }

    /**
     * Filters the array by a threshold number in order to
     * prevent it from having more odd numbers than the threshold.
     *
     * @param arr          the array to be filtered
     * @param threshold    the max number of odd numbers it can contain
     * @return
     */
    public static boolean hasOdd(int[] arr, int threshold) {
        int count = 0;

        for (int element : arr) {
            if (element % 2 != 0) count++;
        }
        return count <= threshold;
    }

    /**
     * Filters the array so that it doesn't have
     * more than two contiguous numbers in a row.
     *
     * @param arr           the array to be filtered
     * @param contiguous    the limit of contiguous numbers
     *                      in a row
     * @return
     */
    public static boolean hasContiguous(int[] arr,int contiguous) {
        contiguous = 2;
        int count = 0;

        for(int i = 0; i < arr.length - 1; i++) {
            if((arr[i + 1] - arr[i]) == 1) {
                count++;
            }
        }
        return count < contiguous;
    }

    /**
     * Filters an array to check how many numbers end with the same digit,
     * maximum 3 allowed.
     *
     * @param arr        the array to be filtered
     * @param ending     the amount of numbers ending with the same digit
     * @return
     */
    public static boolean hasSameEnding(int[] arr, int ending) {
        ending = 3;
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            if(arr[i] % 10 == arr[i++] % 10) {
                count++;
            }
        }
        return count <= ending;
    }

    /**
     * Filters an array to check how many numbers are in the same ten,
     * maximum 3 allowed.
     *
     * @param arr      the array to be filtered
     * @param ten      amount of numbers in the same ten allowed
     * @return
     */
    public static boolean inSameTen(int[] arr,int ten) {
        ten = 3;
        int count10 = 0;
        int count20 = 0;
        int count30 = 0;
        int count40 = 0;

        for (int element : arr) {
            if (element / 10 <= 1) {
                count10++;
            } else if (element / 10 <= 2) {
                count20++;
            } else if (element / 10 <= 3) {
                count30++;
            } else if (element / 10 <= 4) {
                count40++;
            }
        }
        return ten >= count10 && ten >= count20
                && ten >= count30 && ten >= count40;
    }
}
