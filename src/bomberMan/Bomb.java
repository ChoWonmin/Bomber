package bomberMan;

import bomberMan.item.Item;
import bomberMan.manager.FlameManager;
import bomberMan.manager.ImageManager;
import processing.core.PApplet;

import java.util.ArrayList;

public class Bomb extends Room {
    private int power;
    private long createTime;

    public Bomb(int x, int y, int power){
        super(x, y);
        this.power = power;
        createTime = System.currentTimeMillis();
    }

    public int getPower() {
        return power;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void explosiveBomb(Room[][] rooms, ArrayList<Gamer> gamers) {
        int x = getX();
        int y = getY();

        FlameManager flameManager = new FlameManager(rooms,gamers);

        rooms[x][y] = new Flame(x,y, flameManager);

        for (int i = 1; i <= power; i++) {
            Room room = rooms[x-i][y];
            if (rooms[x-i][y] instanceof SolidBlock)
                break;
            else if (rooms[x-i][y] instanceof PopBlock) {
                Item item = ((PopBlock) room).getItem();
                if(((PopBlock) room).getDamage()==2) {
                    ((PopBlock) room).setDamage(1);
                }else if(item != null){
                    item.setX(x - i);
                    item.setY(y);
                    rooms[x-i][y] = item;
                }else
                    rooms[x-i][y] = new Flame(x - i, y, flameManager);
                break;
            }else if(rooms[x-i][y] instanceof Bomb){
                Bomb bomb = (Bomb)rooms[x-i][y];
                for(Gamer gamer:gamers)
                    gamer.getBombs().remove(bomb);
                bomb.explosiveBomb(rooms,gamers);
            }
            rooms[x-i][y] = new Flame(x - i, y, flameManager);
        }
        for (int i = 1; i <= power; i++) {
            Room room = rooms[x+i][y];
            if (rooms[x+i][y] instanceof SolidBlock)
                break;
            if (rooms[x+i][y] instanceof PopBlock) {
                Item item = ((PopBlock) room).getItem();

                System.out.println(item);
                if(((PopBlock) room).getDamage()==2) {
                    ((PopBlock) room).setDamage(1);
                }else if(item != null) {
                    item.setX(x+i);
                    item.setY(y);
                    rooms[x+i][y] = item;
                }else
                    rooms[x+i][y] = new Flame(x+i, y, flameManager);
                break;
            }else if(rooms[x+i][y] instanceof Bomb){
                Bomb bomb = (Bomb)rooms[x+i][y];
                for(Gamer gamer:gamers)
                    gamer.getBombs().remove(bomb);
                bomb.explosiveBomb(rooms,gamers);
            }
            rooms[x+i][y] = new Flame(x+i, y, flameManager);
        }
        for (int i = 1; i <= power; i++) {
            Room room = rooms[x][y-i];

            if (rooms[x][y-i] instanceof SolidBlock)
                break;
            if (rooms[x][y-i] instanceof PopBlock) {
                Item item = ((PopBlock) rooms[x][y-i]).getItem();
                if(((PopBlock) room).getDamage()==2) {
                    ((PopBlock) room).setDamage(1);
                }else if(item != null) {
                    item.setX(x);
                    item.setY(y-i);
                    rooms[x][y-i] = item;
                }else
                    rooms[x][y-i] = new Flame(x, y-i, flameManager);
                break;
            }else if(rooms[x][y-i] instanceof Bomb){
                Bomb bomb = (Bomb)rooms[x][y-i];
                for(Gamer gamer:gamers)
                    gamer.getBombs().remove(bomb);
                bomb.explosiveBomb(rooms,gamers);
            }
            rooms[x][y-i] = new Flame(x, y-i, flameManager);
        }
        for (int i = 1; i <= power; i++) {
            Room room = rooms[x][y+i];

            if (rooms[x][y+i] instanceof SolidBlock)
                break;
            if (rooms[x][y+i] instanceof PopBlock) {
                Item item = ((PopBlock) rooms[x][y+i]).getItem();
                if(((PopBlock) room).getDamage()==2) {
                    ((PopBlock) room).setDamage(1);
                }else if(item != null) {
                    item.setX(x);
                    item.setY(y+i);
                    rooms[x][y+i] = item;
                }else
                    rooms[x][y+i] = new Flame(x, y+i, flameManager);
                break;
            }else if(rooms[x][y+i] instanceof Bomb){
                Bomb bomb = (Bomb)rooms[x][y+i];
                for(Gamer gamer:gamers)
                    gamer.getBombs().remove(bomb);
                bomb.explosiveBomb(rooms,gamers);
            }
            rooms[x][y+i] = new Flame(x, y+i, flameManager);
        }
    }


    @Override
    public void readImg(PApplet pApplet, int select) {
        ImageManager imageManager = new ImageManager(pApplet);
        setImg(imageManager.readEffect(select));
    }

    @Override
    public void draw(PApplet pApplet) {
        readImg(pApplet,0);
        pApplet.image(getImg(),40*getX() +8, 40*getY()+8);
    }



}
