package bomberMan;

import bomberMan.bomberManInterface.FlameInterface;
import bomberMan.manager.ImageManager;
import processing.core.PApplet;

public class Flame extends Room {
    private long createTime;
    private FlameInterface flameInterface;

    public Flame(int x, int y, FlameInterface flameInterface) {
        super(x, y);
        this.flameInterface = flameInterface;

        createTime = System.currentTimeMillis();
    }

    @Override
    public void readImg(PApplet pApplet, int select) {
        ImageManager imageManager = new ImageManager(pApplet);
        setImg(imageManager.readEffect(select));
    }

    @Override
    public void draw(PApplet pApplet) {
        readImg(pApplet,3);
        pApplet.image(getImg(),40*getX()+8, 40*getY()+8);
        if(System.currentTimeMillis() - createTime > 200) {
            readImg(pApplet,9);
            pApplet.image(getImg(),40*getX()+8, 40*getY()+8);
        }
        if(System.currentTimeMillis() - createTime > 400) {
            readImg(pApplet,18);
            pApplet.image(getImg(),40*getX()+8, 40*getY()+8);
        }
        if(System.currentTimeMillis() - createTime > 600) {
            this.flameInterface.destroyFrame(getX(), getY());
        }
        flameInterface.dieGamer(getX(),getY());
    }

}
