package project;

/* Dialogue is a subclass of GameWindow. This class is used to pass the Scene dialogueScene. The 
dialogueScene shows the character's dialogue bewteen battles. Each level has a different set of 
dialogue. Within each level, the dialogue is split into two sections. The transition between 
sections within a level is controlled by whether or not the progress boolean is true or not. The 
first section (progress = false) is concerning the battle that was just won, and the last section
(progress = true) concerns the battle the is about to commence. In the latter section, the player is
given two choices. To battle, or to refuse to battle. 
 */
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class Dialogue extends GameWindow {

    private static String text;
    private static final HBox buttonBox = new HBox();
    private static FlowPane pane;
    private static Label dialogue;

    public static void tutorial() {
        text = "Narrator: Aswana, despite being so far up the "
                + "continent, is a thriving tundra of great forests and "
                + "undiscovered secrets. Camps have been quickly set up in "
                + "the area upon its discovery, to harvest the vast resources"
                + " waiting to be unearthed. It is here that our story begins."
                + "\n\nSir Reilly: Greetings " + player.getName() + ", welcome to Aswana."
                + " I know you just arrived yesterday, but I think its time to "
                + "test your skill. We can't have untrained legion soldiers running around."
                + " Luckily, there happens to be a low level monster right over"
                + " yonder. Why don't you go and show me what you got";
        buttonBox.setPadding(new Insets(90, 12, 15, 12));
        buttonBox.setSpacing(30);
        choice1 = new Button("Yes Sir!");
        choice2 = new Button("I don't have to prove anything to you.");
        choice1.setStyle("-fx-font:25 Arial");
        choice2.setStyle("-fx-font:25 Arial");
        buttonBox.getChildren().addAll(choice1, choice2);
        dialogue = new Label(text);
        dialogue.setFont(new Font(35));
        pane.getChildren().addAll(dialogue, buttonBox);
    }

    public static void lvl1() {
        if (!progress) { // player not done reading dialogue yet
            if (tutorial) { // if player did the tutorial
                text = "Sir Reilly: Not bad " + player.getName() + ". I see great "
                        + "promise in y-";
            } else { // if player skipped tutorial
                text = "Sir Reilly: Listen here now. In my camp you will not speak to your "
                        + "superiors that wa-";
            }
            dialogue = new Label(text);
            GameWindow.addText(dialogue, "\nScout: Sir! Sorry to inturrupt but the west"
                    + " mine has just been attacked!\n\nSir Reilly: What!?\n\nScout: It "
                    + "seems the noise made from our miners has alerted nearby "
                    + "monsters to our position! We must hurry!.\n\nSir Reilly: Very "
                    + "well. " + player.getName() + ". Time is of the essence. Go out"
                    + " now and save those men.\n\nNarrator: And so " + player.getName() + ", "
                    + "whether they wanted to or not, was quickly transported to the "
                    + "mine to do their duty."
            );
            continueButton = new Button("Continue to West Mine");
            continueButton.setStyle("-fx-font:25 Arial");
            buttonBox.getChildren().clear();
            buttonBox.getChildren().add(continueButton);
            dialogue.setFont(new Font (28));
        } else { // player ready to move on to next dialogue
            text = "Scout: Captain, we came as quickly as we could. I've brought reinforcements."
                    + "\n\nLegionary: Thank goodness you've come. The mine is totally over run."
                    + " We need all the assistance we can get. Recruit, take out that monster "
                    + "attacking our miners!";
            dialogue.setText(text);
            buttonBox.getChildren().clear();
            choice1 = new Button("Yes Sir!");
            choice2 = new Button("Fight that monster? Are you crazy?!");
            choice1.setStyle("-fx-font:25 Arial");
            choice2.setStyle("-fx-font:25 Arial");
            buttonBox.getChildren().addAll(choice1, choice2);
            progress = false;
            dialogue.setFont(new Font(35));
        }
        pane.getChildren().addAll(dialogue, buttonBox);

    }

    public static void lvl2() {
        if (!progress) { // player not done reading dialogue yet
            text = "Legionary: Fantastic work recruit. " + player.getName() + " was it? I'll put "
                    + "in a good word with Sir Reilly\n\nScout: Captain! Urgent news! This attack on"
                    + " the West Mine was simply a distraction. A ruse if you will. The real attack "
                    + "is focused on our defacto headquarters in Phuport. Sir Reilly is there, "
                    + "protecting the Duke and his family. \n\nLegionary: This is serious. We need"
                    + " to help liberate the city. If the assaulting army of monsters win, we"
                    + " lose our foothold in Aswana. " + player.getName() + ", let's make haste to"
                    + " Phuport!";
            dialogue = new Label(text);
            continueButton = new Button("Continue to Phuport");
            continueButton.setStyle("-fx-font:25 Arial");
            buttonBox.getChildren().clear();
            buttonBox.getChildren().add(continueButton);
            dialogue.setFont(new Font(33));
        } else { // player ready to move on to next dialogue
            text = "Legionary: There's the Duke's manor. I fear the Duke and his family may be in "
                    + "danger. I'm going to help protect the civilians. Hurry " + player.getName()
                    + ", rush up into the manor and check in on the Duke. Be careful, lord knows "
                    + "what sort of ghoulish monsters and various spooky phantasms you may"
                    + " encounter.";
            dialogue.setText(text);
            buttonBox.getChildren().clear();
            choice1 = new Button("I'm on it Captain!");
            choice2 = new Button("I'm not risking my life for no Duke!");
            choice1.setStyle("-fx-font:25 Arial");
            choice2.setStyle("-fx-font:25 Arial");
            buttonBox.getChildren().addAll(choice1, choice2);
            progress = false;
            dialogue.setFont(new Font(35));
        }
        pane.getChildren().addAll(dialogue, buttonBox);
    }

    public static void lvl3() {
        if (!progress) { // player not done reading dialogue yet
            text = "Duke: Thank you for saving me brave warrior. I owe my life to you. A proper "
                    + "congratulation will have to wait I'm afraid. We have a traitor high up in "
                    + "our ranks. The man behind these monster attacks is none other than my very"
                    + " own right hand man, Sir Reilly! He orchestrated this plan to drive our "
                    + "legion out of Aswana, and take control of the province for him self! I fear"
                    + " that Reilly is now far too powerful for anyone to stop. But if any mortal "
                    + "can defeat him, it is you. Please, go to the old clock tower in the centre of"
                    + " town. This is were Reilly is commanding his army from.";
            dialogue = new Label(text);
            continueButton = new Button("Go to the town centre");
            continueButton.setStyle("-fx-font:25 Arial");
            buttonBox.getChildren().clear();
            buttonBox.getChildren().add(continueButton);
            dialogue.setFont(new Font(33));
        } else { // player ready to move on to next dialogue
            text = "Sir Reilly: So we meet again, " + player.getName() + ". I knew you were special"
                    + " the moment those imperial scum sent you up to Aswana. Unfortunatly for you,"
                    + " still not special enough. There is no stopping me and my army out of hell. "
                    + "I'm giving you the chance to surrender now, and run back to the Duke's manor."
                    + " If you still wish to die, I will be forced to oblige you.";
            dialogue.setText(text);
            buttonBox.getChildren().clear();
            choice1 = new Button("You're going down traitor!\nEn guard!");
            choice2 = new Button("Yeah I think I'll leave Aswana.\nGood luck with your conquest "
                    + "or whatever");
            choice1.setStyle("-fx-font:25 Arial");
            choice2.setStyle("-fx-font:25 Arial");
            buttonBox.getChildren().addAll(choice1, choice2);
            progress = false;
            dialogue.setFont(new Font(35));
        }
        pane.getChildren().addAll(dialogue, buttonBox);
    }

    public static void lvl4() { // boss level
        text = "Legionary: I can't believe it... You actually defeated him. Brave "
                + player.getName() + ", you truly are a hero.\n\nDuke: You have certainly proved "
                + "yourself today soldier, and we will forever be in your debt. Ruling over "
                + "these frotnier provinces is just not for me I'm afraid. You seem much more "
                + "qualified than I. And for that reason, I hearby declare you, "
                + player.getName() + ", the new Duke of Aswana!\n\nNarrator: With Sir Reilly"
                + " defeated, and our hero promoted to Duke of Aswana, this is where our story"
                + " draws to a close. But despite this chapter ending, Aswana still remains "
                + "a vast land of uncountable secrets and mysteries. And while Sir Reilly may "
                + "be defeated, one can be almost positive that there will be another villian "
                + "looking to seize power in Aswana.";
        dialogue = new Label(text);
        continueButton = new Button("Continue");
        continueButton.setStyle("-fx-font:25 Arial");
        buttonBox.getChildren().clear();
        buttonBox.getChildren().add(continueButton);
        dialogue.setFont(new Font(30));
        gameLvl++; // now on lvl 5 (which is just the completed game screen)
        pane.getChildren().addAll(dialogue, buttonBox);
    }

    public static Scene passScene() {
        pane = new FlowPane();
        if (gameLvl == 0) {  // player on lvl 0 (tutorial level)
            tutorial();
        } else if (gameLvl == 1) { // player on lvl 1
            lvl1();
        } else if (gameLvl == 2) { // player on lvl 2
            lvl2();
        } else if (gameLvl == 3) { // player on lvl 3
            lvl3();
        } else if (gameLvl == 4) { // player on lvl 4
            lvl4();
        }
        pane.setAlignment(Pos.CENTER);
        dialogue.setPrefWidth(1100); // setting max length of a single line of text to 1100 pixels
        dialogue.setWrapText(true); // when text hits 1100 pixels, it wraps

        dialogueScene = new Scene(pane, 1200, 760);
        return dialogueScene;
    }
}
