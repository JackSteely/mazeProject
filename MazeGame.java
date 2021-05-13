//This is the first part of the project where I created a maze and had to guide a cyan square out of it. By Jack Steely May 12, 2021
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

public class MazeGame extends Application
{
   
      
      //Important variables.
      File mazeNum = new File("mazeFile.txt");
      int [][] mazeNumArray = new int [21][21];
      MazeCanvas maze = new MazeCanvas();
      int playerX,playerY;
      

 
      //Start method.
      public void start(Stage stage)
      {
      //Put everything in the try block in case the file element isn't found.
      try
      {
         FlowPane root = new FlowPane();
         root.setAlignment(Pos.CENTER);
         root.getChildren().add(maze);
   
 
         Scene scene = new Scene(root,525,525);
         stage.setScene(scene);
         stage.setTitle("Proto-Maze");
         stage.show();
   
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
               gc.fillRect(hh*25,0,25,25);
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

      }
      
}