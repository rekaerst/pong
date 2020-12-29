package com.rekaerst.pong.gameobj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.rekaerst.pong.Game;
import com.rekaerst.pong.World;

public class Edge extends CollisonObject {

    public Edge(int y, int height, Color color, World world) {
        super(0, y, Game.WIDTH, height, color, ID.Edge, world);
        this.height = height;
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);

        g.setColor(color);
        g.fillRect(0, y, width, height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(0, y, width, height);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
