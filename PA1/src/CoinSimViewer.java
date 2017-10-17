/**
 * Name         :Matheos Asfaw
 * User ID      :********
 * course #     :CSCI 455 Section 30100
 * Project #    :1
 * Deadline     : 09/14/2016
 *
 */


/**
 * Contains the main method Prompts for the number of trials,
 * and creates the JFrame containing the CoinSimComponent. Besides CoinSimComponent,
 */


import javax.swing.JFrame;
import java.util.Scanner;

public class CoinSimViewer {
    public static void main (String [] args){

        Scanner input = new Scanner (System.in);
        int numSim;
        CoinTossSimulator toss = new CoinTossSimulator();
        CoinSimComponent simComp;

        System.out.print("Enter number of trials: ");
        numSim = input.nextInt();

        while (numSim <= 0){
            System.out.println("ERROR: Number entered must be greater than 0");
            System.out.print("Enter number of trials: ");
            numSim = input.nextInt();
        }

        toss.run(numSim);
        simComp = new CoinSimComponent(numSim,toss.getTwoHeads(),
                toss.getTwoTails(),toss.getHeadTails());

       // Creating the GUI to display the result.
        JFrame frame = new JFrame();
        frame.setSize(800,500);
        frame.setTitle("CoinSim");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(simComp);

        frame.setVisible(true);
    }

}
