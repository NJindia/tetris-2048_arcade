package cs1302.arcade.gameTetris;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.Button;
import java.util.Arrays;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.event.*;
import cs1302.arcade.gameTetris.shapes.*;

public class GameTetris{
    
    Scene scene;
    Rectangle[][] board = new Rectangle[24][10];
    GridPane grid = new GridPane();
    private Text score = new Text();
    Timeline tl = new Timeline();
    Shape currShape;
    private boolean gameOver = false;
    
    public Scene getGameScene () {
        //updateScore(0);
        score.setFont(new Font(20));
        HBox scores = new HBox(score);
        scores.setSpacing(30);

        //Creates and sets behavior of buttons
        Button b = new Button("New Game") {
                public void requestFocus() { } //Prevents b from taking focus
            };
        //b.setOnAction(e -> newGame());
        Button b2 = new Button("Back to Games List") {
                public void requestFocus() { } //Prevents b2 from taking focus
            };
        //b2.setOnAction(e -> mainMenu());
        HBox buttons = new HBox(b, b2);

        grid.setOnKeyPressed(createKeyHandler());
        setTimeline(1);
        
        grid.setPrefSize(300, 600);
        grid.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        
        //adds cols and rows to grid
        final int numCols = 10;
        final int numRows = 20;
        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / numCols);
            grid.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / numRows);
            grid.getRowConstraints().add(rowConst);         
        }
        grid.setGridLinesVisible(true);
        
        VBox vbox = new VBox(scores, buttons, grid);
        scene = new Scene(vbox);
        newShape();
        grid.requestFocus();
        return scene;        
    }

    private void newShape() {
        Shape s = new TShape(3, 1, board, grid);
        currShape = s;
        tl.play();
    }

    private EventHandler<? super KeyEvent> createKeyHandler() {
        return e -> {
            if(gameOver == false){
                if (e.getCode() == KeyCode.RIGHT) {
                    System.out.println("right");
                    currShape.moveRight();
                } else if (e.getCode() == KeyCode.LEFT) {
                    currShape.moveLeft();
                } else if (e.getCode() == KeyCode.UP) {
                    currShape.rotate();
                    System.out.println("up");
                } else if (e.getCode() == KeyCode.DOWN) {
                    currShape.moveToBottom();
                } //if
            } //if
        }; //return
    } //createKeyHandler
    
    
    private void setTimeline(int level) {
        EventHandler<ActionEvent> handler = e -> {
            if(currShape.moveDown() == false) {
                clearLines();
                newShape();
            } //if
        };        
        KeyFrame k;
        switch(level) {
        case 1:
            k = new KeyFrame(Duration.millis(200), handler);
            break;
        case 2:
            k = new KeyFrame(Duration.millis(666), handler);
            break;
        case 3:
            k = new KeyFrame(Duration.millis(333), handler);
            break;
        default:
            k = new KeyFrame(Duration.millis(1000), handler);
        }
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.getKeyFrames().add(k);
    } //setTimeline

    private void clearLines() {
        int rowsCleared = 0;
        for(int y = 0; y < 20; y++) {
            boolean isFull = true;
            for (int x = 0; x < 10; x++) {
                if(getFromGrid(x, y) == null) {
                    isFull = false;
                } //if
            } //for
            if(isFull) {
                for (int x = 0; x < 10; x++) {
                    Rectangle rect = getFromGrid(x, y);
                    grid.getChildren().remove(rect);
                    Rectangle top = getFromGrid(x, y-1);
                    if(top != null) {
                        grid.add(top, x, y);
                    }
                }
                //clear row and add points
            }
        }
    }

    public Rectangle getFromGrid(int col, int row) {
        for (Node node : grid.getChildren()) {
            if(node != null && GridPane.getColumnIndex(node)!= null
               && GridPane.getRowIndex(node) != null) {
                if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                    return (Rectangle)node;
                }
            }
        }
        return null;
    }
    
    
}