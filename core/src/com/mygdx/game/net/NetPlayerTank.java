package com.mygdx.game.net;

import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ushakov
 * Date: 24.05.16
 * Time: 16:03
 * To change this template use File | Settings | File Templates.
 */
public class NetPlayerTank implements Serializable {

    private Vector2 position;
    private int rotateAngle;
    private boolean isAlive;
    private int playerID;

    public NetPlayerTank(Vector2 position, int rotateAngle, int playerID) {
        this.position = position;
        this.rotateAngle = rotateAngle;
        this.playerID = playerID;
        this.isAlive = true;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public int getRotateAngle() {
        return rotateAngle;
    }

    public void setRotateAngle(int rotateAngle) {
        this.rotateAngle = rotateAngle;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }
}
