package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.model.CollisionObject;

import java.awt.*;

public class Box extends CollisionObject implements Movable {
    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fill3DRect(x, y,width,height, true);
    }

    @Override
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }
}
