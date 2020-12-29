package com.rekaerst.pong;

import com.rekaerst.pong.gameObjects.ID;

public class ScoreBoard {

    private static int player1Points;
    private static int player2Points;
    private static ID winner;

    public ScoreBoard() {

        player1Points = 0;
        player2Points = 0;
    }

    public void tick() {
        if (player1Points > 11) {
            winner = ID.Player1;
        } else if (player2Points > 11) {
            winner = ID.Player2;
        }
    }

    public static int getPlayer1Points() {
        return player1Points;
    }

    public static void setPlayer1Points(int player1Points) {
        ScoreBoard.player1Points = player1Points;
    }

    public static int getPlayer2Points() {
        return player2Points;
    }

    public static void setPlayer2Points(int player2Points) {
        ScoreBoard.player2Points = player2Points;
    }

    public static ID getWinner() {
        return winner;
    }

}
