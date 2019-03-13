import java.io.Serializable;

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
