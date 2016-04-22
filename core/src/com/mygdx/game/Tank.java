package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: US
 * Date: 06.04.16
 * Time: 23:04
 * To change this template use File | Settings | File Templates.
 */
public abstract class Tank {

    private boolean isAlive;
    protected Vector2 Position;
    protected Vector2 Speed;
    protected Vector2 shotDirection;
    protected float rotateAngle;
    protected int tankTextureSize;
    protected float loadTime;
    protected static Vector2 nullVector = new Vector2(0, 0);
    protected Texture boom = new Texture("Boom1.png");
    protected Texture tankTexture;
    protected float destroyDebounce;
    protected float enginePower;

    enum ORIENTATION {NORTH, SOUTH, WEST, EAST}

    protected ORIENTATION Direction;
    protected ArrayList<Bullet> store = new ArrayList<Bullet>();

    public Vector2 getPosition() {
        return Position;
    }

    public void setPosition(Vector2 position) {
        Position = position;
    }

    public ORIENTATION getDirection() {
        return Direction;
    }

    public void setDirection(ORIENTATION direction) {
        Direction = direction;
    }

    public Tank(Vector2 position, Vector2 speed) {
        isAlive = true;
        Position = position;
        Speed = speed;
        destroyDebounce = 0;
        enginePower = 0.4f;

    }

    public abstract void draw(SpriteBatch batch);

    public void update() {

        Position.add(Speed);
        Speed.scl(0.8f);
        if (Position.x > Gdx.graphics.getWidth()) Position.x = -tankTextureSize;
        if (Position.x < -tankTextureSize) Position.x = Gdx.graphics.getWidth();
        if (Position.y > Gdx.graphics.getHeight()) Position.y = -tankTextureSize;
        if (Position.y < -tankTextureSize) Position.y = Gdx.graphics.getHeight();

        if (store.size() > 0) {
            for (int i = 0; i < store.size(); i++) {
                if (store.get(i).isBulletOutOffScreen()) store.remove(i);
            }
        }

        if (store.size() > 0) {
            for (int i = 0; i < store.size(); i++) {
                store.get(i).update();
            }
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void destroy() {
        this.Speed = nullVector;
        this.tankTexture = boom;
        isAlive = false;

    }

    public void rotateTank(float ang) {

        rotateAngle += ang;
    }

    public void moveForwardTank() {
        Vector2 move = new Vector2(0.0f, enginePower);
        Speed.add(move);
        shotDirection = Speed.cpy();
    }

    public void moveBackTank() {
        Vector2 move = new Vector2(0.0f, -enginePower);
        Speed.add(move);
        shotDirection = Speed.cpy();
    }

    public void moveLeftTank() {
        Vector2 move = new Vector2(-enginePower, 0.0f);
        Speed.add(move);
        shotDirection = Speed.cpy();
    }

    public void moveRightTank() {
        Vector2 move = new Vector2(enginePower, 0.0f);
        Speed.add(move);
        shotDirection = Speed.cpy();
    }

    public void tankShot() {

        Bullet bul = new Bullet(Position.cpy().add(tankTextureSize/2, tankTextureSize/2), shotDirection.nor().scl(10));

        store.add(bul);
    }
}