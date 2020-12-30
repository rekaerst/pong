package com.rekaerst.pong;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.rekaerst.pong.gameobj.GameObject;
import com.rekaerst.pong.gameobj.ID;
import com.rekaerst.pong.gameobj.Player;

public class KeyInput extends KeyAdapter {

    private World world;
    private Game game;

    public KeyInput(World handler, Game game) {
        this.world = handler;
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

        for (int i = 0; i < world.objects.size(); i++) {
            GameObject tempObject = world.objects.get(i);
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

        for (int i = 0; i < world.objects.size(); i++) {
            GameObject tempObject = world.objects.get(i);
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
