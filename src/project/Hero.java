package project;

/* Hero objects have a health bar , a name, a picture, a level, a certain number of exp points, and 
a type (swordsman rogue mage). Both the attack and heal methods are based on the level of the hero,
and some element of randomness. The hero levels up when it gains experience, and the experience 
reaches a certain number. When leveling up, the level is incrimented, and the experience is set back
down to zero.
 */

import java.util.Random;
import javafx.scene.image.Image;

public class Hero {

    private HealthBar HP;
    private int LVL, Experience = 0;
    private String name;
    private final String TYPE;
    private Image heroPic;
    Random rand = new Random();

    public Hero(int i, String n, String t) {
        LVL = i;
        HP = new HealthBar(10);
        name = n;
        TYPE = t;
        
        //choosing picture based on the type(class) of the hero
        if (TYPE.equalsIgnoreCase("swordsman")) {
            heroPic = new Image(getClass().getResourceAsStream("/Swordsman.png"));
        };
        if (TYPE.equalsIgnoreCase("mage")) {
            heroPic = new Image(getClass().getResourceAsStream("/Mage.png"));

        }
        if (TYPE.equalsIgnoreCase("rogue")) {
            heroPic = new Image(getClass().getResourceAsStream("/Rogue.png"));

        }
    }

    public Image getPic() {
        return heroPic;
    }

    public void setName(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return TYPE;
    }

    public int getExperience() {
        return Experience;
    }

    public int getLVL() {
        return LVL;
    }

    public int getHP() {
        return HP.getHealth();
    }

    public void damageHero(int i) { // taking damage
        HP.takeDamage(i);
    }

    public int healHero(int i) { // how much hero heals, based on their level
        int heal;
        switch (i) {
            case 1:
                heal = rand.nextInt(4) + 1;
                HP.replenish(heal);
                return heal;
            case 2:
                heal = rand.nextInt(5) + 2;
                HP.replenish(heal);
                return heal;
            case 3:
                heal = rand.nextInt(6) + 3;
                HP.replenish(heal);
                return heal;
            case 4:
                heal = rand.nextInt(9) + 5;
                HP.replenish(heal);
                return heal;
            case 5: // healing hero to full
                HP.replenish(100);
            default:
                return 0;
        }
    }

    public int heroAttack(int i) { // how much damage hero does based on their level
        switch (i) {
            case 1:
                return rand.nextInt(2) + 1;
            case 2:
                return rand.nextInt(2) + 2;
            case 3:
                return rand.nextInt(2) + 3;
            case 4:
                return rand.nextInt(4) + 4;
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return name + " LEVEL:" + LVL + " HP:" + HP.getHealth();
    }

    public boolean gainExperience(int i) {
        /* method calculates the experience gained (based on passed in 
        enemy lvl), and levels up hero if they have enough experience. Also 
        returns true if hero levels up */
        boolean lvlUp = false;
        switch (i) {
            case 1:
                // if a level 1 enemy is defeated
                Experience += 1;
                // if hero levels up
                if ((LVL == 1 && Experience == 1) || (LVL == 2 && Experience == 2) || (LVL == 3 && Experience == 3)) {
                    lvlUp = true;
                    LVL++;
                    HP.setCapacity(HP.getCapacity() + 10); // increasing health bar by 10
                    Experience = 0;
                }
                break;
            case 2:
                // if a level 2 enemy is defeated
                Experience += 2;
                // if hero levels up
                if ((LVL == 2 && Experience == 2) || (LVL == 3 && Experience == 3)) {
                    lvlUp = true;
                    LVL++;
                    HP.setCapacity(HP.getCapacity() + 10); // increasing health bar by 10
                    Experience = 0;
                }
                break;
            case 3:
                // if a level 3 enemy is defeated
                Experience += 3;
                if (Experience == 3) { // if hero levels up
                    lvlUp = true;
                    LVL++;
                    HP.setCapacity(HP.getCapacity() + 10); // increasing health bar by 10
                    Experience = 0;
                }
                break;
            case 4:
                // if a level 4 enemy is defeated
                Experience += 1000;
                if (Experience >=4) { // if hero levels up
                    lvlUp = true;
                    LVL++;
                    HP.setCapacity(HP.getCapacity() + 10); // increasing health bar by 10
                    Experience = 0;
                } 
                break;
            default:
                break;
        }
        HP.setHealth(HP.getCapacity()); // healing hero to full
        return lvlUp;
    }
}
