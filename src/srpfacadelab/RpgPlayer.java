package srpfacadelab;

import java.util.List;
import java.util.ArrayList;

//RPGPlayer is our Facade
public class RpgPlayer {
    public static final int MAX_CARRYING_CAPACITY = 1000;

    private final IGameEngine gameEngine;

    private int health;

    private int maxHealth;

    private int armour;

    private List<Item> inventory;

    // How much the player can carry in pounds
    private int carryingCapacity;

    public RpgPlayer(IGameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.inventory = new Inventory(gameEngine, this);
        carryingCapacity = MAX_CARRYING_CAPACITY;
    }

    public int getCarryingCapacity() {
        return carryingCapacity;
    }
    private void setCarryingCapacity(int carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }
    public List<Item> getInventory() {return inventory;}

    private void calculateStats() {
        for (Item i: inventory) {
            armour += i.getArmour();
        }
    }

    public void takeDamage(int damage) {
        if (damage < armour) {
            gameEngine.playSpecialEffect("parry");
        }

        
        int damageToDeal;

        //for part 2... checks if capacity is less than half in which case less damage is taken
        if (inventory.getCarryingCapacity() > (0.5 * inventory.MAX_CARRYING_CAPACITY))
            damageToDeal = (damage * .75) - armour;
        else
            damageToDeal = damage - armour;

        health -= damageToDeal;

        gameEngine.playSpecialEffect("lots_of_gore");
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getArmour() {
        return armour;
    }

    private void setArmour(int armour) {
        this.armour = armour;
    }


}
