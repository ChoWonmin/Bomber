package bomberMan.item;

import bomberMan.Room;

public abstract class Item extends Room {
    public Item(){
        super(0,0);
    }

    public Item(int x, int y) {
        super(x, y);
    }
}
