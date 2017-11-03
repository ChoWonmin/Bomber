package bomberMan.manager;

import bomberMan.Gamer;
import bomberMan.Room;
import bomberMan.bomberManInterface.FlameInterface;
import java.util.ArrayList;

public class FlameManager implements FlameInterface {

    private Room[][] rooms;
    private ArrayList<Gamer> gamers;

    public FlameManager(Room[][] rooms,ArrayList<Gamer> gamers){
        this.rooms = rooms;
        this.gamers = gamers;
    }

    @Override
    public void dieGamer(int x, int y) {
        for(Gamer gamer:gamers){
            if(x==gamer.getX() && y==gamer.getY())
                gamer.setState(5,System.currentTimeMillis());
        }
    }

    @Override
    public void destroyFrame(int x, int y) {
        rooms[x][y] = null;
    }
}
