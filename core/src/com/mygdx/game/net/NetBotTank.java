package com.mygdx.game.net;

import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;


public class NetBotTank implements Serializable {

    private Vector2 position;
    private Vector2 speed;
    private int angle;
    private boolean isAlive;
    private float turnTime;
    private float destroyDebounce;

    public NetBotTank(Vector2 position, Vector2 speed, int angle, float turnTime) {
        this.position = position;
        this.speed = speed;
        this.angle = angle;
        isAlive = true;
        this.turnTime = 0;
        this.destroyDebounce = 0;
    }

    public float getDestroyDebounce() {
        return destroyDebounce;
    }

    public void setDestroyDebounce(float destroyDebounce) {
        this.destroyDebounce = destroyDebounce;
    }

    public float getTurnTime() {
        return turnTime;
    }

    public void setTurnTime(float turnTime) {
        this.turnTime = turnTime;
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

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
