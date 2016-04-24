package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: US
 * Date: 24.04.16
 * Time: 22:57
 * To change this template use File | Settings | File Templates.
 */
public class PseudoTank implements Serializable {

    private Vector2 Position;
    private Vector2 Speed;

    public PseudoTank(Vector2 position, Vector2 speed) {
        Position = position;
        Speed = speed;
    }

    public Vector2 getPosition() {
        return Position;
    }

    public void setPosition(Vector2 position) {
        Position = position;
    }

    public Vector2 getSpeed() {
        return Speed;
    }

    public void setSpeed(Vector2 speed) {
        Speed = speed;
    }
}
