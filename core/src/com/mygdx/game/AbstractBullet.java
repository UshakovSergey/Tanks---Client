package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: US
 * Date: 23.04.16
 * Time: 22:28
 * To change this template use File | Settings | File Templates.
 */
public class AbstractBullet implements Serializable {

    protected Vector2 bulletPosition;
    protected Vector2 bulletSpeed;

    public AbstractBullet(Vector2 bulletPosition, Vector2 bulletSpeed) {
        this.bulletPosition = bulletPosition;
        this.bulletSpeed = bulletSpeed;
    }

    public Vector2 getBulletPosition() {
        return bulletPosition;
    }

    public void setBulletPosition(Vector2 bulletPosition) {
        this.bulletPosition = bulletPosition;
    }

    public Vector2 getBulletSpeed() {
        return bulletSpeed;
    }

    public void setBulletSpeed(Vector2 bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }
}
