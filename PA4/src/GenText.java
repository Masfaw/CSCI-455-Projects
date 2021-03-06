

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

// Name: Matheos Asfaw
// USC loginid: ***********
// CS 455 PA4
// Fall 2016

/**
 * GenText Class
 * Program will read in text file and generate text based on the world- level Markov Chain.
 * The genereated text will then be writein to an output file.
 *
 * How to call from command line
 *
 *          java GenText [-d] prefixLength numWords sourceFile outFile
 *
 *  Where [-d] is an optional argument that prints to the console debug statmetns that show the step by step generation
 *  of the text. This argument also sets the random seed to 1. Invariants are listed below and in each .java file for
 *  convenience. The user needs to have permistion to write to outFile else this program will throw an exceptions and
 *  exit.
 */


/*************************************************************
 * Invariants
 *  1. The Prefix length has to be smaller than the source length by at least 1.
 *  2. prefix variable is immutable. each time shiftIn is called a new prefix is created.
 *  3. prefixLength can not be smaller than 1.
 *  4. the source has to have a minimum of one word. source.size() > 0.
 */
public class GenText {


    public static void main(String args[]){
        System.out.println("");
        //First make sure the arguments are correct.
        validate(args);

       try{
           if (args.length == 5){
               run(true,args[1],args[2],args[3],args[4]);
           }
           else {
               run(false,args[0],args[1],args[2],args[3]);
           }
       }
       catch (IOException e ){
           System.out.println("ERROR: IOException");
           System.out.println(e.toString());
           printInstructions();
           System.exit(-6);
       }
    }

    /**
     * Validates the arguments to make sure they are in the correct format
     * and the right parameters have been used.
     * Checks length of arguments passed.
     * checks the if the right kind of arguments are passed( strings are strings and nums are int)
     * @param args array of the command line arguments.
     */
    private static void validate(String args[]){
        if (args.length < 4){
            System.out.println("ERROR: Not enough parameters");
            printInstructions();
            System.exit(-1);
        }

        try {
            if (args.length == 4 ){
                int pre = Integer.parseInt(args[0]);
                int num = Integer.parseInt(args[1]);
                checkNumbers(pre,num);
            }
            else if (args.length ==5 ) {
                if (!args[0].equals("-d")){
                    System.out.println("ERROR: Unknown argument: " + args[0]);
                    printInstructions();
                    System.exit(-4);
                }
                int pre = Integer.parseInt(args[1]);
                int num = Integer.parseInt(args[2]);
                checkNumbers(pre,num);
            }
        }
        catch (NumberFormatException e ){
            System.out.println("ERROR: prefixLength and  numWords have to be ints.");
            printInstructions();
            System.out.println(e.toString());
            System.exit(-2);

        }
    }

    /**
     * Prints instructions on what the input format is for the command line arguments
     */
    private static void printInstructions(){
        System.out.println("Please run the program as follows");
        System.out.println("java GenText [-d] prefixLength numWords sourceFile outFile ");
    }

    /**
     * Makes sure the prefixLength and the numWords are not violating the invariants.
     * numWords has to be greater than 0
     * prefixLength has to be greater than 1
     * @param pre prefixLength from the command line
     * @param num numWords from thecommand line
     */
    private static void checkNumbers(int pre, int num){
        if (pre< 1){
            System.out.println("ERROR: prefixLength can not be less than 1");
            printInstructions();
            System.exit(-3);
        }
        if (num<0){
            System.out.println("ERROR: numWords can not be less than 0 ");
            printInstructions();
            System.exit(-3);
        }
    }

    /**
     * Opens the sources file reads the words in it and creates the RandomTextGenerator and passes all the arguments
     * to generate the text and out puts that text to a file.
     * @param debug debug flag to print to console or not
     * @param pre length of the prefix
     * @param num number of words to be generated by the algorithm
     * @param source input file name
     * @param dest output file name
     * @throws IOException general class that handles the File not found and other IOExceptions that could occur
     *                     such as not having the permission to write to a file.
     */

    private static void run(boolean debug, String pre, String num, String source, String dest)
            throws IOException{

        int prefixLength = Integer.parseInt(pre);
        int numWords = Integer.parseInt(num);

        String inputFileName = source;
        String outputFileName = dest;
        String outText = "";

        File in = new File(inputFileName);
        Scanner inScanner = new Scanner(in);
        File out = new File(outputFileName);
        PrintWriter outStream = new PrintWriter(out);

        ArrayList<String> inText = new ArrayList<>();

        while(inScanner.hasNext()){
            inText.add(inScanner.next());
        }

        if (inText.size()-1 <= prefixLength){
            System.out.println("ERROR: prefixLength can not be equal to or greater"
                    +"than the number of words in the input text file ");
            System.exit(-7);
        }
        RandomTextGenerator generator = new RandomTextGenerator(inText,prefixLength,debug);
        outText = generator.generateText(numWords);
        outStream.println(outText);


        outStream.close();

    }


}
