package com.rekaerst.pong;

import java.awt.Graphics;
import java.util.LinkedList;

import com.rekaerst.pong.gameobj.GameObject;
import com.rekaerst.pong.gameobj.ID;

public class Handler { // update all of objects
    public LinkedList<GameObject> objects = new LinkedList<GameObject>();

    public void tick() {
        for (int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);
            tempObject.tick();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);
            tempObject.render(g);
        }
    }

    public GameObject getObject(ID id) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);
            if (tempObject.getId() == id) {
                return tempObject;
            }
        }
        return null;
    }

    public void addObject(GameObject object) {
        this.objects.add(object);
    }

    public void removeObject(GameObject object) {
        this.objects.remove(object);
    }
}
