package bomerMan.test;

import bomberMan.Gamer;
import bomberMan.Room;
import bomberMan.manager.Map;
import processing.core.PApplet;
import processing.event.KeyEvent;

import java.util.ArrayList;

public class BomberManTest extends PApplet {
    @Override
    public void settings(){
        this.size(800,600);
    }

    private Room[][] rooms;
    private Map map;
    private ArrayList<Gamer> gamers;

    @Override
    public void setup(){
        gamers = new ArrayList<Gamer>();
        gamers.add(new Gamer(1,1));
        gamers.add(new Gamer(18,13));

        for(Gamer gamer:gamers)
            gamer.readImg(this,2);

        map = new Map(this,gamers);
        map.settingBorder();
        map.settingPopBlock();
        map.settingSolidBlock();
        map.settingGamer(gamers);

        rooms = map.getRooms();
    }

    public static void main(String[] args) {
        PApplet.main("bomerMan.test.BomberManTest");
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int keyCode = event.getKeyCode();

        moveGamer(keyCode);

        if (keyCode == 16)
            gamers.get(0).addBomb();
        if (keyCode == 32)
            gamers.get(1).addBomb();
    }

    private void moveGamer(int key){
        int player1X = gamers.get(0).getX();
        int player1Y = gamers.get(0).getY();

        int player2X = gamers.get(1).getX();
        int player2Y = gamers.get(1).getY();

        if(key==65) {
            gamers.get(0).readImg(this,9);
            if(gamers.get(0).move(--player1X,player1Y,rooms)) {
                gamers.get(0).setState(1, System.currentTimeMillis());
            }
        }else if(key==68) {
            gamers.get(0).readImg(this,5);
            if(gamers.get(0).move(++player1X,player1Y,rooms)) {
                gamers.get(0).setState(2, System.currentTimeMillis());
            }
        }else if(key==87) {
            gamers.get(0).readImg(this,8);
            if(gamers.get(0).move(player1X,--player1Y,rooms)) {
                gamers.get(0).setState(3, System.currentTimeMillis());
            }
        }else if(key==83) {
            gamers.get(0).readImg(this,2);
            if(gamers.get(0).move(player1X,++player1Y,rooms)) {
                gamers.get(0).setState(4, System.currentTimeMillis());
            }
        }

        if(key==37) {
            gamers.get(1).readImg(this,9);
            if(gamers.get(1).move(--player2X,player2Y,rooms)) {
                gamers.get(1).setState(1, System.currentTimeMillis());
            }
        }else if(key==39) {
            gamers.get(1).readImg(this,5);
            if(gamers.get(1).move(++player2X,player2Y,rooms)) {
                gamers.get(1).setState(2, System.currentTimeMillis());
            }
        }else if(key==38) {
            gamers.get(1).readImg(this,8);
            if(gamers.get(1).move(player2X,--player2Y,rooms)) {
                gamers.get(1).setState(3, System.currentTimeMillis());
            }
        }else if(key==40) {
            gamers.get(1).readImg(this,2);
            if(gamers.get(1).move(player2X,++player2Y,rooms)) {
                gamers.get(1).setState(4, System.currentTimeMillis());
            }
        }

    }

    @Override
    public void draw() {
        background(00,99,66);

        map.draw(this);

        for(Gamer gamer:gamers)
            gamer.makeBomb(gamers,rooms);
    }
}