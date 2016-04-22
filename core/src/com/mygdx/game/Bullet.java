package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: US
 * Date: 08.04.16
 * Time: 23:21
 * To change this template use File | Settings | File Templates.
 */
public class Bullet {

    protected Texture nullBullet = new Texture("nullBullet.png");

    private Texture bulletTexture;
    private Vector2 bulletPosition;
    private Vector2 bulletSpeed;
    private boolean bulletOutOffScreen;
    private int bulletTextureSize;
    private boolean destroyed;

    public Bullet(Vector2 _bulletPosition, Vector2 _bulletSpeed) {
        bulletPosition = _bulletPosition;
        bulletSpeed = _bulletSpeed;
        bulletTexture = new Texture("bullet.png");
        bulletOutOffScreen = false;
        bulletTextureSize = bulletTexture.getWidth();
        destroyed = false;
    }

    public void destroy(){
        this.bulletTexture = nullBullet;
        this.destroyed = true;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public int getBulletTextureSize() {
        return bulletTextureSize;
    }

    public Texture getBulletTexture() {
        return bulletTexture;
    }

    public Vector2 getBulletPosition() {
        return bulletPosition;
    }

    public boolean isBulletOutOffScreen() {
        return bulletOutOffScreen;
    }

    public void update() {

        bulletPosition.add(bulletSpeed);
        if (bulletPosition.x > Gdx.graphics.getWidth()) bulletOutOffScreen = true;
        if (bulletPosition.x < -bulletTexture.getWidth()) bulletOutOffScreen = true;
        if (bulletPosition.y > Gdx.graphics.getHeight()) bulletOutOffScreen = true;
        if (bulletPosition.y < -bulletTexture.getWidth()) bulletOutOffScreen = true;
    }

}
