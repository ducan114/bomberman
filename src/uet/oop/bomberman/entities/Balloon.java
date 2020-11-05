package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Balloon extends MovingEntity {

    public Balloon(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void goLeft() {
        super.goLeft();
        img = Sprite.balloom_left1.getFxImage();
    }

    @Override
    public void goRight() {
        super.goRight();
        img = Sprite.balloom_right1.getFxImage();
    }

    @Override
    public void update() {
        goRight();
        changed = true;
    }

}
