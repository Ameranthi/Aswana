package project;

/* Enemy objects have a health bar , a name, a picture, and a level. Depending on the level, an 
enemy's name and max health are randomly chosen. How much damage an enemy does in a turn is based
on their level, and a element on randomness.
 */
import java.util.Random;
import javafx.scene.image.Image;

public class Enemy {

    public HealthBar HP;
    private int LVL;
    private Random rand = new Random();
    private String name;
    private Image enemyPic;
    // possible enemy names store in string
    private String[] LvlOneEnemy = {"Slime", "Goblin", "Kobold", "Wolf", "Murloc", "Giant Spider",};
    private String[] LvlTwoEnemy = {"Bandit", "Golem", "Ent", "Ogre", "Skeleton", "Furbolg",};
    private String[] LvlThreeEnemy ={"Slime", "Goblin", "Kobold", "Wolf", "Murloc", "Giant Spider",};
    //private String[] LvlThreeEnemy = {"Dragon", "Giant", "Flame Elemental", "Angry Orc", "Assassin", "Troll"};
    private String[] Boss = {"Mecha Reilly"};

    public Enemy(int i) { // constructor with only a level passed in
        LVL = i;
        // health of enemy chosen based on level and a random number
        switch (i) {
            case 1:
                HP = new HealthBar(rand.nextInt(6) + 5);
                break;
            case 2:
                HP = new HealthBar(rand.nextInt(7) + 14);
                break;
            case 3:
                HP = new HealthBar(rand.nextInt(8) + 22);
                break;
            case 4:
                HP = new HealthBar(40); //bosses health always 40
                break;
            default:
                break;
        }
        setName(i); // calling method to randomly chose name based on level
        setPic(); // calling method to set the corresponding image
    }

    public Enemy(int i, int h, String s) {
        // constructor with passed in level, health, and name
        LVL = i;
        HP = new HealthBar(h);
        name = s;
        setPic();
    }

    private void setPic() { // sets the image based on the name of the enemy
        if (name.equalsIgnoreCase("Giant Spider")) {
            enemyPic = new Image(getClass().getResourceAsStream("/Giant-Spider.png"));
        } else if (name.equalsIgnoreCase("Goblin")) {
            enemyPic = new Image(getClass().getResourceAsStream("/Goblin.png"));
        } else if (name.equalsIgnoreCase("Slime")) {
            enemyPic = new Image(getClass().getResourceAsStream("/Slime.png"));
        } else if (name.equalsIgnoreCase("Murloc")) {
            enemyPic = new Image(getClass().getResourceAsStream("/Murloc.png"));
        } else if (name.equalsIgnoreCase("Kobold")) {
            enemyPic = new Image(getClass().getResourceAsStream("/Kobold.png"));
        } else if (name.equalsIgnoreCase("Wolf")) {
            enemyPic = new Image(getClass().getResourceAsStream("/Wolf.png"));
        } else if (name.equalsIgnoreCase("Skeleton")) {
            enemyPic = new Image(getClass().getResourceAsStream("/Skeleton.png"));
        } else if (name.equalsIgnoreCase("Mecha Reilly")) {
            enemyPic = new Image(getClass().getResourceAsStream("/Mecha_Reilly.png"));
        } else {
            enemyPic = new Image(getClass().getResourceAsStream("/GenericEnemy.png"));
        }
    }

    private void setName(int i) {
        // randomly choosing between 6 enemy names for each level of enemy
        switch (i) {
            case 1:
                name = LvlOneEnemy[rand.nextInt(6)];
                break;
            case 2:
                name = LvlTwoEnemy[4]; // only adds skeleton (only pic done
                break;
            case 3:
                name = LvlThreeEnemy[rand.nextInt(6)];
                break;
            case 4:
                name = Boss[0]; // only one boss name to choose from
                break;
            default:
                break;
        }
    }

    public Image getPic() {
        return enemyPic;
    }

    public String getName() {
        return name;
    }

    public int getLVL() {
        return LVL;
    }

    public int getHP() {
        return HP.getHealth();
    }

    public void damageEnemy(int i) {
        HP.takeDamage(i);
    }

    public int healEnemy(int i) {
        HP.replenish(i);
        return i;
    }

    public int enemyAttack() {
        // damage based on enemy level, and a random element
        switch (LVL) {
            case 1:
                return rand.nextInt(2) + 1;
            case 2:
                return rand.nextInt(3) + 2;
            case 3:
                return rand.nextInt(4) + 3;
            case 4:
                return rand.nextInt(6) + 4;
            default:
                return 0;
        }
    }

    public String toString() {
        return name + " LEVEL:" + LVL + " HP:" + HP.getHealth();
    }
}
