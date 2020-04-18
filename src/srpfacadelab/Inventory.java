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