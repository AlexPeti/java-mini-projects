package gr.aueb.cf.projects;

import java.util.Arrays;

/**
 * Write two methods that involve copying two-dimensional arrays. A shallow copy
 * method that copies a two-dimensional array (simply copies the references from
 * the source array to the new array).
 * And a deep copy method that creates an independent copy
 * of the original array.
 * Write a main method that shows why shallow copy does not work as we would like,
 * since by changing an element of an array which has been shallow copied, it changes
 * the element in the other array as well, since it basically is a single
 * element (after a shallow copy has been made).
 * Also show the case of deep copy. Show that it works as we would like.
 * That is, the data changes do not affect the new copy, which is now
 * independent.
 *
 * @author Alexandros Peteinaris
 */
public class ShallowVsDeepCopyApp {

    public static void main(String[] args) {
        try {
            int[][] shallowSource = {{10,20},{30,40},{50,60}};
            int[][] deepSource = {{1,2},{3,4},{5,6}};

            //Declaring a new array and initializing it with the deep copy method
            int[][] newDeep;
            newDeep = deepCopy(deepSource);
            //Printing the new array shows that it has the same elements as the deepSource array
            System.out.println("This is a copy of the original array: "+ Arrays.deepToString(newDeep));
            //We will change the first element of the created array, and we will display both the new
            //and the original array on the console
            newDeep[0][0] = 100;
            //As we can see after displaying the original array and the new array with the altered first
            //element, the original array has the same elements as before,because we performed a deep copy,
            //thus making the new array independent of the original one. That means that it doesn't
            //reference the same element in the heap as the original array.
            System.out.println("This is the copy of the original array,but with" +
                    " an altered first element: " + Arrays.deepToString(newDeep));
            System.out.println("This is the original array, " +
                    "independent from its copy,thus unchanged: " + Arrays.deepToString(deepSource));

            System.out.println();

            //Declaring a new array
            int[][] newShallow;
            //Initializing the new array by using a shallow copy method
            newShallow = shallowCopy(shallowSource);
            //Displaying the new copy,we can see that it has the same elements as the source array
            System.out.println("This is a shallow copy of the original: " + Arrays.deepToString(newShallow));
            //Altering its first element and displaying it again
            newShallow[0][0] = 9;
            System.out.println("This is the shallow copy of the original," +
                    " with an altered first element: " + Arrays.deepToString(newShallow));
            //Displaying the source array to show that its copy is not independent of the source array,
            //and every change made to the copy or vice versa, will affect both arrays because it actually
            //is one array with two references that both point to it at the heap.
            System.out.println("This is the original array, we notice that it also has an altered " +
                    "first element: " + Arrays.deepToString(shallowSource));

        } catch(NullPointerException e) {
            System.out.println("Invalid input. Check if the source array is null and retry.");
        }
    }

    public static int[][] shallowCopy(int[][] arr) {
        if(arr == null) return null;

        return Arrays.copyOf(arr,arr.length);
    }

    public static int[][] deepCopy(int[][] arr) {
        if(arr == null) return null;

        int[][] newDeep = new int[arr.length][];
        for(int i = 0; i < arr.length; i++) {
            newDeep[i] = arr[i].clone();
        }
        return newDeep;
    }
}
