package com.rekaerst.pong.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import com.rekaerst.pong.Game;

public class Menu extends MouseAdapter {
    Font font;
    // private MenuButton playButton;
    // private MenuButton helpButton;
    // private MenuButton exitButton;
    private int mx;
    private int my;
    private Game game;
    private String text;
    public LinkedList<MenuButton> buttons = new LinkedList<MenuButton>();

    public void addButton(MenuButton button) {
        this.buttons.add(button);
    }

    public void removeButton(MenuButton button) {
        this.buttons.remove(button);
    }

    public Menu(String text, Game game) {
        this.text = text;
        this.game = game;
        loadFont();

        new MenuButton(Game.WIDTH / 2, Game.HEIGHT / 5 * 2, 300, 100, "Play", this) {
            @Override
            public void act() {
                game.setGameState(Game.STATE.Game);
                game.initialGame();
            }
        };
        new MenuButton(Game.WIDTH / 2, Game.HEIGHT / 5 * 3, 300, 100, "Help", this) {
            @Override
            public void act() {
                parentMenu.setText("Help");
            }
        };
        new MenuButton(Game.WIDTH / 2, Game.HEIGHT / 5 * 4, 300, 100, "Exit", this) {
            @Override
            public void act() {
                System.exit(0);
            }
        };

    }

    private void loadFont() {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/com/rekaerst/pong/resources/3270-Regular.otf"));
            font = font.deriveFont(Font.BOLD, 50f);
            ge.registerFont(font);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            if (Game.isWindows()) {
                System.err.println("can't find 3270 Font. Fallback to arial");
                font = new Font("arial", Font.BOLD, 50);
            } else if (Game.isLinux()) {
                System.err.println("can't find 3270 Font. Fallback to DejaVu Sans");
                font = new Font("DejaVu Sans", Font.BOLD, 50);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);

        mx = e.getX();
        my = e.getY();

        for (int i = 0; i < buttons.size(); i++) {
            MenuButton tempButton = buttons.get(i);
            if (tempButton.isClicked()) {
                tempButton.act();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
    }

    public void tick() {

    }

    public void render(Graphics g) {

        g.setFont(font);
        g.setColor(Color.white);
        g.drawString(text, Game.WIDTH / 2 - g.getFontMetrics().stringWidth("Menu") / 2, 100);

        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).render(g);
        }
    }

    public int getMx() {
        return mx;
    }

    public int getMy() {
        return my;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
