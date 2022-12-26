package gr.aueb.cf.projects;

/**
 * Let an array of n integers. Then the sub-array maximum sum is the contiguous
 * sub-array with the largest sum.
 * Design a linear algorithm with O(n) complexity to solve the above problem.
 * For example, if we have the array {−2, 1, −3, 4, −1, 2, 1, −5, 4}
 * then the contiguous sub-array with the maximum sum is {4, −1, 2, 1},
 * and its sum is 6.
 * Give a three-part solution of the following form:
 * (a) Describe your algorithm clearly.
 * (b) Write the code in Java.
 * (c) Show that the time complexity is O(n).
 */
public class MaxSumSubArrayApp {

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        System.out.println(maxSubArraySum(arr));
    }

    public static int maxSubArraySum (int[] arr) {
        /*
         This method declares and initializes two variables,one of which tracks
         the sum of the current contiguous sub-array and the other, the max sum of
         any contiguous sub-array so far.
         The method iterates over the elements of the array using an enhanced for loop.
         For each element, it adds the element to the current sum and updates the maximum
         sum, if the current sum is higher than the previous max sum.
         If the current sum becomes negative, the method resets the current sum to zero.
         Finally, it returns the max sum of the contiguous sub-array.
         */

        //Declaring the two variables to track current and max sum
        int sum = 0;
        int maxSum = 0;

        //Running an enhanced for loop to iterate over the elements of the array
        for (int element : arr) {
            //The current sum adds every element it encounters
            sum += element;

            //If the current sum is higher than the previous max sum,then the current sum
            //becomes the max sum
            if (sum > maxSum) {
                maxSum = sum;
            }
            //If the current sum becomes negative, then the current sum is 0.
            if (sum < 0) {
                sum = 0;
            }
        }
        return maxSum;
        /*
         The time complexity of the above program is O(n), because it iterates once
         over each element, and it scales linearly with the size of the input.
        */
    }
}
