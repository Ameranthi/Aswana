package project;


/* GameWindow is the extension of the Application class. GameWindow acts as a "hub" for the entire
 game. This is where all the GUI related variables are declared. For example, all of the scenes, 
 buttons, stages, and textFields are declared in this class. Also, various counters are declared, 
 and sometimes initialized in this class. 
 * The pictures and imageviews for the charSelect scene are initialized in this class.
 * All of the classes that deal with GUI, (Dialogue, Battle, ExpGain, StartMenu) are subclasses of
 GameWindow.
 * All of the action listeners that listen for events like button presses and textfield returns are
 in this class. To change which Scene is on the stage, the passScene methods are called (every one
 of the GUI related classes has a static method passScene() which returns a Scene). This returned
 Scene is then set on the stage.
* Lastly, this class also has the method addText(Label l, String newText), which simply appends a 
label without erasing the previous text in the label. 
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameWindow extends Application {

    protected static Stage window; // main window the game is played in
    protected static Stage expWindow = new Stage(); // pop up window for experience gained

    protected static Hero player;
    protected static Enemy enemy;

    protected String playerName; // temporary variable to construct the hero
    protected String playerType; // temporary variable for type (class) of hero

    // counter for if hero's name has been chosen
    protected static boolean choseName;
    // counter for what stage of the game the player is on. 
    // gameLvl = 0 means tutorial is ongoing, gameLvl = 1 means lvl 1, etc...
    protected static int gameLvl;
    // counter for if tutorial has been played. False means it was skipped
    protected static boolean tutorial;
    // counter for if player wishes to continue to next dialogue.  
    protected static boolean progress;
    // counter for if player wishes to abandon the cause
    protected static boolean giveUp;
    // counter to keep track if a battle is ongoing
    protected static boolean fightStatus;
    //  counter for if player is alive (player starts alive)
    protected static boolean alive = true;

    // all the buttons used throughout game
    protected static Button start;
    protected static Button credits;
    protected static Button exit;
    protected static Button swordsmenButton;
    protected static Button mageButton;
    protected static Button rogueButton;
    protected static Button choice1;
    protected static Button choice2;
    protected static Button attack;
    protected static Button heal;
    protected static Button doNothing;
    protected static Button closeExp;
    protected static Button continueButton;

    // all the scenes used throughout the game
    protected static Scene charSelect;
    protected static Scene startMenu;
    protected static Scene dialogueScene;
    protected static Scene battleScene;
    protected static Scene expScene;

    protected static TextField nameInput; // textfield that player name is inputted

    // possible hero pictures
    protected Image swordPic = new Image(getClass().getResourceAsStream("/Swordsman.png"));
    protected Image magePic = new Image(getClass().getResourceAsStream("/Mage.png"));
    protected Image roguePic = new Image(getClass().getResourceAsStream("/Rogue.png"));

    // image viewers for hero type selection
    protected static ImageView swordViewer = new ImageView();
    protected static ImageView mageViewer = new ImageView();
    protected static ImageView rogueViewer = new ImageView();

    // method to add text to existing label, without deleting existing text. 
    public static void addText(Label l, String newText) {
        l.setText(l.getText() + "\n" + newText); // it puts new text on new line
    }

    @Override
    public void start(Stage stage) {

        // images used in game
        swordViewer.setFitHeight(550);
        swordViewer.setFitWidth(350);
        swordViewer.setImage(swordPic);
        mageViewer.setFitHeight(550);
        mageViewer.setFitWidth(350);
        rogueViewer.setFitHeight(550);
        rogueViewer.setFitWidth(350);
        mageViewer.setImage(magePic);
        rogueViewer.setImage(roguePic);

        window = stage;
        // disabling minimize and maximize buttons
        window.initStyle(StageStyle.UTILITY);

        // act as pop up window
        expWindow.initOwner(window);
        expWindow.initModality(Modality.WINDOW_MODAL);
        expWindow.initStyle(StageStyle.UTILITY);

        startMenu = StartMenu.passScene(); // passing scene startMenu

        start.setOnAction(this::processButtonPress);
        exit.setOnAction(this::processButtonPress);

        window.setScene(startMenu);
        window.setTitle("Aswana!");

        window.show();

    }

    public void processReturn(ActionEvent event) {
        if (event.getSource() == nameInput) {
            // setting hero's name
            playerName = nameInput.getText();
            choseName = true; // name has now been chosen
            charSelect = CharacterSelect.passScene();
            window.setScene(charSelect);
            swordsmenButton.setOnAction(this::processButtonPress);

            mageButton.setOnAction(this::processButtonPress);
            rogueButton.setOnAction(this::processButtonPress);

        }
    }

    // button presses
    public void processButtonPress(ActionEvent event) {
        if (event.getSource() == start) {
            charSelect = CharacterSelect.passScene();
            window.setScene(charSelect);
            nameInput.setOnAction(this::processReturn);
        }
        if (event.getSource() == exit) {
            StartMenu.exitPressed();
        }
        if (event.getSource() == swordsmenButton) {
            player = new Hero(1, playerName, "swordsman");
            dialogueScene = Dialogue.passScene();
            window.setTitle("Aswana!");
            window.setScene(dialogueScene);
            choice1.setOnAction(this::processButtonPress);
            choice2.setOnAction(this::processButtonPress);

        }
        if (event.getSource() == mageButton) {
            player = new Hero(1, playerName, "mage");
            dialogueScene = Dialogue.passScene();
            window.setTitle("Aswana!");
            window.setScene(dialogueScene);
            choice1.setOnAction(this::processButtonPress);
            choice2.setOnAction(this::processButtonPress);

        }
        if (event.getSource() == rogueButton) {
            player = new Hero(1, playerName, "rogue");
            dialogueScene = Dialogue.passScene();
            window.setTitle("Aswana!");
            window.setScene(dialogueScene);
            choice1.setOnAction(this::processButtonPress);
            choice2.setOnAction(this::processButtonPress);

        }
        if (event.getSource() == choice1) { //  button for if player wants to battle
            if (gameLvl == 0) {
                tutorial = true; // player choses to do tutorial
            } else if (gameLvl > 0) { // if player is not on tutorial game level
                giveUp = false; // player doesnt want to abandon cause
            }
            gameLvl++; // now on next level
            battleScene = Battle.passScene(); // a battle commences
            window.setTitle("Battle!");
            window.setScene(battleScene);
            attack.setOnAction(this::processButtonPress);
            heal.setOnAction(this::processButtonPress);
            doNothing.setOnAction(this::processButtonPress);
        }
        if (event.getSource() == choice2) { // player doesn't want to battle
            if (gameLvl == 0) {
                tutorial = false; // player skips tutorial
                player.gainExperience(1); // making hero lvl 2, to make the game fair
                gameLvl++; // now on next level
                dialogueScene = Dialogue.passScene();
                window.setTitle("Aswana!");
                window.setScene(dialogueScene);
                continueButton.setOnAction(this::processButtonPress);
            } else {
                giveUp = true; // player decides they want to abandon cause
                alive = false;
                startMenu = StartMenu.passScene();
                window.setTitle("Aswana!");
                window.setScene(startMenu);
                exit.setOnAction(this::processButtonPress);
            }

        }
        if (event.getSource() == attack) { // player chooses to atack
            Battle.buttonPress(1);
            if (!fightStatus) { // once fight ends
                if (Battle.fightResult()) { // if player won
                    dialogueScene = Dialogue.passScene();
                    window.setTitle("Aswana!");
                    window.setScene(dialogueScene);
                    expScene = ExpGain.passScene();
                    closeExp.setOnAction(this::processButtonPress);
                    expWindow.setScene(expScene);
                    expWindow.setX(500);
                    expWindow.setY(200);
                    expWindow.setTitle("EXP GAIN");
                    expWindow.show();

                } else { // Player lost the fight
                    alive = false;
                    StartMenu.passScene();
                    window.setTitle("Aswana!");
                    window.setScene(startMenu);
                    exit.setOnAction(this::processButtonPress);

                }
            }
        }
        if (event.getSource() == heal) { // player chooses to heal
            Battle.buttonPress(2);
            if (!fightStatus) { // once fight ends
                if (Battle.fightResult()) { // if player won
                    dialogueScene = Dialogue.passScene();
                    window.setTitle("Aswana!");
                    window.setScene(dialogueScene);
                    expScene = ExpGain.passScene();
                    closeExp.setOnAction(this::processButtonPress);
                    expWindow.setScene(expScene);
                    expWindow.setX(500);
                    expWindow.setY(200);
                    expWindow.setTitle("EXP GAIN");
                    expWindow.show();

                } else { // Player lost the fight
                    alive = false;
                    StartMenu.passScene();
                    window.setTitle("Aswana!");
                    window.setScene(startMenu);
                    exit.setOnAction(this::processButtonPress);

                }
            }
        }
        if (event.getSource() == doNothing) { // player chooses to do nothing
            Battle.buttonPress(3);
            if (!fightStatus) { // once fight ends
                if (Battle.fightResult()) { // if player won
                    dialogueScene = Dialogue.passScene();
                    window.setTitle("Aswana!");
                    window.setScene(dialogueScene);
                    expScene = ExpGain.passScene();
                    closeExp.setOnAction(this::processButtonPress);
                    expWindow.setScene(expScene);
                    expWindow.setX(500);
                    expWindow.setY(200);
                    expWindow.setTitle("EXP GAIN");
                    expWindow.show();

                } else { // Player lost the fight
                    alive = false;
                    StartMenu.passScene();
                    window.setTitle("Aswana!");
                    window.setScene(startMenu);
                    exit.setOnAction(this::processButtonPress);

                }
            }
        }
        if (event.getSource() == continueButton) {
            if (player.getLVL() == 5) { // player defeats final boss
                startMenu = StartMenu.passScene();
                window.setScene(startMenu);
                exit.setOnAction(this::processButtonPress);
                credits.setOnAction(this::processButtonPress);
            } else { // game is still ongoing
                progress = true;
                dialogueScene = Dialogue.passScene();
                window.setScene(dialogueScene);
                choice1.setOnAction(this::processButtonPress);
                choice2.setOnAction(this::processButtonPress);
            }

        }
        if (event.getSource() == closeExp) {
            expWindow.hide(); // closing EXP points window
            continueButton.setOnAction(this::processButtonPress);
        }
        if (event.getSource() == credits) {
            startMenu = StartMenu.rollCredits(); // showing credits
            window.setScene(startMenu);
            window.setTitle("Credits");
            exit.setOnAction(this::processButtonPress);
        }

    }

}
