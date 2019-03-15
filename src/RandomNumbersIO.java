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


    public static void selectionSort(int[] array) {
        // Declarations
        int indexMin;      // stores the index of the unsorted minimum value

        // Loop through array, excluding last value
        for (int i = 0; i < array.length - 1; i++) {
            indexMin = i;
            // Loop through unsorted section of array
            for (int j = i + 1; j < array.length; j++) {
                // If current value is smaller than the minimum value
                if (array[j] < array[indexMin]) {
                    // Set the minimum index to current index
                    indexMin = j;
                }
                // Swap minimum value with first element
                int temp = array[indexMin];
                array[indexMin] = array[i];
                array[i] = temp;
            }   // end of unsorted for loop

        }   // end of full array for loop
    }
}
