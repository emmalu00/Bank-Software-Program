/**
* Project6.java
* Emma Lucas
* This program calculates mortgage rates for a bank.
*/

import java.util.*;
import java.io.*;

public class Project6 {
    public static void main (String[] args) throws IOException
    {
        final int P_LOAN = 250000;
        final int P_YEARS = 30;
        final double P_INTEREST = 3.25;
        System.out.println("Big12 Bank of America Mortgage Calculation Software\nDeveloped by Emma Lucas\n");
        Scanner s = new Scanner(System.in);
        System.out.print("Please enter the file name to be used for output (without .txt): ");
        String file = s.nextLine(); 
        System.out.println();
        int selection = 0;
        while (selection != 3)
        {
            selection = selectChoice();
            if (selection == 1)
            {
                System.out.println("\nPROMOTIONAL LOAN...");
                String customerNumber = getCustomerNumber();
                double monthly = calcMonthlyPayment(P_YEARS, P_LOAN, P_INTEREST);
                double total = calcTotalPayment(monthly, P_YEARS);
                displayPayments(customerNumber, P_LOAN, P_YEARS, P_INTEREST, monthly, total);
                writeTo(customerNumber, P_LOAN, P_YEARS, P_INTEREST, monthly, total, file);
            }
            else if (selection == 2)
            {
                System.out.println("\nUNIQUE LOAN...");
                String customerNumber = getCustomerNumber();
                double interest = getInterestRate();
                int term = getLoanTerm();
                int amount = getLoanAmount();
                double monthly = calcMonthlyPayment(term, amount, interest);
                double total = calcTotalPayment(monthly, term);
                displayPayments(customerNumber, amount, term, interest, monthly, total);
                writeTo(customerNumber, amount, term, interest, monthly, total, file);
            }
            else 
            {
                System.out.println("\nFile " + file.concat(".txt") + " created.\n\nEXITING...");
                System.exit(0);
            }
        }
    }

