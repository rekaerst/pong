/*
 * MIT License
 *
 * Copyright (c) 2020 rekaerst
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.rekaerst.pong.gameobj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.rekaerst.pong.Game;
import com.rekaerst.pong.ScoreBoard;
import com.rekaerst.pong.Handler;

/**
 * implemention of Player, of which instances are capable of collide with
 * instances of Ball
 */
public class Player extends CollisonObject {
    private int speed = 10;
    public static final double FORCE = 0.5;
    // expand bounds byound graphics bounds of player as a workaround of human
    // illusion
    public static final double BOUNDS_FACTOR = 2.5;
    // player force that affects the speed of hitted ball
    private double force = 0;

    public Player(int x, int y, int width, int height, Color color, ID id, Handler handler) {
        super(x, y, width, height, color, id, handler);
    }

    public Player(int x, int y, int width, Color color, ID id, Handler handler) {
        super(x, y, width, width * 10, color, id, handler);
    }

    @Override
    public void tick() {
        super.tick();
        // player cannot move beyound bounds of table
        if (velY < 0) {
            if (y > height / 2 + Game.EDGE_HEIGHT)
                y += velY;
        } else if (velY > 0) {
            if (y < Game.HEIGHT - height / 2 - Game.EDGE_HEIGHT)
                y += velY;
        }
        handler.addObject(new Trail(x, y, width, height, 5, color, id, ID.Trail, handler));
    }

    @Override
    public void render(Graphics g) {
        super.render(g);

        g.setColor(color);
        g.fillRect(x - width / 2, y - height / 2, width, height);

        if (Game.isDebugging) {
            g.setColor(Color.green);
            Graphics2D g2d = (Graphics2D) g;
            g2d.draw(getBounds());
            g.setColor(new Color(140, 140, 255));
            drawDebugInfo(g, id.toString(), y + height / 2 + 20);
            drawDebugInfo(g, "Points:" + String.valueOf(
                    id == ID.Player1 ? ScoreBoard.getPlayerPoints(ID.Player1) : ScoreBoard.getPlayerPoints(ID.Player2))
                    + "\n", y + height / 2 + 40);
            drawDebugInfo(g, "Velocity:" + String.valueOf(velY), y + height / 2 + 60);
            drawDebugInfo(g, "Force:" + String.valueOf(force), y + height / 2 + 80);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (x - width * BOUNDS_FACTOR / 2), y - height / 2, (int) (width * BOUNDS_FACTOR),
                height);
    }

    /**
     * get force of player
     * 
     * @return force of player
     */
    public double getForce() {
        return force;
    }

    /**
     * set force of player
     * 
     * @param force new force of player
     */
    public void setForce(double force) {
        this.force = force;
    }

    /**
     * get speed of player
     * 
     * @return speed of player
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * set speed of player
     * 
     * @param speed new speed of player
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

}
