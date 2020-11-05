package uet.oop.bomberman.entities;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends MovingEntity {

    public KeyCode keyCode;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
        layer = 1;
    }

    @Override
    public void handleCollision() {
        super.handleCollision();
        BombermanGame.entities.forEach(g -> {
            if (g.getX() == x && g.getY() == y) {
                if (g instanceof Enemy) {
                    alive = false;
                }
            }
        });
    }

    @Override
    public void goLeft() {
        super.goLeft();
        img = Sprite.player_left.getFxImage();
    }

    @Override
    public void goRight() {
        super.goRight();
        img = Sprite.player_right.getFxImage();
    }

    @Override
    public void goUp() {
        super.goUp();
        img = Sprite.player_up.getFxImage();
    }

    @Override
    public void goDown() {
        super.goDown();
        img = Sprite.player_down.getFxImage();
    }
    @Override
    public void update() {
        if (keyCode == KeyCode.LEFT) {
            goLeft();
            keyCode = null;
        }
        if (keyCode == KeyCode.RIGHT) {
            goRight();
            keyCode = null;
        }
        if (keyCode == KeyCode.UP) {
            goUp();
            keyCode = null;
        }
        if (keyCode == KeyCode.DOWN) {
            goDown();
            keyCode = null;
        }
        changed = true;
    }
}
