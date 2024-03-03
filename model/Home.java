package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Home extends GameObject {
    public Home(int x, int y) {
        super(x, y);
        width = 2;
        height = 2;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.drawLine(x+4,y+4,x+16,y+16);
        graphics.drawLine(x+4,y+16,x+16,y+4);
        graphics.drawOval(x+4, y+4, 12,12);

    }
}
