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
