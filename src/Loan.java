/* Loan.java
 * Author:  William Craycroft
 * Module:  6
 * Project: Lab 6
 * Description: This class hold data representing a bank loan, and automatically calculates the loan's monthly and
 *              total payments.
 *
 *      Instance variables:
 *          mCustomerName (String) - The customer's full name
 *          mInterestPercentage (double) - The loan's annual interest percentage
 *          mYears (int) - The lifetime of the loan in years
 *          mAmount (double) - The amount of the loan
 *          mDate (String) - The date the loan was issued
 *          mMonthlyPayment (double) - The monthly payment for the loan (calculated)
 *          mTotalPayments (double) - The total payment for the loan (calculated)
 *
 *      Methods:
 *          Constructors
 *              Default constructor sets all variables aside from monthly and total payment to default values
 *              Parameterized constructor taking all variables aside from monthly and total payment (calculated in setters)
 *          setters and getters for all instance variables - interest, years and amount setters will also call both
 *              calculate helper methods below
 *          toString() - returns a String will all the loan information
 *          equals(Object) - returns true if member variables are equal
 *          calculateMonthlyPayment - Helper method: calculates and sets the monthly payment for the loan based on loan
 *              amount, interest rate and number of years
 *          calculateTotalPayments - Helper method calculates the total payment for the loan, based on monthly payment
 *              and number of years
 */

import java.io.Serializable;
import java.util.Objects;

public class Loan implements Serializable {

    // Instance variables
    private String mCustomerName;           // Loan customer's full name
    private double mInterestPercentage;     // Annual interest percentage
    private int mYears;                     // Number of Years
    private double mAmount;                 // Loan amount
    private String mDate;                   // Loan date
    private double mMonthlyPayment;         // Monthly payment
    private double mTotalPayments;          // Total payments

    // Constructors

    // Parameterized constructor - takes in customer name, annual interest percentage, number of years, amount and date.
    public Loan(String customerName, double interestPercentage, int years, double amount, String date) {
        setCustomerName(customerName);
        setInterestPercentage(interestPercentage);
        setYears(years);
        setAmount(amount);
        setDate(date);
    }

    // Default constructor - sets customer name, annual interest percentage, years, amount and date to default values.
    public Loan() {
        this("no name", 0.0, 0, 0.0, "no date");
    }

    // Getters
    public String getCustomerName() {
        return mCustomerName;
    }

    public double getInterestPercentage() {
        return mInterestPercentage;
    }

    public int getYears() {
        return mYears;
    }

    public double getAmount() {
        return mAmount;
    }

    public String getDate() {
        return mDate;
    }

    public double getMonthlyPayment() {
        return mMonthlyPayment;
    }

    public double getTotalPayments() {
        return mTotalPayments;
    }

    // Setters
    public void setCustomerName(String customerName) {
        mCustomerName = customerName;
    }

    public void setInterestPercentage(double interestPercentage) {
        mInterestPercentage = interestPercentage;
        calculateMonthlyPayment();
        calculateTotalPayments();
    }

    public void setYears(int years) {
        mYears = years;
        calculateMonthlyPayment();
        calculateTotalPayments();
    }

    public void setAmount(double amount) {
        mAmount = amount;
        calculateMonthlyPayment();
        calculateTotalPayments();
    }

    public void setDate(String date) {
        mDate = date;
    }


    // equals
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Loan loan = (Loan) o;
        return Double.compare(loan.mInterestPercentage, mInterestPercentage) == 0 &&
                mYears == loan.mYears &&
                Double.compare(loan.mAmount, mAmount) == 0 &&
                Double.compare(loan.mMonthlyPayment, mMonthlyPayment) == 0 &&
                Double.compare(loan.mTotalPayments, mTotalPayments) == 0 &&
                Objects.equals(mCustomerName, loan.mCustomerName) &&
                Objects.equals(mDate, loan.mDate);
    }

    // toString
    public String toString() {
        return "Loan{" +
                "Customer Name='" + mCustomerName + '\'' +
                ", Interest Percentage=" + mInterestPercentage +
                ", Years=" + mYears +
                ", Amount=" + mAmount +
                ", Date='" + mDate + '\'' +
                ", Monthly Payment=" + mMonthlyPayment +
                ", Total Payments=" + mTotalPayments +
                '}';
    }

    // Helper Methods
    // calculateMonthlyPayment - calculates the monthly payment based on loan amount, interest rate and number of years.
    private void calculateMonthlyPayment() {
        // Calculate monthly interest rate
        double interestRate = mInterestPercentage / 1200.0;
        // Calculate and story monthly payment
        mMonthlyPayment =  (mAmount * interestRate) / (1.0 - Math.pow((1.0  + interestRate), -(mYears * 12)));
    }

    // calculateTotalPayments - calculates the total payment for the loan, based on monthly payment and number of years
    public void calculateTotalPayments() {
        mTotalPayments = mMonthlyPayment * mYears * 12.0;
    }

}
