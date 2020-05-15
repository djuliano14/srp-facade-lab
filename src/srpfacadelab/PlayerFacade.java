  
package srpfacadelab;

import java.util.List;
import java.util.ArrayList;



public class PlayerFacade{
    RpgPlayerAction action = new RpgPlayerAction();
    Damage damage = new Damage();

    public void useItem(Item item, IGameEngine gameEngine, RpgPlayer player) {
        action.useItem(item, gameEngine, player);
    }

    public boolean pickUpItem(Item item, IGameEngine gameEngine, RpgPlayer player) {
        return action.pickUpItem(item, gameEngine, player);
    }

    public void takeDamage(int damage, IGameEngine gameEngine, RpgPlayer player, int weight) {
        damage.takeDamage(damage, gameEngine, player, weight);
    }
}