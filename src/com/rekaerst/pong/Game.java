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
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import com.rekaerst.pong.gameobj.Ball;
import com.rekaerst.pong.gameobj.Edge;
import com.rekaerst.pong.gameobj.GameObject;
import com.rekaerst.pong.gameobj.ID;
import com.rekaerst.pong.gameobj.Net;
import com.rekaerst.pong.gameobj.Player;
import com.rekaerst.pong.gameobj.Side;
import com.rekaerst.pong.menu.MainMenu;
import com.rekaerst.pong.menu.HelpMenu;
import com.rekaerst.pong.menu.Menu;
import com.rekaerst.pong.menu.MenuHandler;

/** Updates and handles state of current game. Main class */
public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 951988467320086772L;
    /** OS identifier */
    private static String OS = null;

    private Thread thread;
    /** Whether the thread is running */
    private boolean running = false;

    private Menu mainMenu;
    private Menu helpMenu;
    private MenuHandler mainMenuHandler;
    private MenuHandler helpMenuHandler;

    private Handler Handler;
    private HUD hud;
    private ScoreBoard scoreBoard;

    private boolean isFirstRun = true;

    /** Game Windows size */
    public static final int WIDTH = 1280, HEIGHT = WIDTH / 16 * 9;
    /** Game Collision Aera size */
    public static final int EDGE_HEIGHT = 20;
    /** Game Score Aera size */
    public static final int SIDE_WIDTH = 20;

    /** main Application name */
    public static final String TITLE = "Pong";

    /** Possible states of game */
    public enum STATE {
        Game, Menu, Help, End
    }

    /** Current state of instance of game */
    private STATE gameState = STATE.Menu;

    /**
     * Debugin infomation display flag, show collision bounds and atributes of game
     * objects when true
     */
    public static boolean isDebugging = false;

    public Game() {
        // Hardware acclertaion is not enabled on linux by defualt
        if (isLinux()) {
            System.setProperty("sun.java2d.opengl", "true");
        }

        Handler = new Handler();
        hud = new HUD();

        mainMenuHandler = new MenuHandler();
        helpMenuHandler = new MenuHandler();

        mainMenu = new MainMenu("Menu", this, mainMenuHandler);
        helpMenu = new HelpMenu("Help", this, helpMenuHandler);
        scoreBoard = new ScoreBoard(this);

        this.addKeyListener(new KeyInput(Handler, this));
        this.addMouseListener(mainMenuHandler);

        new Window(WIDTH, HEIGHT, TITLE, this);
    }

    // Initial new game or start a new game
    private void initialGame() {

        // remove mouse listeners and thus button press detection
        removeMouseListener(mainMenuHandler);
        // remove preiouse game objects
        Handler.removeAll();
        // clear Score system
        ScoreBoard.clearScore();

        // reverse x velocity of ball when collide with ball
        GameObject player1 = new Player(WIDTH / 16, HEIGHT / 2, 10, new Color(255, 180, 180), ID.Player1, Handler);
        GameObject player2 = new Player(WIDTH / 16 * 15, HEIGHT / 2, 10, new Color(180, 180, 255), ID.Player2, Handler);
        // middle net of table
        GameObject net = new Net(Game.WIDTH / 2, 10, new Color(220, 220, 220), ID.Other, Handler);
        // Scroe area
        GameObject sideLeft = new Side(0, SIDE_WIDTH, ID.Player1Side, Handler);
        GameObject sideRight = new Side(WIDTH - SIDE_WIDTH, SIDE_WIDTH, ID.Player2Side, Handler);
        // pong ball with collision detection
        GameObject ball = new Ball(WIDTH / 2, HEIGHT / 2, 10, new Color(255, 255, 200), ID.Ball, Handler);
        // Objects for collision detection, reverse y velocity of ball when collide with
        // ball
        GameObject edgeUp = new Edge(HEIGHT - 20, EDGE_HEIGHT, new Color(220, 220, 255), Handler);
        GameObject edgeDown = new Edge(0, EDGE_HEIGHT, new Color(220, 220, 255), Handler);

        Handler.addObject(sideLeft);
        Handler.addObject(sideRight);
        Handler.addObject(edgeUp);
        Handler.addObject(edgeDown);
        Handler.addObject(net);
        Handler.addObject(player1);
        Handler.addObject(player2);
        Handler.addObject(ball);

    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join(); // kill the thread
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        /** amount of ticks per second */
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        // request forucs from OS after game started running
        this.requestFocus();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
            }
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {

        switch (gameState) {
            case Game:
                hud.tick();
                Handler.tick();
                scoreBoard.tick();
                break;
            case Menu:
                break;
            default:
                break;
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        if (isLinux()) { // work around with java graphics on linux
            Toolkit.getDefaultToolkit().sync();
        }

        switch (gameState) {
            case Game:
                // render all instances of GameObject
                Handler.render(g);
                // render hud on top of screen
                hud.render(g);
                break;
            case Menu:
                mainMenu.render(g);
                break;
            case Help:
                helpMenu.render(g);
                break;
            case End:
                Handler.render(g);
                hud.render(g);
                break;
            default:
                break;
        }

        g.dispose();
        bs.show();
    }

    public static String getOSName() {
        if (OS == null) {
            OS = System.getProperty("os.name");
        }
        return OS;
    }

    public static boolean isWindows() {
        return getOSName().startsWith("Windows");
    }

    public static boolean isLinux() {
        return getOSName().startsWith("Linux");
    }

    public static int clip(int min, int max, int number) {
        if (number < min) {
            return min;
        } else if (number > max) {
            return max;
        } else {
            return number;
        }
    }

    public static void main(String[] args) {

        new Game();
    }

    public STATE getGameState() {
        return gameState;
    }

    public void setHelpVisiable(boolean isVisiable) {
        if (isVisiable) {
            gameState = STATE.Help;
            removeMouseListener(mainMenuHandler);
            addMouseListener(helpMenuHandler);
        } else {
            gameState = STATE.Menu;
            removeMouseListener(helpMenuHandler);
            addMouseListener(mainMenuHandler);
        }
    }

    public void setMenuVisiable(boolean isVisiable) {
        if (isVisiable) {
            gameState = STATE.Menu;
            addMouseListener(mainMenuHandler);
        } else {
            gameState = STATE.Game;
            removeMouseListener(mainMenuHandler);
        }
    }

    public void startGame() {
        if (isFirstRun) {
            initialGame();
            isFirstRun = false;
        }
    }

    public void endGame() {
        gameState = STATE.End;
        isFirstRun = true;
    }

    public MainMenu getMainMenu() {
        return (MainMenu) mainMenu;
    }

    public boolean isFirstRun() {
        return isFirstRun;
    }

}
