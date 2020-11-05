package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public abstract class Enemy extends MovingEntity {
    public Enemy(int x, int y, Image img) {
        super(x, y, img);
    }
}
