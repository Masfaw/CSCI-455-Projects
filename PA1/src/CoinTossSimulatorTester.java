/**
 * Name         :Matheos Asfaw
 * User ID      :******
 * course #     :CSCI 455 Section 30100
 * Project #    :1
 * Deadline     : 09/14/2016
 *
 */

/**
 * A program to test your CoinTossSimulator class independently from its use
 * in the CoinTossViewer program. It will have its own main method.*
 */


public class CoinTossSimulatorTester {

  //  private static CoinTossSimulator toss = new CoinTossSimulator();

    public static void main(String [] args){

        CoinTossSimulator toss = new CoinTossSimulator();

        System.out.println("After constructor:");
        output(toss);

        toss.run(1);
        System.out.println("After run(1):");
        output(toss);

        toss.run(10);
        System.out.println("After run(10):");
        output(toss);

        toss.run(100);
        System.out.println("After run(100):");
        output(toss);

        toss.reset();
        System.out.println("After reset:");
        output(toss);

        toss.run(1000);
        System.out.println("After run(1000):");
        output(toss);

        toss.reset();
        System.out.println("After reset:");
        output(toss);

        toss.run(100000);
        System.out.println("After run(100000):");
        output(toss);

        toss.reset();
        System.out.println("After reset:");
        output(toss);

        toss.run(2147483647);
        System.out.println("After run(2147483647):");
        output(toss);


        toss.run(1);
        System.out.println("After run(1):");
        output(toss);

        // These are commented out since they take a long time to run.
        /*
         toss.run(1000000000);
         System.out.println("After run(1000000000):");
         output(toss);

         // if i add 1 to this number the program wont compile.
         toss.run(2147483647);
         System.out.println("After run(2147483647):");
         output(toss);

       */
    }

    /**
     * Prints out the current values of instance variables of the CoinTossSimulator class
     *
     */
    public static void output(CoinTossSimulator toss){
     //   System.out.println("After run(" + num + "):");
        System.out.println("Number of trials: " + toss.getNumTrials());
        System.out.println("Two-head tosses : " + toss.getTwoHeads());
        System.out.println("Two-tail tosses : " + toss.getTwoTails());
        System.out.println("One-head one-tail tosses : " + toss.getHeadTails());
        System.out.print("Tosses add up correctly? ");
        System.out.println(toss.getNumTrials() == (toss.getHeadTails() +
                            toss.getTwoHeads() + toss.getTwoTails()));
        System.out.println();
    }
}
