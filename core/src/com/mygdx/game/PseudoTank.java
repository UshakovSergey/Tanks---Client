package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: US
 * Date: 24.04.16
 * Time: 22:57
 * To change this template use File | Settings | File Templates.
 */
public class PseudoTank implements Serializable {

    protected static Random rn = new Random();
    protected Vector2 Position;
    protected Vector2 Speed;
    private int tankTextureSize = 32;
    private float turnTime;
    private int angle;

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

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

    public void update() {
        float prevX = Position.x;
        float prevY = Position.y;

        Position.add(Speed);

        if (Position.x > Gdx.graphics.getWidth() - tankTextureSize) {
            Position.x -= Speed.x;
        }
        if (Position.x < 0) {
            Position.x -= Speed.x;
        }
        if (Position.y > Gdx.graphics.getHeight() - tankTextureSize) {
            Position.y -= Speed.y;
        }
        if (Position.y < 0) {
            Position.y -= Speed.y;
        }

        turnTime = turnTime + Gdx.graphics.getDeltaTime();
        if (turnTime > 0.5) {
            if (Math.abs(prevX - Position.x) < 0.001 && Math.abs(prevY - Position.y) < 0.001) {
                angle = 90 * rn.nextInt(3);
                Speed.rotate(angle);
            }
            turnTime = 0;
        }
    }
}
