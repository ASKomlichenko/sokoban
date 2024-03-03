package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.nio.file.Paths;
import java.util.Set;

public class Model {
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(Paths.get("C:\\Users\\Nicho\\javarush\\2397394\\javarush-project\\src\\com\\javarush\\task\\task34\\task3410\\res/levels.txt"));
    private EventListener eventListener;
    public static final int FIELD_CELL_SIZE = 20;

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (Wall wall : gameObjects.getWalls()) {
            if (gameObject.isCollision(wall, direction))
                return true;
        }
        return false;
    }

    public boolean checkBoxCollisionAndMoveIfAvailable(Direction direction) {
        for (Box box : gameObjects.getBoxes()) {
            if (gameObjects.getPlayer().isCollision(box, direction)) {
                for (Box boxBehind : gameObjects.getBoxes()) {
                    if (box.isCollision(boxBehind, direction))
                        return true;
                }
                if (checkWallCollision(box, direction))
                    return true;
                int dirX = FIELD_CELL_SIZE * (direction == Direction.LEFT ? -1 : direction == Direction.RIGHT ? 1 : 0);
                int dirY = FIELD_CELL_SIZE * (direction == Direction.UP ? -1 : direction == Direction.DOWN ? 1 : 0);
                box.move(dirX, dirY);
            }
        }
        return false;
    }

    public void checkCompletion() {
        int counter = 0;
        int result = gameObjects.getHomes().size();
        for (Box box : gameObjects.getBoxes()) {
            for (Home home : getGameObjects().getHomes()) {
                if (box.getX() == home.getX() && box.getY() == home.getY()) {
                    counter++;
                    break;
                }
            }
        }
        if (counter == result)
            eventListener.levelCompleted(currentLevel);
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        restartLevel(++currentLevel);
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(player, direction))
            return;
        if (checkBoxCollisionAndMoveIfAvailable(direction))
            return;

        int dirX = FIELD_CELL_SIZE * (direction == Direction.LEFT ? -1 : direction == Direction.RIGHT ? 1 : 0);
        int dirY = FIELD_CELL_SIZE * (direction == Direction.UP ? -1 : direction == Direction.DOWN ? 1 : 0);
        player.move(dirX, dirY);
        checkCompletion();
    }
}
