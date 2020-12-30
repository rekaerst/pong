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

/** KeyInput handle keybinding in game */
public class KeyInput extends KeyAdapter {

    private Handler handler;
    private Game game;

    public KeyInput(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
    }

    /**
     * invoked when a key has been ressed
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        // ESCAPE key mapped to "back to menu" or "pause the game"
        if (key == KeyEvent.VK_ESCAPE) {

            if (game.getGameState() == Game.STATE.Game) { // if game is not over, pause the game and shows resume
                game.getMainMenu().getPlayButton().setText("Resume");
                game.setMenuVisiable(true);
                return;
            } else if (game.getGameState() == Game.STATE.End) { // if game ends, back to main menu and shows replay
                game.getMainMenu().getPlayButton().setText("Replay");
                game.setMenuVisiable(true);
            } else if (game.getGameState() == Game.STATE.Menu && !game.isFirstRun()) { // press ESCAPE again to resume a
                                                                                       // game
                game.setMenuVisiable(false);
            }
        }

        // player keygindings
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);
            // player1
            if (tempObject.getId() == ID.Player1) {
                Player player = (Player) tempObject;
                switch (key) {
                    case KeyEvent.VK_W: // go up
                        player.setVelY(-player.getSpeed());
                        break;
                    case KeyEvent.VK_S: // go down
                        player.setVelY(player.getSpeed());
                        break;
                    case KeyEvent.VK_D: // positive force: incress the speed of the ball
                        player.setForce(Player.FORCE);
                        break;
                    case KeyEvent.VK_A: // nagitive force; decrese the speed of the ball
                        player.setForce(-Player.FORCE);
                        break;
                }
            }
            // player2
            if (tempObject.getId() == ID.Player2) {
                Player player = (Player) tempObject;
                switch (key) {
                    case KeyEvent.VK_UP: // go up
                        player.setVelY(-player.getSpeed());
                        break;
                    case KeyEvent.VK_DOWN: // go down
                        player.setVelY(player.getSpeed());
                        break;
                    case KeyEvent.VK_LEFT: // positive force: incress the speed of the ball
                        player.setForce(Player.FORCE);
                        break;
                    case KeyEvent.VK_RIGHT: // nagitive force; decrese the speed of the ball
                        player.setForce(-Player.FORCE);
                        break;
                }
            }
        }
    }

    /**
     * Invoked when a key has been released
     */
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        // set player speed to zero when key released
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
