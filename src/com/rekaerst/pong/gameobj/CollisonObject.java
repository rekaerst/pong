package com.rekaerst.pong.gameobj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.rekaerst.pong.World;

public class CollisonObject extends GameObject {

    public CollisonObject(int x, int y, int width, int height, Color color, ID id, World world) {
        super(x, y, width, height, color, id, world);
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
