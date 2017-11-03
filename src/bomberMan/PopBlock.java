package bomberMan;

import bomberMan.item.BombUpItem;
import bomberMan.item.Item;
import bomberMan.item.PowerItem;
import bomberMan.item.SpeedItem;
import bomberMan.manager.ImageManager;
import processing.core.PApplet;

public abstract class PopBlock extends Block {
    private Item item;
    private int damage;

    public PopBlock(int x, int y) {
        super(x, y);

        int fifth = (int) (Math.random()*5);

        if (fifth == 0)
            item = new BombUpItem(x, y);
        else if (fifth == 1)
            item = new PowerItem(x, y);
        else if (fifth == 2)
            item = new SpeedItem(x, y);
        else
            item = null;
    }


    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Item getItem() {
        return item;
    }

    public void readImg(PApplet pApplet, int select) {
        ImageManager imageManager = new ImageManager(pApplet);
        setImg(imageManager.readBlock(select));
    }

    @Override
    public void draw(PApplet pApplet) {
        pApplet.image(getImg(),40*getX(), 40*getY());
    }
}
