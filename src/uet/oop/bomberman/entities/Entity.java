package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    protected int x;
    protected int y;
    protected Image img;
    public boolean changed;
    public int layer;

    public Entity(int x, int y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
        changed = true;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract void update();
}
