package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class BombItem extends Entity {

    public BombItem(int x, int y, Image img) {
        super(x, y, img);
        layer = 1;
    }

    @Override
    public void update() {

    }
}