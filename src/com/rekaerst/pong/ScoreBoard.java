package com.rekaerst.pong;

import javax.lang.model.element.Element;

import com.rekaerst.pong.gameobj.ID;

public class ScoreBoard {

    private static int player1Points;
    private static int player2Points;
    private static ID winner;
    private static Game game;

    public ScoreBoard(Game game) {
        ScoreBoard.game = game;
        player1Points = 0;
        player2Points = 0;
        winner = null;
    }

    public void tick() {

    }

    public static int getPlayerPoints(ID playerId) {
        if (playerId == ID.Player1) {
            return ScoreBoard.player1Points;
        } else if (playerId == ID.Player2) {
            return ScoreBoard.player2Points;
        } else {
            return 0;
        }
    }

    public static void setPlayerPoints(ID playerId, int playerPoints) {
        if (playerId == ID.Player1) {
            ScoreBoard.player1Points = playerPoints;
        } else if (playerId == ID.Player2) {
            ScoreBoard.player2Points = playerPoints;
        }

        if (player1Points > 11) {
            winner = ID.Player1;
            game.endGame();
        } else if (player2Points > 11) {
            winner = ID.Player2;
            game.endGame();
        }
    }

    public static void clearScore() {
        ScoreBoard.player1Points = 0;
        ScoreBoard.player2Points = 0;
        winner = null;
    }

    public static ID getWinner() {
        return winner;
    }

}
