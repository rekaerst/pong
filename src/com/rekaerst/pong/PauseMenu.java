package com.rekaerst.pong;

import java.awt.Color;
import java.awt.Graphics;

public class PauseMenu {
    public void tick() {

    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.drawString("Menu", Game.WIDTH / 2, Game.HEIGHT / 2);
    }
}
