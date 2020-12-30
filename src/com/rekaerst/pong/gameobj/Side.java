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
import java.awt.Rectangle;

import com.rekaerst.pong.Game;
import com.rekaerst.pong.Handler;

public class Side extends CollisonObject {

    public Side(int x, int width, ID id, Handler handler) {
        super(x, 0, width, Game.HEIGHT, null, id, handler);
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
