package com.rekaerst.pong.gameobj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.rekaerst.pong.Game;
import com.rekaerst.pong.World;

public class Side extends CollisonObject {

    public Side(int x, int width, ID id, World world) {
        super(x, 0, width, Game.HEIGHT, null, id, world);
    }

    @Override
    public void render(Graphics g) {
        if (Game.isDebugging) {
            g.setColor(Color.green);
            g.fillRect(x, 0, width, Game.HEIGHT);
            g.setColor(Color.cyan);
            if (id == ID.Player1Side) {
                g.drawString(id.toString(), x + 60, 50);
            } else if (id == ID.Player2Side) {
                g.drawString(id.toString(), x - 100, 50);
            }
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, 0, width, Game.HEIGHT);
    }

    public int getWidth() {
        return width;
    }

}
