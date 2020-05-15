package srpfacadelab;

import java.util.List;
import java.util.ArrayList;

public class Damage{
    public void takeDamage(int damage, IGameEngine gameEngine, RpgPlayer player, int weight) {
        if (damage < player.getArmour()) {
            gameEngine.playSpecialEffect("parry");
        }

        double damageToDeal = damage - player.getArmour();
        int health = player.getHealth();
        if(weight <= (0.5* player.getCarryingCapacity())){
            damageToDeal = 0.75*damageToDeal;
        }
        player.setHealth(health -= damageToDeal);

        gameEngine.playSpecialEffect("lots_of_gore");
    }
}