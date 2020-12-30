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
package com.rekaerst.pong;

import java.awt.Canvas;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

/** Main window of game */
public class Window extends Canvas {

    /**
     *
     */
    private static final long serialVersionUID = 7397621225088225807L;

    public Window(int width, int height, String title, Game game) {
        JFrame frame = new JFrame(title);
        Container pane = frame.getContentPane();
        // set content pane of frame to width and height
        pane.setPreferredSize(new Dimension(width, height));
        pane.setMaximumSize(new Dimension(width, height));
        pane.setMinimumSize(new Dimension(width, height));
        frame.pack();
        // windows width is equal to content pane width
        // windows height is equal to content pane width plus height of window title bar
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        // add game Canvas
        frame.add(game);
        frame.setVisible(true);
        // start game thread
        game.start();
    }
}
