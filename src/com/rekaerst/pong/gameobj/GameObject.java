package com.rekaerst.pong.gameobj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;

import com.rekaerst.pong.Handler;

public abstract class GameObject {

    protected int x, y;
    protected int velX, velY;
    protected int width, height;
    protected ID id;
    protected Handler handler;
    protected Color color = Color.white;

    public GameObject(int x, int y, int width, int height, Color color, ID id, Handler handler) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;
        this.handler = handler;
        this.color = color;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract Shape getBounds();

    public abstract boolean intersects(GameObject gameObject);

    protected void drawDebugInfo(Graphics g, String str, int offset) {
        g.drawString(str, x - g.getFontMetrics().stringWidth(str) / 2, offset);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }
}
