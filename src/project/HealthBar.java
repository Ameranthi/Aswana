package project;

/* HealthBar objects have a current health, and a capacity. There are methods to replenish the 
health bar, and to damage the health bar. 
 */
public class HealthBar {

    private int capacity; // maximum health
    private int health; // current health

    public HealthBar(int c) {
        capacity = c;
        health = c;
    }

    public int getHealth() {
        return health;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setHealth(int h) {
        health = h;
    }

    public void setCapacity(int c) {
        capacity = c;
    }

    public void takeDamage(int d) {
        if (health - d >= 0) { // if the damage doesnt take health below 0
            health -= d;
        } else { // if the damage is more than the health remaining
            health = 0; 
        }

    }

    public void replenish(int r) {
        if (health + r <= capacity) { // if the replenish doesnt take the health over capacity
            health += r;
        } else { // if the health replenished causes the health to be over capaity
            health = capacity; // health is now full
        }
    }
}
