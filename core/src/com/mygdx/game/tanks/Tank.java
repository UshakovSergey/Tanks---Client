package com.mygdx.game.tanks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.net.NetBullet;

import java.util.ArrayList;


public abstract class Tank {

    protected Vector2 Position;
    protected Vector2 Speed;
    protected boolean isAlive;
    protected Vector2 shotDirection;
    protected int rotateAngle;
    protected int tankTextureSize;
    protected float loadTime;
    protected static Vector2 nullVector = new Vector2(0, 0);
    protected static Texture boom = new Texture("Boom1.png");
    protected Texture tankTexture;
    protected float destroyDebounce;
    protected float enginePower;
    protected int tankId;

    protected enum ORIENTATION {NORTH, SOUTH, WEST, EAST}

    protected ORIENTATION Direction;
    protected ArrayList<NetBullet> netBulletsStore = new ArrayList<NetBullet>();

    public int getTankId() {
        return tankId;
    }

    public ArrayList<NetBullet> getNetBulletsStore() {
        return netBulletsStore;
    }

    public int getRotateAngle() {
        return rotateAngle;
    }

    public void setRotateAngle(int rotateAngle) {
        this.rotateAngle = rotateAngle;
    }

    public Vector2 getPosition() {
        return Position;
    }

    public void setPosition(Vector2 position) {
        Position = position;
    }

    protected Tank(Vector2 position, Vector2 speed, int tankId) {
        Position = position;
        Speed = speed;
        isAlive = true;
        this.destroyDebounce = 0;
        this.enginePower = 0.4f;
        this.tankId = tankId;
    }

    public abstract void draw(SpriteBatch batch);

    public void update() {

        Position.add(Speed);
        Speed.scl(0.8f);
        if (Position.x > Gdx.graphics.getWidth()) Position.x = -tankTextureSize;
        if (Position.x < -tankTextureSize) Position.x = Gdx.graphics.getWidth();
        if (Position.y > Gdx.graphics.getHeight()) Position.y = -tankTextureSize;
        if (Position.y < -tankTextureSize) Position.y = Gdx.graphics.getHeight();
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void destroy() {
        this.Speed = nullVector;
        this.tankTexture = boom;
        isAlive = false;
    }

    public void rotateTank(int ang) {
        rotateAngle += ang;
    }

}