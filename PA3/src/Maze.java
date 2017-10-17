// Name: Matheos Asfaw
// USC loginid: ***********
// CS 455 PA3
// Fall 2016

import sun.awt.image.ImageWatched;

import java.util.LinkedList;


/**
   Maze class

   Stores information about a maze and can find a path through the maze
   (if there is one).
   
   Assumptions about structure of the maze, as given in mazeData, startLoc, and endLoc
   (parameters to constructor), and the path:
     -- no outer walls given in mazeData -- search assumes there is a virtual 
        border around the maze (i.e., the maze path can't go outside of the maze
        boundaries)
     -- start location for a path is maze coordinate startLoc
     -- exit location is maze coordinate exitLoc
     -- mazeData input is a 2D array of booleans, where true means there is a wall
        at that location, and false means there isn't (see public FREE / WALL 
        constants below) 
     -- in mazeData the first index indicates the row. e.g., mazeData[row][col]
     -- only travel in 4 compass directions (no diagonal paths)
     -- can't travel through walls
 */

public class Maze {
   
    public static final boolean FREE = false;
    public static final boolean WALL = true;


    // **************************************************************
    //  PRIVATE INSTANCE VARIABLE(S)

    /*
        * Invariants :
        * 1.a path is return if and only if there is a path to the exit of the maze.
        *   otherwise an empty list is returned
        * 2.pathIsFound boolean is used in getPath() to check if an empty list or a path should be returned.
        * 3.Maze is 2D array where walls are marked as true and free space is marked as false
        * 4.Visited will look exactly link the maze when the constructor is called.
        * 5.once the search starts every node that is visited will be marked true.
        * 5. startLoc and endLoc will hold the staring and ending locations of the maze.
        *
        */

    private LinkedList<MazeCoord> path;

    private boolean pathIsFound = false;

    private boolean [][] visited;
    private boolean [][] maze;

    private MazeCoord startLoc;
    private MazeCoord endLoc;


   
  

   /**
      Constructs a maze.
      @param mazeData the maze to search.  See general Maze comments for what
      goes in this array.
      @param startLoc the location in maze to start the search (not necessarily on an edge)
      @param endLoc the "exit" location of the maze (not necessarily on an edge)
      PRE: 0 <= startLoc.getRow() < mazeData.length and 0 <= startLoc.getCol() < mazeData[0].length
         and 0 <= endLoc.getRow() < mazeData.length and 0 <= endLoc.getCol() < mazeData[0].length

    */
   public Maze(boolean[][] mazeData, MazeCoord startLoc, MazeCoord endLoc){

       //make a copy of the 2D array so the client who called this constructor doesnt have access to a private data
       maze = new boolean[mazeData.length][mazeData[0].length];

       //create 2D array to keep track of the visited locations
       visited = new boolean[mazeData.length][mazeData[0].length];

       for (int i = 0; i < mazeData.length;i++){
           for (int j = 0; j < mazeData[0].length; j++ ){
               maze[i][j] = mazeData[i][j];
               visited[i][j] = mazeData[i][j]; // this is so that the walls will be read as visited.
           }
       }
       // making deep copy of the start and end loc
       this.startLoc = new MazeCoord(startLoc.getRow(),startLoc.getCol());
       this.endLoc = new MazeCoord(endLoc.getRow(),endLoc.getCol());
       path = new LinkedList<MazeCoord>();
   }


   /**
   Returns the number of rows in the maze
   @return number of rows
   */
   public int numRows() {
      return maze.length;
   }

   
   /**
   Returns the number of columns in the maze
   @return number of columns
   */   
   public int numCols() {
      return maze[0].length;
   } 
 
   
   /**
      Returns true iff there is a wall at this location
      @param loc the location in maze coordinates
      @return whether there is a wall here
      PRE: 0 <= loc.getRow() < numRows() and 0 <= loc.getCol() < numCols()
   */
   public boolean hasWallAt(MazeCoord loc) {

      return maze[loc.getRow()] [loc.getCol()];
   }
   

   /**
      Returns the entry location of this maze.
    */
   public MazeCoord getEntryLoc() {

        return  new MazeCoord(startLoc.getRow(),startLoc.getCol());
   }
   
   
   /**
   Returns the exit location of this maze.
   */
   public MazeCoord getExitLoc() {
     return new MazeCoord(endLoc.getRow(),endLoc.getCol());
   }

   
   /**
      Returns the path through the maze. First element is starting location, and
      last element is exit location.  If there was not path, or if this is called
      before search, returns empty list.

      @return the maze path
    */
   public LinkedList<MazeCoord> getPath() {

       if (pathIsFound){
           return new LinkedList<MazeCoord>(path);
       }
      return new LinkedList<MazeCoord>();
   }


   /**
      Find a path through the maze if there is one.  Client can access the
      path found via getPath method.
      @return whether path was found.
    */
   public boolean search()  {
       boolean found = search(startLoc.getRow(),startLoc.getCol());

        if (found){
            path.add(0,startLoc);
            pathIsFound = true;
        }
      return found;

   }

    /**
     * Hellper recursive function that searches for a path through a maze
     * @param row Starting row index
     * @param col Starting coloumn index
     * @return wheather path was found or not.
     */
    private  boolean search(int row, int col){

        MazeCoord[] directions = {
            new MazeCoord(row +1,col),
            new MazeCoord(row -1,col),
            new MazeCoord(row,col +1),
            new MazeCoord(row,col -1),
        };

        boolean found = false;

        //check if the path has already been found.
        // this is to save time if search is called again after path has been found.
        if (pathIsFound){
            return true;
        }

        if (maze[row][col]){
            return false;
        }
        if (visited[row][col]){
            return false;
        }
        if (endLoc.getCol() == col && endLoc.getRow() == row){
            return true;
        }

        // mark location as visited
        visited[row][col] = true;

        for (int i =0 ; i < directions.length; i ++){

           if (directions[i].getRow() >=0 && directions[i].getRow() < maze.length &&
                   directions[i].getCol() >= 0 && directions[i].getCol() < maze[0].length &&
                   !visited[directions[i].getRow()] [directions[i].getCol()]){

               found = search(directions[i].getRow(),directions[i].getCol());
               if (found){
                   path.add(0,directions[i]);
                   return found;
               }
           }
        }

        // return false since all options didnt pan out.
        return false;

    }

}
