package project;

/* StartMenu is a subclass of GameWindow. This class is used to pass the Scene startMenu. The 
start menu scene can funtion as the menu, the game over sequence, the game completed sequence, and
the credit sequence. 
 */

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class StartMenu extends GameWindow {

    private static Label titleText;
    private static Text gameOver;
    private static final VBox buttonBox = new VBox(); // stores buttons
    private static final VBox textBox = new VBox(); // stores text
    private static BorderPane border;

    public static void menu() { // game is booted up. (Start menu)
        titleText = new Label("\nAswana");
        titleText.setFont(new Font(90));
        start = new Button("START");
        border.setStyle("-fx-background-color:#ff69b4;");
        start.setStyle("-fx-font:40 Arial");
        buttonBox.getChildren().addAll(start);
        border.setTop(titleText);
        border.setCenter(buttonBox);
        choseName = false; // player has yet to choose their name
        gameLvl = 0; // level starts at 0. (0 is the tutorial level)
        BorderPane.setAlignment(titleText, Pos.CENTER);
        buttonBox.setAlignment(Pos.CENTER);
    }
    //The message shown differs based on if the player ran away, or if they died in combat
    public static void gameOver() { 
        buttonBox.getChildren().clear();
        gameOver = new Text("Game Over");
        if (giveUp) { // if user disobeyed orders
            titleText.setText("\nThe Imperial Legion has executed you for your cowardice");
            textBox.getChildren().add(titleText);
        } else { // user dies from losing a battle
            gameOver = new Text("\nGame Over");
        }
        textBox.getChildren().add(gameOver);
        gameOver.setFont(new Font(90));
        titleText.setFont(new Font(40));
        textBox.setAlignment(Pos.CENTER);
        border.setStyle("-fx-background-color:#ffe4e1;");
        border.setTop(textBox);
        border.setCenter(buttonBox);
        BorderPane.setAlignment(textBox, Pos.CENTER);
        buttonBox.setAlignment(Pos.CENTER);
    }

    public static void completed() { // game has been beaten
        buttonBox.getChildren().clear();
        titleText.setText("\n\nThank you for Playing!");
        titleText.setFont(new Font(69));
        border.setStyle("-fx-background-color:#afeeee;");
        border.setTop(titleText);
        credits = new Button("Credits");
        credits.setStyle("-fx-font:40 Arial");
        buttonBox.getChildren().add(credits);
        border.setCenter(buttonBox);
        BorderPane.setAlignment(titleText, Pos.CENTER);
        buttonBox.setAlignment(Pos.CENTER);
    }

    public static Scene passScene() { // call to pass menu, gameover, and game completed sequences
        border = new BorderPane();
        exit = new Button("EXIT GAME");
        exit.setStyle("-fx-font:40 Arial");
        textBox.setPadding(new Insets(10));
        textBox.setSpacing(80);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setSpacing(48);
        if (gameLvl == 5) {
            completed();
        } else if (alive) {
            menu();
        } else {
            gameOver();
        }
        buttonBox.getChildren().add(exit);
        startMenu = new Scene(border, 1200, 760);

        return startMenu;
    }

    public static Scene rollCredits() { // call to pass credit sequence
        textBox.getChildren().clear();
        border = new BorderPane();
        exit = new Button("EXIT GAME");
        exit.setStyle("-fx-font:30 Arial");
        titleText.setText("Aswana");
        Label nameCredits = new Label("Created by:\nNick Milson, Andy Tran, Julia Olmstead, Esam "
                + "Mahmoud-Ahmed, and Hayam Mahmoud-Ahmed.\n\nCharacter artist:\nJulia Olmstead");
        nameCredits.setFont(new Font(35));
        textBox.setPadding(new Insets(10));
        textBox.setSpacing(45);
        textBox.getChildren().addAll(titleText, nameCredits, exit);
        textBox.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(titleText, Pos.CENTER);
        border.setCenter(textBox);
        nameCredits.setPrefWidth(1100); // setting max length of a single line of text to 1100 pixels
        nameCredits.setWrapText(true); // when text hits 1100 pixels, it wraps
        border.setStyle("-fx-background-color:#ffdead;");
        startMenu = new Scene(border, 1200, 760);
        return startMenu;
    }

    public static void exitPressed() { // When exit button is pressed
        // user pushed exit button
        System.out.println("Thanks for playing");
        System.exit(0); // Program is terminated
    }
}
