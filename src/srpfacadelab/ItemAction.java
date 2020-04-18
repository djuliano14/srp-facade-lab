  
package srpfacadelab;
import java.util.List;

public class ItemAction {
    

    public void useItem(RpgPlayer player, Item item) {
        if (item.getName().equals("Stink Bomb"))
        {
            List<IEnemy> enemies = player.getGameEngine().getEnemiesNear(player);

            for (IEnemy enemy: enemies){
                enemy.takeDamage(100);
            }
        }
    }

    public boolean pickUpItem(RpgPlayer player, Item item) {
        int weight = player.calculateCarryingCapacity();
        if (weight + item.getWeight() > player.getCarryingCapacity())
            return false;

        if (item.isUnique() && player.checkIfItemExistsInInventory(item))
            return false;

        // Don't pick up items that give health, just consume them.
        if (item.getHeal() > 0) {
            player.health += item.getHeal();

            if (player.health > player.maxHealth)
                player.health = player.maxHealth;

            if (item.getHeal() > 500) {
                player.getGameEngine().playSpecialEffect("green_swirly");
            }

            return true;
        }

        if (item.isRare() && item.isUnique()) {
            player.getGameEngine().playSpecialEffect("blue_swirly");
        } else if (item.isRare())
            player.getGameEngine().playSpecialEffect("cool_swirly_particles");

        player.getInventory().add(item);

        player.calculateStats();

        return true;
    }
}