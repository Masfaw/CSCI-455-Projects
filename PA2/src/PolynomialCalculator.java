// Name: Matheos Asfaw
// USC loginid: ******
// CS 455 PA2
// Fall 2016

import java.util.ArrayList;
import java.util.Scanner;

/**
 * An interactive program that lets the user manipulate an array of 10 Polynomials.
 */

public class PolynomialCalculator {
    //Number of Polynomials in the array , put here in case the size of the array is to be changed later.
    private static final int  NUM_OF_POLY = 10;

    //Various commands the user cant enter.
    private static final String QUIT_CMD = "quit";
    private static final String ADD_CMD = "add";
    private static final String PRINT_CMD = "print";
    private static final String EVAL_CMD = "eval";
    private static final String CREATE_CMD = "create";
    private static final String HELP_CMD = "help";


    public static void main (String args []){
        Polynomial [] polynomials = new Polynomial[NUM_OF_POLY];
        Scanner input = new Scanner(System.in), line;
        String command, arguments;

        initPolynomials(polynomials);

        printMenu();
        System.out.print("cmd> ");
        line = new Scanner(input.nextLine());
        command = line.next();

        while (!((command.toLowerCase()).equals(QUIT_CMD))){

            arguments = "";
            if (line.hasNext()) {
                arguments = line.nextLine();
            }

            if ((command.toLowerCase()).equals(HELP_CMD)){
                printMenu();
            }else if ((command.toLowerCase()).equals(ADD_CMD)){ // ADD
                add(arguments,polynomials);
            }else if ((command.toLowerCase()).equals(PRINT_CMD)){ // PRINT
                printPoly(arguments,polynomials);
            }else if ((command.toLowerCase()).equals(EVAL_CMD)){ // EVAL
                eval(arguments,polynomials);
            }else if ((command.toLowerCase()).equals(CREATE_CMD)){ // CREATE
                create(arguments,polynomials);
            } else {
                System.out.println("ERROR: Illegal command.  Type 'help' for command options.");
            }
            System.out.print("cmd> ");
            line = new Scanner(input.nextLine());
            command = line.next();
        }
        System.out.println("Exiting calculator.");
    }

    private static void initPolynomials(Polynomial [] polynomials){

        for (int i = 0; i < polynomials.length;i++){
            polynomials[i] = new Polynomial();
        }

    }

    /**
     * Checks if the argument passed in is an empty string
     * @param argument parsed input string from the user.
     * @return true if its not empty false otherwise.
     */
    private static boolean isValidArgs(String argument){
        if (argument.equals("")){
            System.out.println("ERROR: index not entered");
            return false;
        }else {
            return true;
        }
    }
    /**
     * Prints the menu to show the list of commands. Is called when the user enters the help command.
     */
    private static void printMenu(){
        System.out.println("Welcome to Polynomial Calculator.");
        System.out.println("Here are the list of commands: ");
        System.out.println("1. create ( int 0 -9) eg. create 4");
        System.out.println("2. print ( int 0 -9) eg. print 4");
        System.out.println("3. eval ( int 0 -9) eg. eval 4");
        System.out.println("4. add ( int 0 -9) eg. add 4 3 1.");
        System.out.println("5. help -- brings up this menu again.");
        System.out.println("6. quit -- exit the program.");
    }

