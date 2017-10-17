/**
 * Name         :Matheos Asfaw
 * User ID      :*******
 * course #     :CSCI 455 Section 30100
 * Project #    :1
 * Deadline     : 09/14/2016
 *
 */

import javax.swing.JComponent;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * Constructor initializes any necessary data and runs the simulation.
 * Overrides paintComponent to draw the bar graph,
 * using Bar objects for each bar in the graph.
 * This class uses the CoinTossSimulator and Bar class.
 */
public class CoinSimComponent extends JComponent {

    private int numberOfTrials;
    private int numberOfTwoHeads;
    private int numberOfTwoTails;
    private int numberOfHeadTails;


    // Calculating the percenatges and creating the string for the label.
    int twoHeadsPercnt, twoTailsPercnt, headTailPercnt;

    // Label strings
    String twoHeadsStr;
    String twoTailsStr;
    String headTailStr;

    // Color Constants
    final Color TWO_HEADS_COLOR = Color.RED;
    final Color TWO_TAILS_COLOR = Color.BLUE;
    final Color HEAD_TAILS_COLOR = Color.GREEN;

    public CoinSimComponent(int numberOfTrials, int numberOfTwoHeads, int numberOfTwoTails, int numberOfHeadTails){
        this.numberOfTrials = numberOfTrials;
        this.numberOfTwoHeads = numberOfTwoHeads;
        this.numberOfTwoTails = numberOfTwoTails;
        this.numberOfHeadTails = numberOfHeadTails;

        twoHeadsPercnt = (int) Math.round((numberOfTwoHeads/ (double) numberOfTrials)* 100) ;
        twoTailsPercnt = (int) Math.round((numberOfTwoTails/ (double) numberOfTrials)* 100 ) ;
        headTailPercnt = (int) Math.round((numberOfHeadTails/ (double) numberOfTrials)* 100) ;

        twoHeadsStr = "Two Heads: " + numberOfTwoHeads + " (" + twoHeadsPercnt + "%)";
        twoTailsStr = "Two Tails: " + numberOfTwoTails + " (" + twoTailsPercnt + "%)";
        headTailStr = "A Head and a Tail: " + numberOfHeadTails + " (" + headTailPercnt + "%)";
    }
    @Override
    public void paintComponent(Graphics g){

        // Recovering the Graphics 2d. This is from the book.
        Graphics2D g2 = ( Graphics2D) g;

        int heightOfLabel;
        double unitsPerPixel;

        // location of the rectangle
        final int BAR_WIDTH = 50;
        final int BOTTOM_PADDING = getHeight() - 20;     // this is used to locate the bottom of the text label
        int twoHeadsLeft,twoTailsLeft,headTailLeft;

        //Bars
        Bar twoHeadsBar;
        Bar twoTailsBar;
        Bar headTailsBar;

        // The X-coordinate of the bars is relative to the width of the window
        headTailLeft = (getWidth()/2) - (BAR_WIDTH/2);
        twoHeadsLeft = ( getWidth()/4 ) - (BAR_WIDTH/2)  ;
        twoTailsLeft = ( 3 * getWidth() /4) - (BAR_WIDTH/2);

        // we need to find the height of the labal to adjust the height of the bar.
        //Since all the labels will be the same height only one was used.
        Font font  = g2.getFont();
        FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D labelBounds = font.getStringBounds(twoHeadsStr,context);
        heightOfLabel = (int) labelBounds.getHeight();


        unitsPerPixel = (getHeight() - heightOfLabel - (getHeight() - BOTTOM_PADDING))
                        / (double) numberOfTrials;

        twoHeadsBar = new Bar (BOTTOM_PADDING,twoHeadsLeft,BAR_WIDTH,
                numberOfTwoHeads,unitsPerPixel,TWO_HEADS_COLOR,twoHeadsStr);
        twoTailsBar = new Bar (BOTTOM_PADDING,twoTailsLeft,BAR_WIDTH,
                numberOfTwoTails,unitsPerPixel,TWO_TAILS_COLOR,twoTailsStr);
        headTailsBar = new Bar (BOTTOM_PADDING, headTailLeft,BAR_WIDTH,
                numberOfHeadTails,unitsPerPixel,HEAD_TAILS_COLOR,headTailStr);

        twoHeadsBar.draw(g2);
        twoTailsBar.draw(g2);
        headTailsBar.draw(g2);

    }

}
