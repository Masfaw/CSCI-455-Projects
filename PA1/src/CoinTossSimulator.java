/**
 * Name         :Matheos Asfaw
 * User ID      :*******
 * course #     :CSCI 455 Section 30100
 * Project #    :1
 * Deadline     : 09/14/2016
 *
 */

/**
 * class CoinTossSimulator
 * 
 * Simulates trials of tossing two coins and allows the user to access the
 * cumulative results.
 * 
 * NOTE: we have provided the public interface for this class.  Do not change
 * the public interface.  You can add private instance variables, constants, 
 * and private methods to the class.  You will also be completing the 
 * implementation of the methods given. 
 * 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */

import java.util.Random;

public class CoinTossSimulator {

   //instance variables holding the number o TwoHeads, TwoTails and HeadTails;
   private int numTwoHeads;
   private int numTwoTails;
   private int numHeadTails;
   private int totalNumTrials;

   // pseudo random number generator.

   Random coin = new Random();

   /**
      Creates a coin toss simulator with no trials done yet.
   */
   public CoinTossSimulator() {
      numHeadTails = 0;
      numTwoHeads = 0;
      numTwoTails = 0;
      totalNumTrials = 0;
   }


   /**
      Runs the simulation for numTrials more trials. Multiple calls to this
      without a reset() between them add these trials to the simulation
      already completed.
      
      @param numTrials  number of trials to for simulation; must be >= 0
    */
   public void run(int numTrials) {

      /* coin.nextInt(2) will give either 0 or 1
        I have assigned 0 to be tails and 1 to be heads
       */
      for (int i = 0; i < numTrials; i++){
         if (coin.nextInt(2)== 0){     // first coin is tails
            if (coin.nextInt(2)== 0 ){ // second coin is tails
               numTwoTails ++;
            }
            else {                     // second coin is heads
               numHeadTails++;
            }
         }
         else {                        // first coin is heads
            if (coin.nextInt(2)== 0){  //Second coin is tails
               numHeadTails++;
            }
            else{                      // second coin is heads
               numTwoHeads++;
            }

         }
      }// end of for loop

      // add the currently run trials to the total tally.
      totalNumTrials+=numTrials;
 
   }


   /**
      Get number of trials performed since last reset.
    @return Returns the total number of times the coin has been tossed.
            Calling reset puts this value back to zero
   */
   public int getNumTrials() {
       return totalNumTrials;

   }


   /**
      Get number of trials that came up two heads since last reset.
    @return Returns the number of twoHeads since last reset.
   */
   public int getTwoHeads() {
       return numTwoHeads;
   }


   /**
     Get number of trials that came up two tails since last reset.
    @return Returns number of TwoTails since last reset.
   */  
   public int getTwoTails() {
       return numTwoTails;
   }


   /**
     Get number of trials that came up one head and one tail since last reset.
    @return Returns number of HeadTails since last reset.
   */
   public int getHeadTails() {
       return numHeadTails;
   }


   /**
      Resets the simulation, so that subsequent runs start from 0 trials done.
    */
   public void reset() {
      numHeadTails = 0;
      numTwoHeads = 0;
      numTwoTails = 0;
      totalNumTrials = 0;
   }

}
