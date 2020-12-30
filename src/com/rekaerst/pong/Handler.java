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

import java.awt.Graphics;
import java.util.LinkedList;

import com.rekaerst.pong.gameobj.GameObject;
import com.rekaerst.pong.gameobj.ID;

/**
 * Implement of simple game object handler, implement tick and render methods.
 * instances of GameObject that was to be handled by handler was added to a
 * linked list LinkedList<GameObject> objects
 */
public class Handler { // update all of objects
    public LinkedList<GameObject> objects = new LinkedList<GameObject>();

    /** call tick method of each object in LinkedList<GameObject> objects */
    public void tick() {
        for (int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);
            tempObject.tick();
        }
    }

    /**
     * call render method of each object that managed by handler
     * 
     * @param g graphics contexts to render with
     */
    public void render(Graphics g) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);
            tempObject.render(g);
        }
    }

    /**
     * get reference of first appearence of a GameObject that has id of paprameter
     * id
     * 
     * @param id id of GameObject to get
     * @return first GameObject on handler.objects that id == parameter id
     */
    public GameObject getObject(ID id) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);
            if (tempObject.getId() == id) {
                return tempObject;
            }
        }
        return null;
    }

    /**
     * append a specific GameObject to handeler
     * 
     * @param object GameObject ot add
     */
    public void addObject(GameObject object) {
        this.objects.add(object);
    }

    /**
     * removed a specific GameObject from handler
     * 
     * @param object GameObject to remove
     */
    public void removeObject(GameObject object) {
        this.objects.remove(object);
    }

    /**
     * discard original LinkedList<GameObject> objects and create a new one
     * effectivelt equal to remove all instances of GameObject from handler
     */
    public void removeAll() {
        objects = new LinkedList<GameObject>();
    }
}
