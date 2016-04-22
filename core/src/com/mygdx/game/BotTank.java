package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: US
 * Date: 15.04.16
 * Time: 1:59
 * To change this template use File | Settings | File Templates.
 */
public class BotTank extends Tank {

    protected static Random rn = new Random();

    public BotTank(Vector2 position, Vector2 speed) {
        super(position, speed);
        tankTexture = new Texture("TANK.tga");
        tankTextureSize = tankTexture.getWidth();
        Direction = ORIENTATION.NORTH;
        rotateAngle = 0;
        shotDirection = new Vector2(1, 0);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(tankTexture, Position.x, Position.y);
    }

    @Override
    public void update() {
        Position.add(Speed);
        if (Position.x > Gdx.graphics.getWidth()) Position.x = -tankTextureSize;
        if (Position.x < -tankTextureSize) Position.x = Gdx.graphics.getWidth();
        if (Position.y > Gdx.graphics.getHeight()) Position.y = -tankTextureSize;
        if (Position.y < -tankTextureSize) Position.y = Gdx.graphics.getHeight();

    }

}