    /**
    * This method gets the selection from the user for the program, only allowing selections 1-3.
    * Selection 1 warrants a promotion loan, selection 2 warrants a unique loan, and selection 3
    * exits the program.
    * @return selection number of user
    */    
    public static int selectChoice()
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Please choose from the following choices below: ");
        System.out.println("1) Promotional Loan ($250,000.00 at 3.25% for 30 years)\n2) Unique Loan (enter in loan values)\n3) Quit (Exit program)");
        int selection = 0;
        while (selection < 1 || selection > 3)
        {
            System.out.print("\nPlease enter your selection (1-3): ");
            selection = Integer.parseInt(s.nextLine());
            if (selection < 1 || selection > 3)
            {
                System.out.println("\tInvalid choice.");
            }
        }
        return selection;
    }

    /**
    * This method gets the customer number of the user. The user must enter a 6-character/digit
    * customer number.
    * @return customer number entered by the user
    */   
    public static String getCustomerNumber()
    {
        Scanner s = new Scanner(System.in);
        String customerNumber = "";
        boolean sixDigits = false;
        boolean validChars = false;
        while (!sixDigits || !validChars)
        {
            System.out.print("Enter the customer number (6-characters and/or digits): ");
            customerNumber = s.nextLine();
            sixDigits = customerNumber.length() == 6;
            validChars = true;
            for (int i = 0; i < customerNumber.length(); ++i)
            {
                if (!Character.isLetterOrDigit(customerNumber.charAt(i)))
                {
                    validChars = false;
                }
            }
            if (!sixDigits || !validChars)
            {
                System.out.println("Invalid customer number. Please re-enter a valid customber number.");
            }
        }
        System.out.println();
        return customerNumber;
    }

    /**
    * This method gets the interest rate from the user. The user must enter an interest rate 
    * between 2.5 and 9.0 inclusive, with or without the percent(%) sign. 
    * @return interest rate entered by user
    */   
    public static double getInterestRate()
    {
        Scanner s = new Scanner(System.in);
        double interestRate = 0.0;
        while (interestRate < 2.5 || interestRate > 9.0)
        {
            System.out.print("Enter yearly interest (2.5%-9.0%): ");
            String input = s.nextLine();
            if (input.charAt(input.length() - 1) == '%')
            {
                interestRate = Double.parseDouble(input.substring(0, input.length() - 1));
            }
            else
            {
                interestRate = Double.parseDouble(input);
            }
            if (interestRate < 2.5 || interestRate > 9.0)
            {
                System.out.println("Invalid interest rate. Please re-enter a valid interest rate.");
            }
        }
        return interestRate;
    }

    /**
    * This method gets the loan term from the user. The user must enter a loan term of 15, 20, 25, 
    * or 30 years, with or without "years".
    * @return loan term entered by user
    */   
    public static int getLoanTerm()
    {
        Scanner s = new Scanner(System.in);
        int loanTerm = 0;
        while (loanTerm != 15 && loanTerm != 20 && loanTerm != 25 && loanTerm != 30) 
        {
            System.out.print("Enter number of years for the loam (15, 20, 25, or 30 years): ");
            loanTerm = s.nextInt();
            String garbage = s.nextLine();
            if (loanTerm != 15 && loanTerm != 20 && loanTerm != 25 && loanTerm != 30)
            {
                System.out.println("Invalid loan term. Please re-enter a valid loan term.");
            }
        }
        return loanTerm;
    }

    /**
    * This method gets the loan amount from the user. The user must enter an loan amount between
    * $100,000 and 1,000,000 inclusive, without the dollar sign or commas. 
    * @return loan amount entered by user
    */   
    public static int getLoanAmount()
    {
        Scanner s = new Scanner(System.in);
        boolean valid = false;
        int loanAmount = 0;
        while (!valid)
        {
            System.out.print("Enter the loan amount without $ or commas ($100,000-$1,000,000): ");
            if (s.hasNextInt())
            {
                loanAmount = Integer.parseInt(s.nextLine());
                valid = (loanAmount >= 100000 && loanAmount <= 1000000000);
            }
            else
            {
                s.nextLine();
                System.out.println("Not a valid number.");
            }
            if (!valid)
            {
                System.out.println("Invalid loan amount. Please re-enter a valid loan amount.");
            }
        }
        return loanAmount;
    }

    /**
    * Calculates the montlhy mortgage payment
    * @param numYears - loan term
    * @param loanAmount - loan amount
    * @param interest - interest rate
    * @return monthly payment
    */
    public static double calcMonthlyPayment(int numYears, int loanAmount, double interest)
    {
        double monthlyInterest = (interest / 12) / 100;
        int numMonths = numYears * 12;
        double num = monthlyInterest * (Math.pow((1 + monthlyInterest), numMonths));
        double denom = (Math.pow((1 + monthlyInterest), numMonths)) - 1;
        double calcMonthlyPayment = loanAmount * (num / denom);
        return calcMonthlyPayment;
    }

    /**
    * Calculates the total mortgage payment
    * @param monthlyInterest - monthly interest
    * @param numYears - loan term
    * @return total payment
    */
    public static double calcTotalPayment(double monthlyPayment, int numYears)
    {
        return monthlyPayment * numYears * 12;
    }

    /**
    * Displays customer information and loan information.
    * @param cusNum - customer number
    * @param loanAmt - loan amount
    * @param loanTerm - loan term
    * @param interest - interest
    * @param monthly - monthly payment
    * @param total - total payment
    */
    public static void displayPayments(String cusNum, double loanAmt, int loanTerm, double interest, double monthly, double total)
    {
        System.out.println("Customer number: " + cusNum);
        System.out.printf("Loan Amount: $%,.2f\nLoan Term: %d years\nInterest Rate: %.2f%%\nMonthly Payment: $%,.2f\nTotal Payment: $%,.2f\n", loanAmt, loanTerm, interest, monthly, total);
        System.out.println();
    }

    /**
    * Writes customer information and loan information to output file.
    * @param cusNum - customer number
    * @param loanAmt - loan amount
    * @param loanTerm - loan term
    * @param interest - interest
    * @param monthly - monthly payment
    * @param total - total payment
    * @param file - file name
    */
    public static void writeTo(String cusNum, double loanAmt, int loanTerm, double interest, double monthly, double total, String file) throws IOException
    {
        File outFile = new File(file.concat(".txt"));
        FileOutputStream fs = new FileOutputStream(outFile, true);
        PrintWriter outFS = new PrintWriter(fs);
        outFS.println("\nCustomer number: " + cusNum);
        outFS.printf("Loan Amount: $%,.2f\nLoan Term: %d years\nInterest Rate: %.2f%%\nMonthly Payment: $%,.2f\nTotal Payment: $%,.2f\n", loanAmt, loanTerm, interest, monthly, total);
        outFS.close();
        System.out.println();
    }
}