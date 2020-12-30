package com.rekaerst.pong.gameobj;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import java.awt.Shape;

import com.rekaerst.pong.Handler;

/**
 * non collision implemention of GameObject that serve as graphical declaration
 * A trail flows the object created it and fade by time
 * 
 */
public class Trail extends GameObject {
    private ID parentId;

    private float alpha = 1F;

    private int life;

    /**
     * 
     * @param x        x coordinate (center)
     * @param y        y coordinate (center)
     * @param width    width of trail
     * @param height   height of trail
     * @param life     lenght of trail
     * @param color    color of trail
     * @param parentId parent/creator of trail
     * @param id       id
     * @param handler  handler
     */
    public Trail(int x, int y, int width, int height, int life, Color color, ID parentId, ID id, Handler handler) {
        super(x, y, width, height, color, id, handler);
        this.parentId = parentId;
        this.life = life;
    }

    @Override
    public void tick() {
        if (alpha > 1.0 / life) {
            alpha -= 1.0 / life - 0.001f;
        } else {
            handler.removeObject(this);
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
        g2d.setComposite(makeTranspaprent(1));
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
