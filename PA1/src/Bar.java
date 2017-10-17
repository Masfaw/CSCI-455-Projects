/**
 * Name         :Matheos Asfaw
 * User ID      :*******
 * course #     :CSCI 455 Section 30100
 * Project #    :1
 * Deadline     : 09/14/2016
 *
 */

// we added the import statements for you
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * Bar class
 * A labeled bar that can serve as a single bar in a bar graph.
 * The text for the label is centered under the bar.
 *
 * NOTE: we have provided the public interface for this class. Do not change
 * the public interface. You can add private instance variables, constants,
 * and private methods to the class. You will also be completing the
 * implementation of the methods given.
 * 
 */


public class Bar {
    private int labelBottom,barX,barWidth,numUnits;
    private double unitsPerPixel;
    private Color barColor;
    private String barLabel;


   
   /**
      Creates a labeled bar.  You give the height of the bar in application
      units (e.g., population of a particular state), and then a scale for how
      tall to display it on the screen (parameter unitsPerPixel). 
  
      @param bottom  location of the bottom of the label
      @param left  location of the left side of the bar
      @param width  width of the bar
      @param numUnits  height of the bar in application units
      @param unitsPerPixel  how many application units per pixel for height
      @param color  the color of the bar
      @param label  the label at the bottom of the bar
   */
   public Bar(int bottom, int left, int width, int numUnits, 
              double unitsPerPixel, Color color, String label) {


        labelBottom = bottom;
        barX = left;
        barWidth = width;
        barColor = color;
        barLabel = label;
        this.numUnits = numUnits;
        this.unitsPerPixel= unitsPerPixel;
   }

   /**
      Draw the labeled bar. 
      @param g2  the graphics context
   */
   public void draw(Graphics2D g2) {

        final int TOP_PADDING = 20;
        int  widthOfLabel;
        int  heightOfLabel;
        int  barHeight;
        int  barY;


       // Geting the graphical dimentions of the label. Code snippit from the assigments
       //description.

        Font font  = g2.getFont();
        FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D labelBounds = font.getStringBounds(barLabel,context);
        widthOfLabel = (int)labelBounds.getWidth();
        heightOfLabel = (int) labelBounds.getHeight();


       //Bar height is computed using the number of trials multiplied by unitsPerPixel then
       //subtracting the a constant ammount so there is a gap between the top of the bar and
       // the top of the screen.
        barHeight =(int) (numUnits * unitsPerPixel)- TOP_PADDING ;

        barY = labelBottom - heightOfLabel - barHeight;

       if (barHeight > 0 ){
           Rectangle bar = new Rectangle( barX,barY,barWidth,barHeight);
           g2.setColor(barColor);
           g2.fill(bar);
           g2.draw(bar);
       }

        g2.setColor(Color.BLACK);
        g2.drawString(barLabel,barX - (widthOfLabel /4), labelBottom);

   }
}
