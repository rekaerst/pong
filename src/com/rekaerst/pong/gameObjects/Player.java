package com.rekaerst.pong.gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.rekaerst.pong.Game;
import com.rekaerst.pong.ScoreBoard;
import com.rekaerst.pong.World;

public class Player extends CollisonObject {
    private int speed = 10;
    public static final double FORCE = 0.5;
    public static final double BOUNDS_FACTOR = 2.5;

    private double force = 0;

    public Player(int x, int y, int width, int height, Color color, ID id, World world) {
        super(x, y, width, height, color, id, world);
    }

    public Player(int x, int y, int width, Color color, ID id, World world) {
        super(x, y, width, width * 10, color, id, world);
    }

    @Override
    public void tick() {
        super.tick();

        if (velY < 0) {
            if (y > height / 2 + Game.EDGE_HEIGHT)
                y += velY;
        } else if (velY > 0) {
            if (y < Game.HEIGHT - height / 2 - Game.EDGE_HEIGHT)
                y += velY;
        }
        world.addObject(new Trail(x, y, width, height, 5, color, id, ID.Trail, world));
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
            drawDebugInfo(g,
                    "Points:"
                            + String.valueOf(
                                    id == ID.Player1 ? ScoreBoard.getPlayer1Points() : ScoreBoard.getPlayer2Points())
                            + "\n",
                    y + height / 2 + 40);
            drawDebugInfo(g, "Velocity:" + String.valueOf(velY), y + height / 2 + 60);
            drawDebugInfo(g, "Force:" + String.valueOf(force), y + height / 2 + 80);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (x - width * BOUNDS_FACTOR / 2), y - height / 2, (int) (width * BOUNDS_FACTOR),
                height);
    }

    public double getForce() {
        return force;
    }

    public void setForce(double force) {
        this.force = force;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

}
