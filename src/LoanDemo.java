/* LoanDemo.java
 * Author:  William Craycroft
 * Module:  6
 * Project: Lab 6
 * Problem Statement: This class demonstrates the ability to read and write Loan objects to and from a binary file using
 *      the ObjectInputStream, ObjectOutputStream and a custom AppendingObjectOutputStream.
 *
 * Algorithm / Plan:
 *      1. Create 3 Loan objects using parameterized constructor
 *      2. Open file using ObjectOutputStream
 *      3. Write 3 Loans to file
 *      4. Close output stream
 *      5. Create 2 Loan objects using default constructor and setters
 *      6. Open file using AppendingObjectOutputStream(custom class, overrides header) with appending set to true
 *      7. Write remaining 2 Loans to file
 *      8. Close appending output stream
 *      9. Open file using ObjectInputStream
 *      10. Loop through all data in file (until EOFException is thrown)
 *          For each loan object found, print Loan information to console
 *          Add loan amount, monthly payment and total payment to a running sum stored for each value
 *      11. Close input stream
 *      12. Print amount, monthly payment and total payment sums to console.
 */

import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class LoanDemo {

    public static void main (String[] args) {

        // Declarations
        Loan[] loans = new Loan[5];             // array of Loan objects
        String fileName = "loanBinary.dat";     // binary file to be written to then read from
        ObjectOutputStream outStream = null;    // output stream used to write first 3 objects to file
        ObjectInputStream inStream = null;      // input stream used to read objects from binary file
        AppendingObjectOutputStream appendStream = null;    // output stream used to append last 2 objects to file
        NumberFormat currency = NumberFormat.getCurrencyInstance();   // Currency formatter
        DecimalFormat oneDP = new DecimalFormat("0.0");       // Decimal formatter (used for percentage display)

        // Output report sums
        double amountSum = 0;   // Running total for all loan amounts
        double monthlySum = 0;  // Running total for all monthly payments
        double totalSum = 0;    // Running total for all loan payments


        // Instantiate 3 Loan objects using parameterized constructor
        loans[0] = new Loan("Bob Smith", 6.5, 30, 318000.0, "Sep 1, 2015");
        loans[1] = new Loan("Alicia Herman", 4.2, 15, 248000.0, "Oct 15, 2013");
        loans[2] = new Loan("Julie Franciosa", 8.5, 10, 30000.0, "Apr 14, 2010");

        // Open binary file with output stream
        try {
            outStream = new ObjectOutputStream(new FileOutputStream(fileName));

            // Write three objects to binary file
            for (int i = 0; i < 3; i ++) {
                outStream.writeObject(loans[i]);
            }
            // Close stream
            outStream.close();
        }
        catch (IOException e) {
            System.err.println("Error writing to file " + fileName);
            e.printStackTrace();
            System.exit(0);
        }

        // Instantiate Loan objects using default constructor and setters
        loans[3] = new Loan();
        loans[3].setCustomerName("Julio Quiros");
        loans[3].setInterestPercentage(15.0);
        loans[3].setYears(3);
        loans[3].setAmount(50000.0);
        loans[3].setDate("June 23, 2017");

        loans[4] = new Loan();
        loans[4].setCustomerName("Frank Larsen");
        loans[4].setInterestPercentage(8.9);
        loans[4].setYears(5);
        loans[4].setAmount(23000.0);
        loans[4].setDate("Mar 8, 2016");

        // Append objects to stream using AppendObjectOutputStream (custom override class)
        try {
            // Set append to true
            appendStream = new AppendingObjectOutputStream(new FileOutputStream(fileName, true));

            // Write last two objects
            appendStream.writeObject(loans[3]);
            appendStream.writeObject(loans[4]);
            // Close stream
            appendStream.close();
        }
        catch (IOException e) {
            System.err.println("Error appending file " + fileName);
            e.printStackTrace();
            System.exit(0);
        }

        // Read objects from file and display in report

        // Report Header
        System.out.println("                  Annual                     Monthly      Total                  ");
        System.out.println(" Customer Name    Prcnt  Yrs   Loan-Amount   Payment   Loan Payments   Loan-Date ");
        System.out.println("----------------  -----  ---  ------------  ---------  -------------  -----------");

        // Row data
        // Instantiate input stream
        try {
            inStream = new ObjectInputStream(new FileInputStream(fileName));

            // Read each loan object, print formatted row, add payments to totals (will throw EOFException when done)
            while (true) {
                // Get loan object from file
                Loan loan = (Loan) inStream.readObject();
                // Print row
                System.out.printf("%-16s  %5s  %3d  %12s  %9s  %13s  %-11s\n",
                        loan.getCustomerName(),
                        oneDP.format(loan.getInterestPercentage()),
                        loan.getYears(),
                        currency.format(loan.getAmount()),
                        currency.format(loan.getMonthlyPayment()),
                        currency.format(loan.getTotalPayments()),
                        loan.getDate());
                // Add to totals sums
                amountSum += loan.getAmount();
                monthlySum += loan.getMonthlyPayment();
                totalSum += loan.getTotalPayments();
            }

        }
        catch (EOFException e) {
            // Expected behavior, no action necessary.
        }
        catch (FileNotFoundException e) {
            System.err.println("Error opening file " + fileName);
            e.printStackTrace();
            System.exit(0);
        }
        catch (ClassNotFoundException e) {
            System.err.println("Error reading object of class " + Loan.class.getName());
            e.printStackTrace();
            System.exit(0);
        }
        catch (IOException e) {
            System.out.println("Error reading file " + fileName);
            e.printStackTrace();
            System.exit(0);
        }

        // Close input stream
        try {
            inStream.close();
        } catch (IOException e) {
            System.out.println("Error closing file " + fileName);
            e.printStackTrace();
            System.exit(0);
        }

        // Print Totals
        System.out.printf("%42s  %9s  %13s\n", "============", "=========", "=============");
        System.out.printf("%42s  %9s  %13s\n",
                currency.format(amountSum),
                currency.format(monthlySum),
                currency.format(totalSum));

    }
}
