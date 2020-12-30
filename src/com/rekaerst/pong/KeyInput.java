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

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.rekaerst.pong.gameobj.GameObject;
import com.rekaerst.pong.gameobj.ID;
import com.rekaerst.pong.gameobj.Player;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    private Game game;

    public KeyInput(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_ESCAPE) {
            if (game.getGameState() == Game.STATE.Game) {
                game.getMainMenu().getPlayButton().setText("Resume");
                game.setMenuVisiable(true);
                return;
            } else if (game.getGameState() == Game.STATE.End) {
                game.getMainMenu().getPlayButton().setText("Replay");
                game.setMenuVisiable(true);
            } else if (game.getGameState() == Game.STATE.Menu && !game.isFirstRun()) {
                game.setMenuVisiable(false);
            }
        }

        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);
            if (tempObject.getId() == ID.Player1) {
                Player player = (Player) tempObject;
                switch (key) {
                    case KeyEvent.VK_W:
                        player.setVelY(-player.getSpeed());
                        break;
                    case KeyEvent.VK_S:
                        player.setVelY(player.getSpeed());
                        break;
                    case KeyEvent.VK_D:
                        player.setForce(Player.FORCE);
                        break;
                    case KeyEvent.VK_A:
                        player.setForce(-Player.FORCE);
                        break;
                }
            }
            if (tempObject.getId() == ID.Player2) {
                Player player = (Player) tempObject;
                switch (key) {
                    case KeyEvent.VK_UP:
                        player.setVelY(-player.getSpeed());
                        break;
                    case KeyEvent.VK_DOWN:
                        player.setVelY(player.getSpeed());
                        break;
                    case KeyEvent.VK_LEFT:
                        player.setForce(Player.FORCE);
                        break;
                    case KeyEvent.VK_RIGHT:
                        player.setForce(-Player.FORCE);
                        break;
                }
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);
            if (tempObject.getId() == ID.Player1) {
                Player player = (Player) tempObject;
                switch (key) {
                    case KeyEvent.VK_W:
                    case KeyEvent.VK_S:
                        player.setVelY(0);
                        break;
                    case KeyEvent.VK_A:
                    case KeyEvent.VK_D:
                        player.setForce(0);
                        break;
                }
            }
            if (tempObject.getId() == ID.Player2) {
                Player player = (Player) tempObject;
                switch (key) {
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_DOWN:
                        player.setVelY(0);
                        break;
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_RIGHT:
                        player.setForce(0);
                        break;
                }
            }
        }
    }

}