    /**
     * Adds two polynomials and places the result in a third one.
     * @param input String holding the indexes of the additives and result, in this format sum additive1 additive2.
     * @param polynomials The instance array that holds the Polynomials
     */
    private static void add (String input, Polynomial [] polynomials){
        if (!isValidArgs(input)){
            return;
        }
        int sumIndex, addIndex1, addIndex2;
        Scanner line = new Scanner(input);
        if (line.hasNextInt()){
            sumIndex = line.nextInt();
            if (line.hasNextInt() && sumIndex >= 0 && sumIndex < NUM_OF_POLY){
                addIndex1 = line.nextInt();
                if (line.hasNextInt() && addIndex1 >= 0 && addIndex1 < NUM_OF_POLY){
                    addIndex2 = line.nextInt();
                     if (addIndex2 >= 0 && addIndex2 < NUM_OF_POLY){
                         polynomials[sumIndex] = polynomials[addIndex1].add(polynomials[addIndex2]);
                     }
                     else {
                         System.out.println("ERROR: not enough indexes entered."+
                                 "Or invalid index numbers  must be (0 - 9)");
                     }
                }
                else {
                    System.out.println("ERROR: not enough indexes entered."+
                            "Or invalid index numbers  must be (0 - 9) ");
                }
            }
            else {
                System.out.println("ERROR: not enough indexes entered."+
                        "Or invalid index numbers  must be (0 - 9)");
            }
        }
        else {
            System.out.println("ERROR: not enough indexes entered. "+
                    "Or invalid index numbers  must be (0 - 9)");
        }
    }

    /**
     * Calls the toFormatedString funcion and prints out the string for a Polynomial whose index is passed into input.
     * @param input Sting that holds the index for the array of Polynomials
     * @param polynomials The instance array that holds the Polynomials
     */
    private static void printPoly(String input, Polynomial []  polynomials){

        if (!isValidArgs(input)){
            return;
        }
        Scanner line = new Scanner(input);
        if (line.hasNextInt()){
           System.out.println(polynomials[line.nextInt()].toFormattedString());
        }
       else {
            System.out.println("ERROR: no index was entered. Please try again.");

        }
    }

    /**
     * Creates a polynomial from the given index and Coef-power pairs.
     * @param command holds the index for the new Polynomial hols the Coef-power pairs
     * @param polynomials The instance array that holds the Polynomials
     */
    private static void create(String command, Polynomial []  polynomials){

        int index, expon=0;
        Polynomial poly = new Polynomial();
        Scanner input = new Scanner(System.in);
        Scanner line = new Scanner(command);
        double coef=0;

        if (!isValidArgs(command)){ // Checks if the string passed contains Int.
            return;
        }

        if (line.hasNextInt()){
            index = line.nextInt();
            if (index < NUM_OF_POLY && index >= 0){
                System.out.println("Enter a space-separated sequence of coeff-power pairs terminated by <nl>");
                line = new Scanner(input.nextLine());
                while (line.hasNextInt() || line.hasNextDouble()){
                    if (line.hasNextDouble()){
                        coef = line.nextDouble();
                    }
                    if (line. hasNextInt()) {
                        expon = line.nextInt();
                        if (expon < 0) {
                            System.out.println("WARNING: Negative exponent detected. Using the Absolute value.");
                            expon = Math.abs(expon);
                        }
                        poly = poly.add(new Polynomial(new Term(coef,expon)));
                    }
                    else {
                        System.out.println("WARNING: No exponent detected. coef "+ coef +
                                " ignored. Make sure to have coeff-power pairs " );
                    }
                }
                polynomials[index] = poly;
            }
            else {
                System.out.println("ERROR: illegal index for a poly.  must be between 0 and 9, inclusive");
            }
        }else {
            System.out.println("ERROR: no index was entered. Please try again.");
        }
    }

    /**
     * Outpurs the value of the polynomial evaluated with a given x.
     * @param command Holds the index and value of x for the polynomial to be evaluated.
     * @param polynomials The instance array that holds the Polynomials
     */
    private static void eval(String command, Polynomial []  polynomials ){
        Scanner line = new Scanner(command);
        Scanner input  = new Scanner(System.in);
        int index;

        if (!isValidArgs(command)){
            return;
        }
        if (line.hasNextInt()){
            index = line.nextInt();
            if (index>= 0 && index < NUM_OF_POLY){
                System.out.print("Enter a floating point value for x: ");
                if (input.hasNextInt()){
                    System.out.println( polynomials[index].eval(input.nextInt()));
                }
            }
            else {
                System.out.println("ERROR: illegal index for a poly.  must be between 0 and 9, inclusive");
            }
        }
        else {
            System.out.println("ERROR: not enough indexes entered. ");
        }

    }
}
