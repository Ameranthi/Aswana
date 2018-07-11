
package project;

/* ExpGain is a subclass of GameWindow. This class is used to pass the Scene expScene. Unlike the 
other scenes, expScene doesn't go into the main stage, window. It goes into its own pop up style 
stage called expWindow. expScene shows how much exp the hero gained from defeating an enemy, and it
also shows whether or not the hero levels up after gaining said exp.
 */

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class ExpGain extends GameWindow {

    private static int exp;
    private static boolean lvlUp; // whether or not the hero levels up
    private static Label text = new Label();

    public static void setExp(int i) {
        exp = i;
    }

    public static void setLvlUp(boolean u) {
        lvlUp = u;
    }

    public static Scene passScene() {
        closeExp = new Button ("OK");
        closeExp.setStyle("-fx-font:25 Arial");
        BorderPane pane = new BorderPane();
        text.setText("Enemy defeated!\n\nEXP gained: " + exp);
        if (lvlUp) { // if the hero lvls up
            GameWindow.addText(text, "\n" + player.getName() + " Levels up to Lvl " + player.getLVL());
        }
        text.setFont(new Font( 30));
        pane.setCenter(text);
        pane.setBottom(closeExp);
        BorderPane.setAlignment(text, Pos.CENTER);
        BorderPane.setAlignment(closeExp, Pos.CENTER);
        expScene = new Scene(pane, 500, 500);
        return expScene;
    }

}
