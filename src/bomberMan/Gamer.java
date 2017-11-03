package bomberMan;

import bomberMan.item.BombUpItem;
import bomberMan.item.PowerItem;
import bomberMan.item.SpeedItem;
import bomberMan.manager.ImageManager;
import processing.core.PApplet;

import java.util.*;

public class Gamer extends Room {
    private int state;
    private float speed;
    private int bombCount;
    private int powerCount;
    private ArrayList<Bomb> bombs;
    private long startMoveTime;
    private int select;

    public Gamer(int x, int y) {
        super(x, y);

        state = 0;
        speed = 0.5f;
        bombCount = 1;
        powerCount = 1;
        bombs = new ArrayList<Bomb>();
        select = 12;
    }

    public void increaseSpeed() {
        if(this.speed>0.2)
            this.speed -= 0.15;
    }

    public void increaseBombCount() {
        if(this.bombCount<3)
            this.bombCount ++;
    }

    public void increasePowerCount() {
        if(this.powerCount<3)
            this.powerCount ++;
    }

    public ArrayList<Bomb> getBombs() {
        return bombs;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state, long startMoveTime){
        this.startMoveTime = startMoveTime;
        this.state = state;

        switch (state){
            case 1: select=28;
                break;
            case 2: select=18;
                break;
            case 3: select=23;
                break;
            case 4: select=13;
                break;
        }

    }

    public void addBomb(){
        if (bombs.size() < bombCount && state!=5) {
            Bomb bomb = new Bomb(getX(), getY(), powerCount);
            bombs.add(bomb);
        }
    }

    public void readImg(PApplet pApplet, int select) {
        ImageManager imageManager = new ImageManager(pApplet);

        if(select<13)
            setImg(imageManager.readGamer(select));
        else
            setImg(imageManager.readMoveGamer(select-13));
    }

    public boolean move(int x, int y, Room[][] rooms) {
        boolean possibleMove = false;

        if (!(rooms[x][y] instanceof SolidBlock) && !(rooms[x][y] instanceof PopBlock)
                && !(rooms[x][y] instanceof Bomb) && getState()==0)
            possibleMove = true;

        if(possibleMove) {
            setX(x);
            setY(y);
            eatItem(x, y, rooms);
            rooms[x][y] = this;
        }
        return possibleMove;
    }

    public void eatItem(int x,int y,Room[][] rooms) {
        if (rooms[x][y] instanceof SpeedItem)
            increaseSpeed();
        else if (rooms[x][y] instanceof PowerItem)
            increasePowerCount();
        else if (rooms[x][y] instanceof BombUpItem)
            increaseBombCount();
    }

    public void makeBomb(ArrayList<Gamer> gamers, Room[][] rooms){
        for(int i=0;i<getBombs().size();i++){
            Bomb bomb = getBombs().get(i);
            rooms[bomb.getX()][bomb.getY()] = bomb;
            if( System.currentTimeMillis() - bomb.getCreateTime() > 3000 ) {
                getBombs().get(i).explosiveBomb(rooms, gamers);
                getBombs().remove(i);
            }
        }
    }

    @Override
    public void draw(PApplet pApplet) {

        long currentTime = System.currentTimeMillis();
        long motion = (currentTime/800)%2;
        long moveTime = currentTime - startMoveTime;
        float speedTime = speed*1000;

        switch (state){
            case 0:
                if( motion == 0)
                    pApplet.image(getImg(),40*getX()+8, 40*getY()-2);
                else if(motion == 1)
                    pApplet.image(getImg(),40*getX()+8, 40*getY()+2);
                break;
            case 1:
                if(moveTime < speedTime) {
                    readImg(pApplet,select);
                    pApplet.image(getImg(), 40 * (getX()+1 - (float)moveTime / speedTime)+8, 40 * getY() - 2);
                }else {
                    readImg(pApplet,9);
                    setState(0, System.currentTimeMillis());
                }break;
            case 2:
                if(moveTime < speedTime) {
                    readImg(pApplet,select);
                    pApplet.image(getImg(), 40 * (getX()-1 + (float)moveTime / speedTime)+8, 40 * getY() - 2);
                }else {
                    setState(0, System.currentTimeMillis());
                    readImg(pApplet,5);
                }break;
            case 3:
                if(moveTime < speedTime) {
                    readImg(pApplet,select);
                    pApplet.image(getImg(), 40 * getX() +8, 40 * (getY()+1-(float)moveTime/speedTime) - 2);
                }else {
                    setState(0, System.currentTimeMillis());
                    readImg(pApplet,8);
                }break;
            case 4:
                if(moveTime < speedTime ) {
                    readImg(pApplet,select);
                    pApplet.image(getImg(), 40 * getX()+8, 40 * (getY()-1+(float)moveTime/speedTime) - 2);
                }else {
                    setState(0, System.currentTimeMillis());
                    readImg(pApplet,2);
                }break;
            case 5:
                long fourth = (moveTime/100)%4;
                if(fourth == 0 )
                    readImg(pApplet,9);
                else if(fourth == 1 )
                    readImg(pApplet,5);
                else if(fourth == 2 )
                    readImg(pApplet,8);
                else if(fourth == 3 )
                    readImg(pApplet,2);
                if(moveTime < 1000)
                    pApplet.image(getImg(),40*getX()+8, 40*getY());
        }

    }

}
