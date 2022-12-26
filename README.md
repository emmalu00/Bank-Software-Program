# Bank-Software-Program
# Mortgage Calculation Software
Using the loan amount, loan term, and interest rate, this software program calculates mortgage payments.

When opening the program, a menu is displayed, the user has three options: 
1. `Promotional Loan (50,000 @ 3.25% interest for 30 years)`
1. `Unique Loan (user enters values)`
1. `Quit Program. `

If the user selections option 1 or 2, they are immediately prompted to enter a 6-character/digit customer number. If the user selects option 1, the monthly mortgage payments are automatically calculated. If the user selects option 2, the user is prompted to enter values for the loan amount, loan term, and interest rate. _The program uses error checking to ensure that the program does not crash if the user enters dollar sigs or commas when entering the loan amount, or the percent sign when entering the interest rate._ If the user selects option 3, the program exits. The program will continue to loop until the user selects option 3.

Using all of the relevant information, the program calculates the monthly and total mortgage payments of the user.

For each customer entered into the program, information is displayed and written to an output file in the following format:

`Customer Number: <customer number>`

`Loan Amount: $<loan amount>`

`Loan Term: <loan term> years`

`Interest Rate: <interest rate>%`

`Monthly Payment: <monthly payment>`

`Total Payment: <total payment>`

The program will allow the user to continue calculating and displaying mortgage payments for customers until the user exits the program by selecting option 3 from the menu.

