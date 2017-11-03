package bomberMan;

import bomberMan.manager.ImageManager;
import processing.core.PApplet;

public class SolidBlock extends Block{

    public SolidBlock(int x, int y) {
        super(x, y);
    }


    public void readImg(PApplet pApplet, int select) {
        ImageManager imageManager = new ImageManager(pApplet);
        setImg(imageManager.readBlock(6));
    }

    @Override
    public void draw(PApplet pApplet) {
        pApplet.image(getImg(),40*getX(), 40*getY());
    }
}
