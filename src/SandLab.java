import java.awt.*;
import java.util.*;

public class SandLab
{
  public static void main(String[] args)
  {
    SandLab lab = new SandLab(120, 80);
    lab.run();
  }
  
  //add constants for particle types here
  public static final int EMPTY = 0;
  public static final int METAL = 1;
  public static final int SAND = 2;
  public static final int WATER = 3;
  public static final int OIL = 4;
  
  //do not add any more fields
  private int[][] grid;
  private SandDisplay display;
  
  public SandLab(int numRows, int numCols)
  {
    String[] names;
    names = new String[5];
    names[EMPTY] = "Empty";
    names[METAL] = "Metal";
    names[SAND] = "Sand";
    names[WATER] = "Water";
    names[OIL] = "Oil";
    display = new SandDisplay("Falling Sand", numRows, numCols, names);
    
    grid = new int[numRows][numCols];
  }
  
  //called when the user clicks on a location using the given tool
  private void locationClicked(int row, int col, int tool)
  {
      grid[row][col] = tool;
  }

  //copies each element of grid into the display
  public void updateDisplay()
  {
      for(int i = 0; i < grid.length; i++){
          for(int j = 0; j < grid[i].length; j++){
              
              switch(grid[i][j]){
                  
                  case 0:
                      display.setColor(i, j, Color.BLACK);
                      break;
                  case 1:
                      display.setColor(i, j, Color.LIGHT_GRAY);
                      break;                      
                  case 2:
                      display.setColor(i, j, Color.YELLOW);
                      break;
                  case 3:
                      display.setColor(i, j, Color.BLUE);
                      break;
                  case 4:
                      display.setColor(i, j, Color.GRAY);
              }
          }
      }
  }

  //called repeatedly.
  //causes one random particle to maybe do something.
  public void step()
  {
      int[] direction = {-1, 0, 1};
      
      int randomRow = (int)(Math.random() * grid.length);
      int randomCol = (int)(Math.random() * grid[0].length);  
      int randomDir = (int)(Math.random() * direction.length);
      int directionAdd = randomCol + direction[randomDir];
      
      
      if(randomRow < grid.length - 1){
          
          if(randomDir == 1){
           if(grid[randomRow][randomCol] == 3 && grid[randomRow + 1][randomCol] == 0){
               
               grid[randomRow + 1][randomCol] = 3;
               grid[randomRow][randomCol] = 0;
           }
           
           if(grid[randomRow][randomCol] == 4 && grid[randomRow + 1][randomCol] == 0){
               
               grid[randomRow + 1][randomCol] = 4;
               grid[randomRow][randomCol] = 0;
           }
          }
          else{
              if(randomCol < grid[0].length - 2){
                  
                if(grid[randomRow][randomCol] == 3 && grid[randomRow][randomCol + 1] == 0 && randomDir == 2){
                  
                    grid[randomRow][randomCol + 1] = 3;
                    grid[randomRow][randomCol] = 0;
                        }
                
                if(grid[randomRow][randomCol] == 4 && grid[randomRow][randomCol + 1] == 0 && randomDir == 2){
                  
                    grid[randomRow][randomCol + 1] = 4;
                    grid[randomRow][randomCol] = 0;
                        }
              }
              if(randomCol > 1){
                
                if(randomDir == 0 && grid[randomRow][randomCol] == 3 && grid[randomRow][randomCol - 1] == 0){
                  
                    grid[randomRow][randomCol - 1] = 3;
                    grid[randomRow][randomCol] = 0;
                        }
                
                if(randomDir == 0 && grid[randomRow][randomCol] == 4 && grid[randomRow][randomCol - 1] == 0){
                  
                    grid[randomRow][randomCol - 1] = 4;
                    grid[randomRow][randomCol] = 0;
                        }
              }
          }
           if(grid[randomRow][randomCol] == 2 && grid[randomRow + 1][randomCol] == 0){
            
               grid[randomRow + 1][randomCol] = 2;
               grid[randomRow][randomCol] = 0;
           }
      
           if(grid[randomRow][randomCol] == 2 && grid[randomRow + 1][randomCol] == 3){
            
               grid[randomRow + 1][randomCol] = 2;
               grid[randomRow][randomCol] = 3;
           }
           
           if(grid[randomRow][randomCol] == 2 && grid[randomRow + 1][randomCol] == 4){
            
               grid[randomRow + 1][randomCol] = 2;
               grid[randomRow][randomCol] = 4;
           }
           
           if(grid[randomRow][randomCol] == 3 && grid[randomRow + 1][randomCol] == 4){
            
               grid[randomRow + 1][randomCol] = 3;
               grid[randomRow][randomCol] = 4;
           }
      }
  }
  
  //do not modify
  public void run()
  {
    while (true)
    {
      for (int i = 0; i < display.getSpeed(); i++)
        step();
      updateDisplay();
      display.repaint();
      display.pause(1);  //wait for redrawing and for mouse
      int[] mouseLoc = display.getMouseLocation();
      if (mouseLoc != null)  //test if mouse clicked
        locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
    }
  }
}
