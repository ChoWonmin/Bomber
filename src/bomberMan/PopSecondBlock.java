package bomberMan;

import processing.core.PApplet;

public class PopSecondBlock extends PopBlock{
    public PopSecondBlock(int x, int y) {
        super(x, y);
        setDamage(2);
    }

    @Override
    public void draw(PApplet pApplet) {
        super.draw(pApplet);
        if(getDamage()==1)
            readImg(pApplet,5);
    }
}
