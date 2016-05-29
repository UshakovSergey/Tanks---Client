package com.mygdx.game.net;

import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: US
 * Date: 21.05.16
 * Time: 13:45
 * To change this template use File | Settings | File Templates.
 */
public class NetBullet implements Serializable {

    private Vector2 position;
    private Vector2 speed;
    private boolean bulletOutOffScreen;

    public NetBullet(Vector2 position, Vector2 speed) {
        this.position = position;
        this.speed = speed;
        this.bulletOutOffScreen = false;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getSpeed() {
        return speed;
    }

    public void setSpeed(Vector2 speed) {
        this.speed = speed;
    }

    public boolean isBulletOutOffScreen() {
        return bulletOutOffScreen;
    }

    public void setBulletOutOffScreen(boolean bulletOutOffScreen) {
        this.bulletOutOffScreen = bulletOutOffScreen;
    }
}
