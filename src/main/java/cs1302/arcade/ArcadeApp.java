package cs1302.arcade;

import javafx.scene.layout.BackgroundFill;
import javafx.geometry.Orientation;
import javafx.scene.layout.Background;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.scene.*;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import cs1302.arcade.game2048.*;
import java.util.Random;
import javafx.scene.control.Separator;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
//import java.awt.Font;
import javafx.scene.text.Font;
import javafx.scene.layout.VBox;


public class ArcadeApp extends Application {
    Separator style = new Separator(Orientation.HORIZONTAL);

    Group group = new Group();           // main container
    Random rng = new Random();           // random number generator
    //Rectangle r = new Rectangle(20, 20); // some rectangle
    Tile r = new Tile();
    Game2048 game = new Game2048();
    private Stage stage;
    
    /**
     * Return a mouse event handler that moves to the rectangle to a random
     * position any time a mouse event is generated by the associated node.
     * @return the mouse event handler
     */
    private EventHandler<? super MouseEvent> createMouseHandler() {
	return event -> {
	    System.out.println(event);
	    r.setX(rng.nextDouble() * (480 - 100/*r.getWidth()*/));
	    r.setY(rng.nextDouble() * (640 - 100/*r.getHeight()*/));
	};
    } // createMouseHandler

    /**
     * Return a key event handler that moves to the rectangle to the left
     * or the right depending on what key event is generated by the associated
     * node.
     * @return the key event handler
     */
    private EventHandler<? super KeyEvent> createKeyHandler() {
	return event -> {
	    System.out.println(event);
	    if (event.getCode() == KeyCode.LEFT)  r.setX(r.getX() - 10.0);
	    if (event.getCode() == KeyCode.RIGHT) r.setX(r.getX() + 10.0);
	    // TODO bounds checking
	};
    } // createKeyHandler


  public void mainMenu()
        {
            Pane mainMenu;
            Font font = Font.font(50);
//          Scene scene2 = new Scene();
            Scene scene1;
            Button game1 = new Button("2048 Game");
            game1.setFont(font);
            //game1.setPadding(new Insets(10, 50, 100, 50));

            game1.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("Launching 2048!");
                        Scene scene2 = game.getGameScene();
                        stage.setScene(scene2);
                    }
                });
  
          Button game2 = new Button("Tetris Game");
            game2.setFont(font);
//            game2.setPadding(new Insets(10, 50, 100, 50));
            game2.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("Launching Tetris!");
                    }
                });
            Button exit = new Button("Exit");
            exit.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event) {
                        System.exit(0);
                    }
                });
            style.setPadding(new Insets(50, 10, 50, 50));

            VBox newPane = new VBox(game1, style, game2, exit);
            
            newPane.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
            scene1 = new Scene(newPane);
            stage.setScene(scene1);
            
            
            /* game1.setOnAction(e -> new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("Launching 2048!");
                        scene2 = game.getGameScene();
                        scene1.getChildren().add(scene2);
                    }
                });
            */

            
        }






    /** {@inheritdoc} */
    @Override
    public void start(Stage stage) {

        /* You are allowed to rewrite this start method, add other methods,
         * files, classes, etc., as needed. This currently contains some
         * simple sample code for mouse and keyboard interactions with a node
         * (rectangle) in a group.
         */

        r.setX(50);                                // 50px in the x direction (right)
        r.setY(50);                                // 50ps in the y direction (down)
        group.getChildren().add(r);                // add to main container
        r.setOnMouseClicked(createMouseHandler()); // clicks on the rectangle move it randomly
        group.setOnKeyPressed(createKeyHandler()); // left-right key presses move the rectangle
        this.stage = stage;
        Scene scene = new Scene(group, 480, 640);
        stage.setTitle("cs1302-arcade!");

        mainMenu();
        //stage.setScene(game.getGameScene());
        //stage.setScene(scene);
        stage.sizeToScene();
        stage.show();

        // the group must request input focus to receive key events
        // @see https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#requestFocus--
        //group.requestFocus();

    } // start

} // ArcadeApp
