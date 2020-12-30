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
import java.awt.Shape;

import com.rekaerst.pong.Handler;

/** abstract class for all non UI objects appears in game */
public abstract class GameObject {

    protected int x, y;
    protected int velX, velY;
    protected int width, height;
    protected ID id;
    protected Handler handler;
    protected Color color = Color.white;

    /**
     * 
     * @param x       x coordinate (center)
     * @param y       y coordinate (center)
     * @param width   object width
     * @param height  object height
     * @param color   object color
     * @param id      objecgt id
     * @param handler handler of object
     */
    public GameObject(int x, int y, int width, int height, Color color, ID id, Handler handler) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;
        this.handler = handler;
        this.color = color;
    }

    /** handles game logic */
    public abstract void tick();

    /**
     * handles rendering
     * 
     * @param g graphics context to render with
     */
    public abstract void render(Graphics g);

    /**
     * get collision bounds of object
     * 
     * @return a Shape that indicates collision bounds of object
     */
    public abstract Shape getBounds();

    /**
     * test if instance of GameObject intersects/collide to other instance of
     * GameObjec
     * 
     * @param gameObject other instance of GameObject to test with
     * @return true when two object collide into each other, false in other
     *         condition
     */
    public abstract boolean intersects(GameObject gameObject);

    /**
     * display debuge information around an instance of GameObject
     * 
     * @param g      graphics context
     * @param str    information to display
     * @param offset information vertical offset to object
     */
    protected void drawDebugInfo(Graphics g, String str, int offset) {
        g.drawString(str, x - g.getFontMetrics().stringWidth(str) / 2, offset);
    }

    // getters and setters

    /**
     * get x coordinate of object
     * 
     * @return x coordinate of object
     */
    public int getX() {
        return x;
    }

    /**
     * set x coordinate of object
     * 
     * @param x new x coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * get y coordinate of object
     * 
     * @return y coordinate of object
     */
    public int getY() {
        return y;
    }

    /**
     * set y coordinate of object
     * 
     * @param y new y coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * get id of object
     * 
     * @return id of object
     */
    public ID getId() {
        return id;
    }

    /**
     * set id of object
     * 
     * @param id new id of object
     */
    public void setId(ID id) {
        this.id = id;
    }

    /**
     * get velocity at x coordinate of object
     * 
     * @return x coordinate velocity of object
     */
    public int getVelX() {
        return velX;
    }

    /**
     * sete x coordinate velocity of object
     * 
     * @param velX new x coordinate velocity of object
     */
    public void setVelX(int velX) {
        this.velX = velX;
    }

    /**
     * get velocity at y coordinate of object
     * 
     * @return y coordinate velocity of object
     */
    public int getVelY() {
        return velY;
    }

    /**
     * sete y coordinate velocity of object
     * 
     * @param velY new y coordinate velocity of object
     */
    public void setVelY(int velY) {
        this.velY = velY;
    }
}
