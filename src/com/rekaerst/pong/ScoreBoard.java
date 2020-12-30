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
