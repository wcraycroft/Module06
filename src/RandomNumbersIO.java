/* RandomNumbersIO.java
 * Author:  William Craycroft
 * Module:  6
 * Project: Homework 6, Project 1
 * Problem Statement: This class demonstrates the ability to read and write primitive data to and from a binary files
 *      using both ObjectOutputStream and RandomAccessFile.
 *
 * Algorithm / Plan:
 *      1. Open file as File object, check if File exists (from previous run)
 *          If exists, delete old file
 *      2. Open file with ObjectOutputStream
 *      3. Write a random integer from 1-10 using Random to file
 *      4. Repeat step 3 10 times total
 *      5. Close stream
 *      6. Open file with ObjectInputStream
 *      7. Read integer from file and store in integer array
 *      8. Repeat for all integers written to file (10)
 *      9. Close stream
 *      10. Sort integer array using selection sort method
 *          Loop through entire array excluding last value
 *              Loop through unsorted portion of array
 *                  Find and store the index of the minimum value
 *                  Swap the minimum value with the first value of unsorted portion of array
 *      11. Print sorted random integers to console
 *      12. Open file using RandomAccessFile (read/write)
 *      13. Clear previous data on file by setting length to 0
 *      14. Write and random integer from 1-10 to file, repeat 10 times total.
 *      15. Move cursor to start of file using seek method
 *      16. Read 10 integer values from file, storing them in array
 *      17. Sort array using selection sort
 *      18. Print sorted random integers to console
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.Random;

public class RandomNumbersIO {

    public static void main(String[] args) {
        // Declarations
        String fileName = "numbers.dat";        // Binary file name, used to store random integers
        File numberFile = new File(fileName);   // File object reference (used to delete old file)
        int[] randomNums = new int[10];         // Array to store random integers read from file
        Random rng = new Random();              // Used to generate random values

        // Object I/O streams
        ObjectOutputStream outStream = null;    // Output stream used to write binary data to file
        ObjectInputStream inStream = null;   // Input stream used to read binary data from file

        // Random Access I/O
        RandomAccessFile randomAccessFile;

        // Check if files exists...
        if (numberFile.exists()) {
            // Delete file
            numberFile.delete();
            System.out.println("Deleting old file " + fileName);
        }

        // Write random numbers to file
        try {
            // Open file
            outStream = new ObjectOutputStream(new FileOutputStream(fileName));

            // Write random 1-10 integers to file
            for (int i = 0; i < randomNums.length; i++) {
                outStream.writeInt(rng.nextInt(10) + 1);
            }
            // Close file
            outStream.close();
        }
        catch (IOException e) {
            System.err.println("Error writing to file " + fileName);
            e.printStackTrace();
            System.exit(0);
        }

        // Read integers from file
        try {
            inStream = new ObjectInputStream(new FileInputStream(fileName));

            // Read integers into array
            for (int i = 0; i < randomNums.length; i++) {
                randomNums[i] = inStream.readInt();
            }
            // Close file
            inStream.close();
        }
        catch (IOException e) {
            System.err.println("Error reading file " + fileName);
            e.printStackTrace();
            System.exit(0);
        }

        // Use selection sort to sort array into ascending order
        selectionSort(randomNums);

        // Display sorted numbers
        System.out.println("Sorted random integers fetched using ObjectOutputStream:");
        for (int current : randomNums) {
            System.out.print(current + " ");
        }


        // Repeat process using Random Access

        // Open file using RandomAccessFile
        try {
            // "rw" since we will be writing to then reading from file
            randomAccessFile = new RandomAccessFile(fileName, "rw");

            // Clear any data currently in file
            randomAccessFile.setLength(0);

            // Write 10 random numbers to file
            for (int i = 0; i < randomNums.length; i++) {
                randomAccessFile.writeInt(rng.nextInt(10) + 1);
            }

            // Move cursor to start of file
            randomAccessFile.seek(0);

            // Read numbers into array
            for (int i = 0; i < randomNums.length; i++) {
                randomNums[i] = randomAccessFile.readInt();
            }
            // Close file
            randomAccessFile.close();

        } catch (IOException e) {
            System.err.println("Error opening file " + fileName);
            e.printStackTrace();
        }

        // Use selection sort to sort array into ascending order
        selectionSort(randomNums);

        // Display sorted numbers
        System.out.println("\nSorted random integers fetched using RandomAccessFile:");
        for (int current : randomNums) {
            System.out.print(current + " ");
        }

    }

    // Returns the minimum index in an array, past the given starting index
    public static int indexOfMin(int[] arr, int startIndex) {
        int min=Integer.MAX_VALUE, minIndex=0;
        // Loop through array, starting at startIndex
        for (int i = startIndex; i < arr.length; i++) {
            // If minimum, set as new minimum and index
            if (arr[i] < min) {
                min = arr[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    // Sorts the passed array using selection sort method
    public static void selectionSort(int[] arr) {
        int minIndex, temp;
        // Loop through array, excluding last value
        for (int i = 0; i < arr.length - 1; i++) {
            // Find minimum index
            minIndex = indexOfMin(arr, i);
            // Swap minimum value with starting value for this loop
            temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

}
