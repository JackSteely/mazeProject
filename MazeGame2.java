//This is the second part of the project where I created a maze and had to guide a cyan square out of it. This contains the needed additions to part I By Jack Steely May 13, 2021
import java.util.*;
import java.text.*;
import java.io.*;
import javafx.application.*;
import javafx.event.*;
import javafx.stage.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.animation.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import java.net.*;
import javafx.geometry.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class MazeGame2 extends Application
{
   
   //PART ONE
      //Important variables.
      File mazeNum = new File("mazeFile.txt"); //This is the file itself.
      int [][] mazeNumArray = new int [21][21]; //This is the array that takes the numbers from the file so the maze can be made.
      MazeCanvas maze = new MazeCanvas(); //This is the maze itself.
      int playerX,playerY; //These are for the cyan block's coordinates whenever arrows are pressed.
      

 
      //Start method.
      public void start(Stage stage)
      {
      //Put everything in the try block in case the file element isn't found.
      try
      {
         FlowPane root = new FlowPane();
         root.setAlignment(Pos.CENTER);
         
         //Make sure the maze works, then add it.
         maze.setOnKeyPressed(new KeyHandler());
         root.getChildren().add(maze);
         
 
         Scene scene = new Scene(root,525,525);
         stage.setScene(scene);
         stage.setTitle("Maze Escape");
         stage.show();
         
         maze.requestFocus();
   
      } catch(NoSuchElementException nsee)
      {
      
      }
      }
      
      //Main method.
      public static void main(String[]args)
      {
         launch(args);
      }
      
      //Make the canvas for the maze.
      public class MazeCanvas extends Canvas
      {
         //This variables were added in part 2.
         private int x;
         private int y;
         public MazeCanvas()
         {
            super(525,525);
         
            GraphicsContext gc = getGraphicsContext2D();
            count();
            draw(gc);
         }
         
         //The draw method will make the project easier.
         public void draw(GraphicsContext gc)
         {
            for(int h = 0; h<21;h++)
            {
               for(int k = 0; k<21;k++)
               {
                  if(mazeNumArray[h][k] == 0)
                  {
                     gc.setFill(Color.WHITE);
                     gc.fillRect(25*k,25*h,25,25);
                  }
                  else
                  {
                     gc.setFill(Color.BLACK);
                     gc.fillRect(25*k,25*h,25,25);
                  }
               }
            }
            //Make the player square. Make sure the player is on the top row in an available spot.
            gc.setFill(Color.CYAN);
            for(int hh = 0; hh<21;hh++)
            {
               if(mazeNumArray[0][hh] == 0)
               {
               x=hh*25;
               y=0;
               gc.fillRect(x,y,25,25);
               }
            }
            
         }
         
         //Make the count method to get the numbers from the file.
         public void count()
         {
            //Make the try block incase there is a file not found exception.
            try
            {
               Scanner read = new Scanner(mazeNum);
               for(int i = 0;i<21;i++)
               {
                  for(int j = 0; j<21;j++)
                  {
                     mazeNumArray[i][j] = read.nextInt();
                  }
               }
               
            } catch(FileNotFoundException fnfe)
            {
            
            }
         }
         
         //This was added in part two. It resets the board and moves the cyan block.
         public void update(int newX, int newY)
         {
            x=newX;
            y=newY;
            GraphicsContext gc = getGraphicsContext2D();
            gc.clearRect(0,0,525,525);
            
            //This part is just copy and pasted from the draw() method.
            for(int h = 0; h<21;h++)
            {
               for(int k = 0; k<21;k++)
               {
                  if(mazeNumArray[h][k] == 0)
                  {
                     gc.setFill(Color.WHITE);
                     gc.fillRect(25*k,25*h,25,25);
                  }
                  else
                  {
                     gc.setFill(Color.BLACK);
                     gc.fillRect(25*k,25*h,25,25);
                  }
               }
            }
            //This part was edited so that the cyan block is not stuck on the highest white block.
            gc.setFill(Color.CYAN);
            gc.fillRect(x,y,25,25);

         }
         //These were added in part two. These get the x and y values.
         public int getX()
         {
            return x;
         }
         public int getY()
         {
            return y;
         }
      }
    //PART TWO
      
      /*Make the key handler event to guide the cyan square. The arrow keys will be used to guide them(WASD might be a better option though)
      The cyan square will not able to go through black squares*/
      public class KeyHandler implements EventHandler<KeyEvent>
      {
         public void handle(KeyEvent ke)
         {
         
            if(ke.getCode() == KeyCode.UP) //If the up arrow is pressed, move the cyan square up when available
            {
               for(int h = 0; h<21;h++)
               {
                  for(int k = 0; k<21;k++)
                  {
                     if(maze.getX() == 25*k && maze.getY() == 25*h)
                     {
                        playerX = maze.getX();
                        playerY = maze.getY();
                        if(h-1>=0)//This prevents an exception from happening at the next line with the [h-1] part.
                        {
                           if(mazeNumArray[h-1][k] == 0 && playerY >= 25)
                           {
                              maze.update(playerX,playerY-25);
                           }
                           else
                           {
                              maze.update(playerX,playerY);
                           }
                        }
                        else
                        {
                           System.out.println("Lol.");
                        }
                     }
                  }
               }
            }
            if(ke.getCode() == KeyCode.DOWN) //If the up arrow is pressed, move the cyan square up when available
            {
               for(int h = 0; h<21;h++)
               {
                  for(int k = 0; k<21;k++)
                  {
                     if(maze.getX() == 25*k && maze.getY() == 25*h)
                     {
                        playerX = maze.getX();
                        playerY = maze.getY();
                     }
                  }
               }
               /*I had to seperate the inner if/else statements because the cyan block just went to the lowermost white block before the next black block
               whenever I pressed the down key using the KeyCode.UP format. 
               */
               if((maze.getY()/25)+1 <21)
               {
                  if(mazeNumArray[(maze.getY()/25)+1][maze.getX()/25] == 0)
                  {
                     maze.update(playerX,playerY+25);
                  }
                  else
                  {
                     maze.update(playerX,playerY);
                  }
               }
               else
               {
                  System.out.println("You win!");
               }
            }

            
            if(ke.getCode() == KeyCode.RIGHT) //If the up arrow is pressed, move the cyan square up when available
            {
               for(int h = 0; h<21;h++)
               {
                  for(int k = 0; k<21;k++)
                  {
                     if(maze.getX() == 25*k && maze.getY() == 25*h)
                     {
                        playerX = maze.getX();
                        playerY = maze.getY();
                     }
                  }
               }
               /*I had to seperate the inner if/else statements because the cyan block just went to the rightmost white block before the next black block
               whenever I pressed the down key using the KeyCode.LEFT format. 
               */
               if(mazeNumArray[maze.getY()/25][1+ (maze.getX()/25)] == 0)
               {
                  maze.update(playerX+25,playerY);
               }
               else
               {
                  maze.update(playerX,playerY);
               }
            }
            if(ke.getCode() == KeyCode.LEFT) //If the up arrow is pressed, move the cyan square up when available
            {
               for(int h = 0; h<21;h++)
               {
                  for(int k = 0; k<21;k++)
                  {
                     if(maze.getX() == 25*k && maze.getY() == 25*h)
                     {
                        playerX = maze.getX();
                        playerY = maze.getY();
                        if(k-1>=0)//This prevents an exception from happening at the next line with the [h-1] part.
                        {
                           if(mazeNumArray[h][k-1] == 0 && playerX >= 25)
                           {
                              maze.update(playerX-25,playerY);
                           }
                           else
                           {
                              maze.update(playerX,playerY);
                           }
                        }
                        else
                        {
                           System.out.println("Lol.");
                        }
                     }
                  }
               }
            }  
         }
      
      }
      
}