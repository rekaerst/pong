package com.rekaerst.pong.gameobj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;

import com.rekaerst.pong.Game;
import com.rekaerst.pong.Handler;

public class Net extends GameObject {
    public static final int HOLES = 18;
    public static final int HOLES_LENGTH = (Game.HEIGHT - 2 * Game.EDGE_HEIGHT) / (HOLES * 2 + 1);

    public Net(int x, int width, Color color, ID id, Handler handler) {
        super(x, 0, width, Game.HEIGHT, color, id, handler);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(x - width / 2, 0, width, height);
        g.setColor(Color.black);
        for (int i = 0; i < HOLES; i++) {
            g.fillRect(x - width / 2, Game.EDGE_HEIGHT + HOLES_LENGTH + i * HOLES_LENGTH * 2, width, HOLES_LENGTH);
        }

    }

    @Override
    public Shape getBounds() {
        return null;
    }

    @Override
    public boolean intersects(GameObject gameObject) {
        return false;
    }

}
