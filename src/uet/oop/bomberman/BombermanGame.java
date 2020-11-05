package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BombermanGame extends Application {
    
    public static int WIDTH;
    public static int HEIGHT;
    
    private GraphicsContext gc;
    private Canvas canvas;
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();

    private Scanner scanner;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws IOException {

        FileReader level = new FileReader("res/levels/Level1.txt");
        scanner = new Scanner(level);
        int lvl = scanner.nextInt();
        HEIGHT = scanner.nextInt();
        WIDTH = scanner.nextInt();
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();
        AnimationTimer timer = new AnimationTimer() {
            private long lastCall = 0;
            @Override
            public void handle(long l) {
                if (l - lastCall >= 2e8) {
                    update();
                    render();
                    lastCall = l;
                }
            }
        };
        timer.start();

        createMap();

        Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);
        scene.setOnKeyPressed(event -> {
            bomberman.keyCode = event.getCode();
        });
    }

    public void createMap() {
        scanner.nextLine();
        for (int i = 0; i < HEIGHT; i++) {
            String r = scanner.nextLine();
            for (int j = 0; j < WIDTH; j++) {
                Entity object;
                if (r.charAt(j) == '#') {
                    object = new Wall(j, i, Sprite.wall.getFxImage());
                } else {
                    object = new Grass(j, i, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                    if (r.charAt(j) == '*') {
                        object = new Brick(j, i, Sprite.brick.getFxImage());
                    } else if (r.charAt(j) == 'x') {
                        object = new Portal(j, i, Sprite.portal.getFxImage());
                        stillObjects.add(object);
                        object = new Brick(j, i, Sprite.brick.getFxImage());
                    } else if (r.charAt(j) == '1') {
                        object = new Balloon(j, i, Sprite.balloom_left1.getFxImage());
                        entities.add(object);
                        continue;
                    } else if (r.charAt(j) == '2') {
                        object = new Oneal(j, i, Sprite.oneal_left1.getFxImage());
                        entities.add(object);
                        continue;
                    } else if (r.charAt(j) == 'b') {
                        object = new BombItem(j, i, Sprite.powerup_bombs.getFxImage());
                        stillObjects.add(object);
                        object = new Brick(j, i, Sprite.brick.getFxImage());
                    } else if (r.charAt(j) == 'f') {
                        object = new FlameItem(j, i, Sprite.powerup_flames.getFxImage());
                        stillObjects.add(object);
                        object = new Brick(j, i, Sprite.brick.getFxImage());
                    } else if (r.charAt(j) == 's') {
                        object = new SpeedItem(j, i, Sprite.powerup_speed.getFxImage());
                        stillObjects.add(object);
                        object = new Brick(j, i, Sprite.brick.getFxImage());
                    }
                }
                stillObjects.add(object);
            }
        }
    }

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        //gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
