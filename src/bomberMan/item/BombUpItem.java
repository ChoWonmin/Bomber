package bomberMan.item;

import bomberMan.item.Item;
import bomberMan.manager.ImageManager;
import processing.core.PApplet;

public class BombUpItem extends Item {
    public BombUpItem(int x, int y) {
        super(x, y);
    }

    @Override
    public void readImg(PApplet pApplet, int select) {
        ImageManager imageManager = new ImageManager(pApplet);
        setImg(imageManager.readItem(select));
    }

    @Override
    public void draw(PApplet pApplet) {
        readImg(pApplet,2);
        pApplet.image(getImg(),40*getX()+8, 40*getY()+8);
    }
}
