package com.rekaerst.pong.gameobj;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

import com.rekaerst.pong.World;

public class Trail extends GameObject {
    private ID parentId;

    private float alpha = 1F;

    private int life;

    public Trail(int x, int y, int width, int height, int life, Color color, ID parentId, ID id, World world) {
        super(x, y, width, height, color, id, world);
        this.parentId = parentId;
        this.life = life;
    }

    @Override
    public void tick() {
        if (alpha > 1.0 / life) {
            alpha -= 1.0 / life - 0.001f;
        } else {
            world.removeObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTranspaprent(alpha));
        g.setColor(color);
        switch (parentId) {
            case Ball:
                g.fillOval(x - width / 2, y - height / 2, width, height);
                break;
            case Player1:
            case Player2:
                g.fillRect(x - width / 2, y - height / 2, width, height);
                break;
            default:
                break;
        }
    }

    private AlphaComposite makeTranspaprent(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));
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
