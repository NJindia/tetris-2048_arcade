package cs1302.arcade.gameTetris;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import java.util.Arrays;
import javafx.scene.shape.Rectangle;
public class GameTetris{
    
    Pane display = new Pane();
    //dimensions for now
    int distanceDown = 30;
    int blockSize = 30;
    int gameSizeX = 300;
    int gameSizeY = 600;
    Scene scene;
    int [][] grid = new int[gameSizeX/blockSize][gameSizeY/blockSize]; //main grid for game    
    Rectangle[][] board = new Rectangle[24][10];
    Pane rectanglePane = new Pane();
    //Rectangles are 20x20px
    public Scene getGameScene () {
        rectanglePane 
        for(int[] a: grid)
        {
            Arrays.fill(a, 0);
        }

        Element c = Board.generateBoard();

        display.getChildren().addAll(c.e1, c.e2, c.e3, c.e4);

        scene = new Scene(display);

        return scene;        
    }

}
