/* PetDemo.java
 * Author:  William Craycroft
 * Module:  6
 * Project: Homework 6, Project 2
 * Problem Statement: This class demonstrates the ability to read and write objects to and from a binary data file using
 *      the ObjectOutputStream.
 *
 * Algorithm / Plan:
 *      1. Prompt user for the file name.
 *      2. Prompt the user whether they would like to read from or write to the file.
 *      3. If writing to file...
 *          Open File object and check if file currently exists.
 *          If exists, open the file using AppendingObjectOutputStream (custom class that overrides header)
 *          Else, open the file using Java's ObjectOutputStream
 *          Prompt user for Pet information (name, age, weight)
 *          Create a new Pet object using user inputs and write Pet object to file
 *          Prompt user if they would like to enter another Pet
 *          If yes, go back to Pet information prompt and repeat
 *          Close file
 *      4. If reading from file...
 *          Open File object and check if file currently exists. Exit if not
 *          Open file for reading
 *          Loop through all data in file until Exception is thrown...
 *              Print Pet information by calling Pet's toString method
 *              After 6 Pets have been read, stop and prompt user to continue
 *          Close file
 *      5. If user input is invalid, print error message and exit.
 */

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PetDemo {

    public static void main(String[] args) {

        // Declarations
        // Pet member variables
        String name;        // The pet's name, input by user
        int age;            //       ... age
        double weight;      //       ... weight

        // File I/O
        String fileName;    // file name input by user to read or write from
        int choice = 0;     // user selection for reading or writing, 1 = write, 2 = read
        String cont;   // user selection to continue writing pets, 'y' = continue
        ObjectOutputStream outputStream = null;     // output stream used to write to file
        ObjectInputStream inputStream = null;       // input stream used to read from file
        File petFile;       // File object used to determine if file exists or not

        Scanner keyboard = new Scanner(System.in);  // used to read console input


        // Prompt user for fileName
        System.out.print("Please enter the file name to be processed: ");
        fileName = keyboard.nextLine();

        // Prompt for file reading or writing
        System.out.println("Please select from the options below:");
        System.out.println("1) Enter pet information into the file.");
        System.out.println("2) Display pet information on file.");
        System.out.print(">> ");
        try {
            choice = keyboard.nextInt();
        }
        catch (InputMismatchException e) {
            System.err.println("Error: Invalid input.");
            System.exit(0);
        }
        // Clear newline char
        keyboard.nextLine();

        // If user selects write (output)...
        if (choice == 1) {

            // Open file
            try {
                // Use File class to see if file exists (open in append mode if it does)
                petFile = new File(fileName);

                if (petFile.exists()) {
                    // use custom AppendingObjectOutputStream to append
                    outputStream = new AppendingObjectOutputStream(new FileOutputStream(fileName, true));
                }
                else {
                    // create new file using default ObjectOutputStream
                    outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
                }

                do {    // Start of pet input loop

                    // Prompt user for Pet information
                    System.out.print("Please enter your pet's name: ");
                    name = keyboard.nextLine();
                    System.out.print("Please enter your pet's age: ");
                    age = keyboard.nextInt();
                    System.out.print("Please enter your pet's weight: ");
                    weight = keyboard.nextDouble();
                    // Clear newline char
                    keyboard.nextLine();

                    // Create new Pet object
                    Pet newPet = new Pet(name, age, weight);

                    // Write pet to File
                    outputStream.writeObject(newPet);

                    // Prompt user if they would like to continue
                    System.out.print("Enter another pet? (Y/N) ");
                    cont = keyboard.nextLine();


                } while (cont.substring(0, 1).equalsIgnoreCase("y"));
                // end of pet input loop

                // Close file
                outputStream.close();
            }
            catch (IOException e) {
                System.err.println("Error writing to file " + fileName);
                e.printStackTrace();
                System.exit(0);
            }

        }
        // If user selects read (input)...
        else if (choice == 2) {

            // Check if file exists, exit if not
            petFile = new File(fileName);
            if (!petFile.exists()) {
                System.err.println("Error: File " + fileName + " does not exist.");
                System.exit(0);
            }

            // Open file for reading
            try {
                inputStream = new ObjectInputStream(new FileInputStream(fileName));

                while (true) {   // Loops until EOFException is thrown
                    // Stop every 6 pets to prompt user for more
                    for (int i = 0; i < 7; i++) {
                        // Get Pet object and print its toString to console
                        System.out.println(inputStream.readObject().toString());
                        System.out.println();
                    }
                    System.out.println("\nPress enter to continue...");
                    keyboard.nextLine();
                }
            }
            catch (EOFException e) {
                System.out.println("File reading complete.");
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.exit(0);
            }
            catch (IOException e) {
                System.err.println("Error reading file " + fileName);
                e.printStackTrace();
                System.exit(0);
            }
            // Close stream
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
            }

        }
        // If user inputs an invalid choice
        else {
            System.err.println("Error: Invalid choice selection.");
            System.exit(0);
        }

        keyboard.close();

    }
}
