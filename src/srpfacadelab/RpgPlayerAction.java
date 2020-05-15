package srpfacadelab;

import java.util.List;
import java.util.ArrayList;

public class RpgPlayerAction{
    public void useItem(Item item, IGameEngine gameEngine, RpgPlayer player) {
        if (item.getName().equals("Stink Bomb"))
        {
            List<IEnemy> enemies = gameEngine.getEnemiesNear(player);

            for (IEnemy enemy: enemies){
                enemy.takeDamage(100);
            }
        }
    }

    public boolean pickUpItem(Item item, IGameEngine gameEngine, RpgPlayer player) {
        int weight = calculateInventoryWeight(player);
        if (weight + item.getWeight() > player.getCarryingCapacity())
            return false;

        if (item.isUnique() && checkIfItemExistsInInventory(item, player))
            return false;

        // Don't pick up items that give health, just consume them.
        if (item.getHeal() > 0) {
            int health = player.getHealth();
            player.setHealth(health += item.getHeal());

            if (player.getHealth() > player.getMaxHealth())
            player.setHealth(player.getMaxHealth());

            if (item.getHeal() > 500) {
                gameEngine.playSpecialEffect("green_swirly");
            }

            return true;
        }

        if (item.isRare()){
            gameEngine.playSpecialEffect("cool_swirly_particles");
            if(item.isUnique()){
                gameEngine.playSpecialEffect("blue_swirly");
            }
        }
        //player.inventory.add(item);
        player.addItemToInventory(item);

        calculateStats(player);

        return true;
    }

    private void calculateStats(RpgPlayer player) {
        for (Item i: player.getInventory()) {
            //armour += i.getArmour();
            player.setArmour(i.getArmour());
        }
    }

    private boolean checkIfItemExistsInInventory(Item item, RpgPlayer player) {
        for (Item i: player.getInventory()) {
            if (i.getId() == item.getId())
                return true;
        }
        return false;
    }

    private int calculateInventoryWeight(RpgPlayer player) {
        int sum=0;
        for (Item i: player.getInventory()) {
            sum += i.getWeight();
        }
        return sum;
    }
}