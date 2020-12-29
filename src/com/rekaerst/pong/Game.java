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
import com.rekaerst.pong.menuobj.Menu;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 951988467320086772L;

    private static String OS = null;

    private Thread thread;
    private boolean running = false;

    private World world;
    private HUD hud;
    private Menu menu;
    private ScoreBoard scoreBoard;

    public static final int WIDTH = 1280, HEIGHT = WIDTH / 16 * 9;
    public static final int EDGE_HEIGHT = 20;
    public static final int SIDE_WIDTH = 20;

    public static final String TITLE = "JRocket Game";

    public enum STATE {
        Pause, Game, Menu
    }

    private STATE gameState = STATE.Menu;

    public static long test = 0;

    public static boolean isDebugging = false;

    public Game() {
        if (isLinux()) {
            System.setProperty("sun.java2d.opengl", "true");
        }
        isDebugging = true;
        world = new World();
        hud = new HUD();
        menu = new Menu("Menu", this);
        scoreBoard = new ScoreBoard();

        this.addKeyListener(new KeyInput(world));
        this.addMouseListener(menu);

        new Window(WIDTH, HEIGHT, TITLE, this);

        if (gameState == STATE.Game) {
            initialGame();
        }
    }

    public void initialGame() {
        GameObject player1 = new Player(WIDTH / 16, HEIGHT / 2, 10, new Color(255, 180, 180), ID.Player1, world);
        GameObject player2 = new Player(WIDTH / 16 * 15, HEIGHT / 2, 10, new Color(180, 180, 255), ID.Player2, world);
        GameObject net = new Net(Game.WIDTH / 2, 10, new Color(220, 220, 220), ID.Other, world);
        GameObject sideLeft = new Side(0, SIDE_WIDTH, ID.Player1Side, world);
        GameObject sideRight = new Side(WIDTH - SIDE_WIDTH, SIDE_WIDTH, ID.Player2Side, world);
        GameObject ball = new Ball(WIDTH / 2, HEIGHT / 2, 10, new Color(255, 255, 200), ID.Ball, world);
        GameObject edgeUp = new Edge(HEIGHT - 20, EDGE_HEIGHT, new Color(220, 220, 255), world);
        GameObject edgeDown = new Edge(0, EDGE_HEIGHT, new Color(220, 220, 255), world);

        world.addObject(sideLeft);
        world.addObject(sideRight);
        world.addObject(edgeUp);
        world.addObject(edgeDown);
        world.addObject(net);
        world.addObject(player1);
        world.addObject(player2);
        world.addObject(ball);

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
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

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
        world.tick();

        switch (gameState) {
            case Game:
                hud.tick();
                scoreBoard.tick();
                break;
            case Menu:
                menu.tick();
                break;
            default:
                break;
        }

        menu.tick();
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
        if (isLinux()) {
            Toolkit.getDefaultToolkit().sync();
        }

        world.render(g);

        switch (gameState) {
            case Game:
                hud.render(g);
                break;
            case Menu:
                menu.render(g);
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

    public void setGameState(STATE gameState) {
        this.gameState = gameState;
    }

}
