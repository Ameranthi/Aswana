
package project;

/* CharacterSelect is a subclass of GameWindow. This class is used to pass the Scene charSelect.  
charSelect shows the picking of the hero's type/class (hero, rogue, mage), and the picking of the 
hero's name. The name is chosen first. Once the name is chosen, then the sequence for choosing the
type/class is shown in the scene.
 */

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class CharacterSelect extends GameWindow {

    private static HBox imageBox = new HBox();
    private static HBox buttonBox = new HBox();
    private static VBox inputBox = new VBox();
    private static Label text = new Label("\n\n\nEnter your name, recruit:");
    private static BorderPane border;

    public static void inputName() { // inputting name

        inputBox.setPadding(new Insets(10));
        inputBox.setSpacing(60);
        nameInput = new TextField();
        nameInput.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        nameInput.setAlignment(Pos.CENTER);
        border.setStyle("-fx-background-color:#dda0dd;");
        inputBox.getChildren().addAll(text, nameInput);
        inputBox.setAlignment(Pos.CENTER);
        border.setTop(inputBox);
    }

    public static void chooseClass() { // choosing type/class
        swordsmenButton = new Button("Swordsman");
        mageButton = new Button("Mage");
        rogueButton = new Button("Rogue");
        text.setText("Choose your class");
        BorderPane.setAlignment(text, Pos.CENTER);
        mageButton.setStyle("-fx-font:25 Arial");
        swordsmenButton.setStyle("-fx-font:25 Arial");
        rogueButton.setStyle("-fx-font:25 Arial");
        buttonBox.setPadding(new Insets(15, 12, 15, 12));
        imageBox.setPadding(new Insets(15, 12, 15, 12));
        buttonBox.setSpacing(300);
        imageBox.setSpacing(97.2);
        buttonBox.setStyle("-fx-background-color:#336699;");
        imageBox.setAlignment(Pos.CENTER);
        buttonBox.setAlignment(Pos.CENTER);
        imageBox.getChildren().addAll(swordViewer, mageViewer, rogueViewer);
        buttonBox.getChildren().addAll(swordsmenButton, mageButton, rogueButton);
        border.setCenter(imageBox);
        border.setBottom(buttonBox);
        border.setTop(text);
    }

    public static Scene passScene() {
        text.setFont(new Font(40));
        border = new BorderPane();
        if (choseName) { // if the name has been chosen already
            chooseClass();
        } else { // if the name hasnt been chosen yet
            inputName();
        }
        charSelect = new Scene(border, 1200, 760);
        window.setTitle("Character Select");
        return charSelect;

    }

}
