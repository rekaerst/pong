package com.rekaerst.pong.gameobj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.rekaerst.pong.Handler;

public class CollisonObject extends GameObject {

    public CollisonObject(int x, int y, int width, int height, Color color, ID id, Handler handler) {
        super(x, y, width, height, color, id, handler);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    @Override
    public boolean intersects(GameObject gameObject) {
        return getBounds().intersects((Rectangle) gameObject.getBounds());
    }

}
