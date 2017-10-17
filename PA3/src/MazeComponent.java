// Name: Matheos Asfaw
// USC loginid: **********
// CS 455 PA3
// Fall 2016

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.swing.JComponent;

/**
   MazeComponent class
   
   A component that displays the maze and path through it if one has been found.
*/
public class MazeComponent extends JComponent
{
   private Maze maze;
   
   private static final int START_X = 10; // where to start drawing maze in frame
   private static final int START_Y = 10;
   private static final int BOX_WIDTH = 20;  // width and height of one maze unit
   private static final int BOX_HEIGHT = 20;
   private static final int INSET = 2;
                    // how much smaller on each side to make entry/exit inner box
    private static final Color EXIT_COLOR = Color.GREEN;
    private static final Color ENTRY_COLOR = Color.YELLOW;
    private static final Color WALL_COLOR = Color.BLACK;
    private static final Color FREE_COLOR = Color.WHITE;
    private static final Color PATH_COLOR = Color.BLUE;
   
   /**
      Constructs the component.
      @param maze   the maze to display
   */
   public MazeComponent(Maze maze) 
   {

       // deep copy will not work for this implementation because of the way MazeFrame is implemented.
       this.maze = maze;
   }

   
   /**
     Draws the current state of maze including the path through it if one has
     been found.
     @param g the graphics context
   */
   public void paintComponent(Graphics g)
   {

      Graphics g2 = (Graphics)g;

       g2.setColor(FREE_COLOR);

        // draw the maze
       for (int i = 0; i < maze.numCols(); i ++){
           for (int j = 0; j < maze.numRows(); j++){
               if (maze.hasWallAt(new MazeCoord(j,i))){
                   g2.setColor(WALL_COLOR);
               }

               g2.fillRect((i * BOX_WIDTH )+ START_X, (j * BOX_HEIGHT) + START_Y,BOX_WIDTH,BOX_HEIGHT);
               g2.setColor(FREE_COLOR);
           }
       }

       //draw entry location
       g2.setColor(ENTRY_COLOR);
       g2.fillRect(maze.getEntryLoc().getCol()* BOX_WIDTH+ START_X + (INSET/2),
               maze.getEntryLoc().getRow() * BOX_HEIGHT + START_Y + (INSET/2),
               BOX_WIDTH-INSET,BOX_HEIGHT-INSET);
       // draw exit location
       g2.setColor(EXIT_COLOR);
       g2.fillRect(maze.getExitLoc().getCol()* BOX_WIDTH+ START_X + (INSET/2),
               maze.getExitLoc().getRow() * BOX_HEIGHT + START_Y + (INSET/2),
               BOX_WIDTH-INSET,BOX_HEIGHT-INSET);

       //Draw a frame around the whole maze
       g2.setColor(WALL_COLOR);
       g2.drawRect(START_X,START_X,BOX_WIDTH* maze.numCols(), BOX_HEIGHT*maze.numRows());

        // Draw the path

       if (maze.getPath().size() > 0 ){
           drawPath(g2);
       }
   }

    /**
     * Helper function that draws the path if it is found.
     * @param g Graphics context passed from the paintCompononet.
     */

    private void drawPath(Graphics g){
        ListIterator<MazeCoord> iterator = maze.getPath().listIterator();


        g.setColor(PATH_COLOR);
        MazeCoord cur = iterator.next();
        while (iterator.hasNext()){
            MazeCoord next = iterator.next();
            g.drawLine((cur.getCol() * BOX_WIDTH)+ (BOX_WIDTH/2) + START_X,cur.getRow() * BOX_HEIGHT + (BOX_HEIGHT/2) + START_Y,
                    next.getCol() * BOX_WIDTH + (BOX_WIDTH/2) + START_X, next.getRow() * BOX_HEIGHT + (BOX_HEIGHT/2) + START_Y);
            cur=next;
        }
    }
   
}



