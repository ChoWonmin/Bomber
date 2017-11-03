package bomberMan.manager;

import processing.core.PApplet;
import processing.core.PImage;

public class ImageManager {

    private PApplet pApplet;

    public ImageManager(PApplet pApplet) {
        this.pApplet = pApplet;
    }

    public  PImage readBlock(int select) {
        PImage blockImage;
        PImage[] block;
        blockImage = pApplet.loadImage("src/images/bomberman-block.png");
        block = new PImage[12];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 4; j++)
                block[j * 3 + i] = blockImage.get(40 * i, 40 * j, 40, 40);
        return block[select];
    }
    public PImage readGamer(int select){
        PImage gamerImage;
        PImage[] gamer;
        gamerImage = pApplet.loadImage("src/images/bomberman-stay.png");
        gamer = new PImage[12];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 4; j++)
                gamer[j * 3 + i] = gamerImage.get(20 * i, 32 * j, 20, 32);
        return gamer[select];
    }
    public PImage readMoveGamer(int select){
        PImage movementImage;
        PImage[] movement;
        movementImage = pApplet.loadImage("src/images/bomberman-movement.png");
        movement = new PImage[20];
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 4; j++)
                movement[j * 5 + i] = movementImage.get(20 * i, 32 * j, 20, 32);
        return movement[select];
    }

    public PImage readItem(int select){
        PImage itemImage;
        PImage[] items;
        itemImage=pApplet.loadImage("src/images/bomberman-items.png");
        items=new PImage[20];
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 2; j++)
                items[j * 5 + i] = itemImage.get(24 * i, 24 * j, 24, 24);
        return items[select];
    }
    public PImage readEffect(int select){
        PImage effectImage;
        PImage[] effect;
        effectImage=pApplet.loadImage("src/images/bomberman-effect.png");
        effect =new PImage[45];

        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 5; j++)
                effect[j * 9 + i] = effectImage.get(24 * i, 24 * j, 24, 24);
        return effect[select];
    }


}