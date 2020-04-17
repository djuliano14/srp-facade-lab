package srpfacadelab;

import java.util.List;
import java.util.ArrayList;

public class Inventory {
    public static final int MAX_CARRYING_CAPACITY = 1000;
    private int carryingCapacity; // How much the player can carry in pounds
    private final IGameEngine gameEngine;
    private List<Item> inventory;
    private RpgPlayer player;

    public Inventory(IGameEngine gameEngine, RpgPlayer player){
        this.gameEngine = gameEngine;
        this.player = player;
        inventory = new ArrayList<Item>();
        carryingCapacity = MAX_CARRYING_CAPACITY;

    }

    public int getCarryingCapacity() {
        return carryingCapacity;
    }
    private void setCarryingCapacity(int carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }
    public List<Item> getInventory() {return inventory;}


    public void useItem(Item item) {
        if (item.getName().equals("Stink Bomb"))
        {
            List<IEnemy> enemies = gameEngine.getEnemiesNear(player);
            for (IEnemy enemy: enemies){
                enemy.takeDamage(100);
            }

        }
    }
    public boolean pickUpItem(Item item) {
        int weight = calculateInventoryWeight();
        if (weight + item.getWeight() > carryingCapacity)
            return false;

        if (item.isUnique() && checkIfItemExistsInInventory(item))
            return false;

        // Don't pick up items that give health, just consume them.
        if (item.getHeal() > 0) {
            player.setHealth(player.getHealth() + item.getHeal());

            if (player.getHealth() > player.getMaxHealth())
                player.setHealth(player.getMaxHealth());

            if (item.getHeal() > 500)
                gameEngine.playSpecialEffect("green_swirly");

            return true;
        }

        //part one of assignment
        if(item.isRare() && item.isUnique())
            gameEngine.playSpecialEffect("blue_swirly");
        else if(item.isRare())
            gameEngine.playSpecialEffect("cool_swirly_particles");

        inventory.add(item);

        player.calculateStats();

        return true;
    }
    private boolean checkIfItemExistsInInventory(Item item) {
        for (Item i: inventory) {
            if (i.getId() == item.getId())
                return true;
        }
        return false;
    }
    private int calculateInventoryWeight() {
        int sum=0;
        for (Item i: inventory) {
            sum += i.getWeight();
        }
        return sum;
    }
}