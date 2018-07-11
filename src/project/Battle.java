package project;

/* Battle is a subclass of GameWindow. This class is used to pass the Scene battleScene. The 
battleScene shows the battle. At the start of each battle, an emeny is randomly generated (based
on what level the player is on). The window is arranged so that on the right is the hero's H.P.,
picture, and name. The left has the enemy's h.p., name, and picture. The centre has the actions of
the previous round, and the bottom of the window has the buttons for the player to choose from. 
When the player chooses an action, they always act first. If the enemy is still alive, then they
may attack. If their hp is at a certain number, the enemy heals. If the enemy being fought is the 
boss, they can charge a strong attack, and then unleash it on the next turn. When the hero wins,
their experience points incriment, based on the level of enemy they defeated
 */

import java.util.Random;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

public class Battle extends GameWindow {

    private static Random rand = new Random();
    private static boolean result; // result of who won the fight
    private static boolean charging = false; // if the enemy is charging an attack

    private static Label narration, heroHP, enemyHP;
    private static Text heroName, enemyName;

    public static Scene passScene() {
        enemy = new Enemy(gameLvl); // creating a random enemy, based on gameLvl
        fightStatus = true; //  a fight commences
        String t = "Choose your action!";
        heroName = new Text(player.getName());
        enemyName = new Text(enemy.getName());
        heroName.setFont(new Font(15));
        enemyName.setFont(new Font(15));
        heroHP = new Label("HP: " + player.getHP());
        enemyHP = new Label("HP: " + enemy.getHP());
        heroHP.setFont(new Font(15));
        enemyHP.setFont(new Font(15));
        BorderPane border = new BorderPane();
        HBox buttonBox = new HBox();
        VBox heroBox = new VBox();
        VBox enemyBox = new VBox();

        // picture of the hero
        ImageView heroViewer = new ImageView();
        heroViewer.setFitHeight(550);
        heroViewer.setFitWidth(299);
        heroViewer.setImage(player.getPic());

        // picture of the enemy
        ImageView enemyViewer = new ImageView();
        enemyViewer.setFitHeight(550);
        enemyViewer.setFitWidth(342);
        enemyViewer.setImage(enemy.getPic());

        heroBox.setPadding(new Insets(10));
        enemyBox.setPadding(new Insets(10));
        heroBox.setSpacing(7);
        enemyBox.setSpacing(7);
        buttonBox.setPadding(new Insets(15, 12, 15, 12));
        buttonBox.setSpacing(100);
        buttonBox.setStyle("-fx-background-color:#336699;");
        narration = new Label(t);
        narration.setFont(new Font(25));
        attack = new Button("Attack");
        attack.setStyle("-fx-font:25 Arial");
        heal = new Button("Heal");
        heal.setStyle("-fx-font:25 Arial");
        doNothing = new Button("Do nothing");
        doNothing.setStyle("-fx-font:25 Arial");

        buttonBox.getChildren().addAll(attack, heal, doNothing); // bottom of the screen
        heroBox.getChildren().addAll(heroName, heroHP, heroViewer); // left side of the screen
        enemyBox.getChildren().addAll(enemyName, enemyHP, enemyViewer); // right side of the screen
        border.setLeft(heroBox);
        border.setRight(enemyBox);
        border.setCenter(narration);
        border.setBottom(buttonBox);
        buttonBox.setAlignment(Pos.CENTER);
        heroBox.setAlignment(Pos.CENTER);
        enemyBox.setAlignment(Pos.CENTER);
        battleScene = new Scene(border, 1200, 760);
        return battleScene;

    }

// method called once one of the battle buttons (attack, heal, do Nothing) is pressed
    public static void buttonPress(int i) {
        int dmg, hpGain;
        switch (i) {
            case 1: // attack was pressed
                dmg = player.heroAttack(player.getLVL());
                enemy.damageEnemy(dmg);
                narration.setText(player.getName() + " does " + dmg + " damage!");
                break;
            case 2: // heal was pressed
                hpGain = player.healHero(player.getLVL());
                narration.setText(player.getName() + " heals for " + hpGain + " H.P.");
                break;
            case 3: // do nothing was pressed
                narration.setText(player.getName() + " waits for a better opening.");
                break;
            default:
                break;
        }
        if (enemy.getHP() <= 0) { // if enemy is dead
            boolean lvlUp; // counter for if hero lvls up due to exp gain
            result = true; // hero wins battle
            // gaining experience from win, and healing hero
            lvlUp = player.gainExperience(enemy.getLVL());
            ExpGain.setExp(enemy.getLVL()); // passing exp gained to exp window
            ExpGain.setLvlUp(lvlUp); // passing if hero lvls up to exp window
        } else { // if enemy isnt dead (they will attack, heal, or charge)
            if (charging) { // if the boss has charged their super attack 
                charging = false;
                player.damageHero(13); // charged attack does 13 damage
                addText(narration, enemy.getName() + " Unleashes his mega canon\n" + enemy.getName()
                        + " deals 13 damage!");
            } else if (enemy.getLVL() == 4 && enemy.getHP() == 30) { // boss has 30 health left
                charging = true; // boss charges his strong attack
                addText(narration, enemy.getName() + " is charging his mega canon!");
            } else if (enemy.getLVL() == 4 && enemy.getHP() == 20) { // boss has 20 health left
                charging = true; // boss charges his strong attack
                addText(narration, enemy.getName() + " is charging his mega canon!");
            } // if enemy has 5, 10, or 15 health remaining(enemy is healed)
            else if (enemy.getHP() == 5 || enemy.getHP() == 10 || enemy.getHP() == 15) {
                switch (enemy.getLVL()) {
                    case 4:
                        // if enemy is the boss
                        hpGain = enemy.healEnemy(rand.nextInt(7) + 1); // healing enemy 
                        addText(narration, enemy.getName() + " heals for " + hpGain + " H.P.");
                        break;
                    case 3:
                        // if enemy is the lvl 3
                        hpGain = enemy.healEnemy(rand.nextInt(4) + 1); // healing enemy 
                        addText(narration, enemy.getName() + " heals for " + hpGain + " H.P.");
                        break;
                    case 2:
                        // if enemy is lvl 2
                        hpGain = enemy.healEnemy(rand.nextInt(3) + 1); // healing enemy 
                        addText(narration, enemy.getName() + " heals for " + hpGain + " H.P.");
                        break;
                    default:
                        // if enemy is lvl 1
                        hpGain = enemy.healEnemy(rand.nextInt(2) + 1); // healing enemy 
                        addText(narration, enemy.getName() + " heals for " + hpGain + " H.P.");
                        break;
                }
            } else { // enemy attacks
                dmg = enemy.enemyAttack();
                player.damageHero(dmg);
                addText(narration, enemy.getName() + " does " + dmg + " damage!");
            }
            if (player.getHP() <= 0) { // hero dies
                result = false; // player lost the fight

            }

        }
        if (player.getHP() <= 0 || enemy.getHP() <= 0) { // when either party is dead
            fightStatus = false; // fight is over
        }

        // updating Health points on window
        heroHP.setText("HP: " + player.getHP());
        enemyHP.setText("HP: " + enemy.getHP());
    }

    public static boolean fightResult() { // returns if hero wins fight or not
        return result;
    }

}
