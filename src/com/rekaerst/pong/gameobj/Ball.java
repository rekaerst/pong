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
import java.util.Random;

import com.rekaerst.pong.Game;
import com.rekaerst.pong.ScoreBoard;
import com.rekaerst.pong.Handler;

public class Ball extends CollisonObject {
    public static final double BOUNDS_FACTOR = 2;
    private int radius;
    private Random r;

    public Ball(int x, int y, int radius, Color color, ID id, Handler handler) {
        super(x, y, radius * 2, radius * 2, color, id, handler);
        this.radius = radius;
        r = new Random();
        velY = 0;
        velX = 10;
    }

    @Override
    public void tick() {
        super.tick();

        x += velX;
        y += velY;
        // collision detection
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);
            if (!tempObject.equals(this) && tempObject.id != ID.Other && tempObject.intersects(this)) {
                switch (tempObject.id) {
                    case Edge:
                        velY *= -1;
                        break;
                    case Player1:
                    case Player2:
                        Player player = (Player) tempObject;
                        velX = (int) (-Math.signum(velX)
                                * Game.clip(10, 30, Math.abs((int) ((1 + player.getForce()) * velX))));
                        if (velY == 0) {
                            velY += r.nextInt(Game.clip(3, 10, player.velY));
                        } else {
                            velY += Game.clip(0, 30, (int) (player.velY * 0.5 + r.nextDouble())
                                    + r.nextInt(Game.clip(3, 30, player.velY * r.nextInt(3))));
                        }
                        break;
                    case Player2Side:
                        ScoreBoard.setPlayerPoints(ID.Player1, ScoreBoard.getPlayerPoints(ID.Player1) + 1);
                        respawn();
                        break;
                    case Player1Side:
                        ScoreBoard.setPlayerPoints(ID.Player2, ScoreBoard.getPlayerPoints(ID.Player2) + 1);
                        respawn();
                        break;
                    default:
                        break;
                }
            }
        }
        handler.addObject(new Trail(x, y, width, height, 25, color, id, ID.Trail, handler));
    }

    @Override
    public void render(Graphics g) {
        super.render(g);

        g.setColor(color);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);

        if (Game.isDebugging) {
            g.setColor(Color.green);
            Graphics2D g2d = (Graphics2D) g;
            g2d.draw(getBounds());
            g.setColor(Color.cyan);
            g.drawString("Ball Speed: " + (int) Math.sqrt(velX * velX + velY * velY), Game.WIDTH / 2 - 150,
                    Game.HEIGHT - 30);
        }

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (x - radius * BOUNDS_FACTOR), (int) (y - radius * BOUNDS_FACTOR),
                (int) (radius * 2 * BOUNDS_FACTOR), (int) (radius * 2 * BOUNDS_FACTOR));
    }

    private void respawn() {
        x = Game.WIDTH / 2 - radius;
        y = Game.HEIGHT / 2 - radius;
        velX = (int) (Math.signum(velX)) * 10;
        velY = (r.nextBoolean() ? 1 : -1) * r.nextInt(5);
    }

}
