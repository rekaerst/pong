package com.rekaerst.pong;

import com.rekaerst.pong.gameObjects.ID;

public class Spawner {
    private World world;
    private HUD hud;
    private boolean isGameOver = false;
    private ID winner;

    private static int player1Points;
    private static int player2Points;

    public Spawner(World world, HUD hud) {
        this.world = world;
        this.hud = hud;

        player1Points = 0;
        player2Points = 0;
    }

    public void tick() {
        world.tick();
        hud.tick();

        if (player1Points > 11) {
            isGameOver = true;
            winner = ID.Player1;
        } else if (player2Points > 11) {
            isGameOver = true;
            winner = ID.Player2;
        }
    }

    public static int getPlayer1Points() {
        return player1Points;
    }

    public static void setPlayer1Points(int player1Points) {
        Spawner.player1Points = player1Points;
    }

    public static int getPlayer2Points() {
        return player2Points;
    }

    public static void setPlayer2Points(int player2Points) {
        Spawner.player2Points = player2Points;
    }

}
