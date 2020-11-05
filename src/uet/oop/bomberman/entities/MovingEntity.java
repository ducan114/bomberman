package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public abstract class MovingEntity extends Entity {
    protected int previousX;
    protected int previousY;
    protected boolean alive = true;

    public MovingEntity(int x, int y, Image img) {
        super(x, y, img);
        previousX = x;
        previousY = y;
    }
    public void goLeft() {
        previousX = x;
        previousY = y;
        x--;
        handleCollision();
    }

    public void goRight() {
        previousX = x;
        previousY = y;
        x++;
        handleCollision();
    }

    public void goUp() {
        previousX = x;
        previousY = y;
        y--;
        handleCollision();
    }

    public void goDown() {
        previousX = x;
        previousY = y;
        y++;
        handleCollision();
    }

    public void render(GraphicsContext gc) {
        if (!changed) return;
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        ImageView iv = new ImageView(img);
        Image base = iv.snapshot(params, null);

        gc.drawImage(base, x * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE);
        changed = false;
    }
    public void handleCollision() {
        BombermanGame.stillObjects.forEach(g -> {
            if (g.getX() == previousX && g.getY() == previousY) {
                g.update();
            }
            if (g.getX() == x && g.getY() == y) {
                if (g.layer > layer) {
                    x = previousX;
                    y = previousY;
                }
            }
        });
    }
}
