package com.javarush.task.task34.task3410.model;

public abstract class CollisionObject extends GameObject {
    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction) {
        switch (direction) {
            case DOWN:
                return y + Model.FIELD_CELL_SIZE == gameObject.getY() && x == gameObject.getX();
            case UP:
                return y - Model.FIELD_CELL_SIZE == gameObject.getY() && x == gameObject.getX();
            case LEFT:
                return x - Model.FIELD_CELL_SIZE == gameObject.getX() && y == gameObject.getY();
            case RIGHT:
                return x + Model.FIELD_CELL_SIZE == gameObject.getX() && y == gameObject.getY();
        }
        return false;
    }
}
