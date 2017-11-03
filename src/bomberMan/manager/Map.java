package bomberMan.manager;

import bomberMan.*;
import bomberMan.item.Item;
import processing.core.PApplet;

import java.util.ArrayList;

public class Map{
    private Room[][] rooms;
    private PApplet pApplet;
    private FlameManager flameManager;

    public Map(PApplet pApplet,ArrayList<Gamer> gamers) {
        rooms = new Room[20][15];
        this.pApplet = pApplet;
        flameManager = new FlameManager(rooms,gamers);
    }

    public void settingBorder() {
        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 15; j++)
                if (i == 0 || i == 19 || j == 0 || j == 14) {
                    SolidBlock solidBlock = new SolidBlock(i, j);
                    rooms[i][j] = solidBlock;
                    solidBlock.readImg(pApplet, 9);
                }
    }

    public void settingPopBlock() {
        for (int i = 1; i < 19; i++)
            for (int j = 1; j < 14; j++) {
                int half = (int) (Math.random() * 3);
                if (half == 0) {
                    PopBlock popFirstBlock = new PopFirstBlock(i, j);
                    rooms[i][j] = popFirstBlock;
                    popFirstBlock.readImg(pApplet, 1);
                }else if(half == 1){
                    PopBlock popSecondBlock = new PopSecondBlock(i, j);
                    rooms[i][j] = popSecondBlock;
                    popSecondBlock.readImg(pApplet, 4);
                }
            }// for j
    }

    public void settingSolidBlock() {
        int width = (int) (Math.random() * 2) + 2;
        int height = (int) (Math.random() * 2) + 2;

        for (int i = 1; i < 19; i++)
            for (int j = 1; j < 14; j++)
                if (i % width == 0 && j % height == 0) {
                    SolidBlock solidBlock = new SolidBlock(i, j);
                    rooms[i][j] = solidBlock;
                    solidBlock.readImg(pApplet, 9);
                }
    }

    public void settingGamer(ArrayList<Gamer> gamers) {
        int x = 1;
        int y = 1;

        for(Gamer gamer:gamers){
            if(gamer.getX()==18) {
                x = -1;
                y = -1;
            }
            rooms[gamer.getX()][gamer.getY()] = gamer;
            rooms[gamer.getX()+x][gamer.getY()] = null;
            rooms[gamer.getX()][gamer.getY()+y] =null;
        }

    }

    public void draw(PApplet pApplet){
        for(int i=0;i<20;i++)
            for (int j=0;j<15;j++) {
                if(rooms[i][j] == null ) continue;
                rooms[i][j].draw(pApplet);
            }
    }

    public Room[][] getRooms() {
        return rooms;
    }

}