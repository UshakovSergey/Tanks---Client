package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: US
 * Date: 07.04.16
 * Time: 0:39
 * To change this template use File | Settings | File Templates.
 */
public class PlayerTank extends Tank {

    private static float shotTime = 0;

    public PlayerTank(Vector2 position, Vector2 speed) {
        super(position, speed);
        tankTexture = new Texture("TANK2.tga");
        tankTextureSize = tankTexture.getWidth();
        Direction = ORIENTATION.NORTH;
        rotateAngle = 0;
        shotDirection = new Vector2(1, 0);
        loadTime = 0.4f;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(tankTexture, Position.x, Position.y, tankTextureSize / 2, tankTextureSize / 2, tankTextureSize, tankTextureSize, 1, 1, rotateAngle, 0, 0, tankTextureSize, tankTextureSize, false, false);

        if (store.size() > 0) {
            for (int i = 0; i < store.size(); i++) {
                batch.draw(store.get(i).getBulletTexture(), store.get(i).getBulletPosition().x, store.get(i).getBulletPosition().y, store.get(i).getBulletTextureSize() / 2, store.get(i).getBulletTextureSize() / 2, store.get(i).getBulletTextureSize(), store.get(i).getBulletTextureSize(), 1, 1, rotateAngle, 0, 0, store.get(i).getBulletTextureSize(), store.get(i).getBulletTextureSize(), false, false);

            }
        }

    }

    @Override
    public void update() {

        super.update();
        shotTime = shotTime + Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            moveLeftTank();
            rotateAngle = 180;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            moveRightTank();
            rotateAngle = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            moveForwardTank();
            rotateAngle = 90;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            moveBackTank();
            rotateAngle = 270;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if (shotTime > loadTime) {
                tankShot();
                shotTime = 0;
            }
        }


    }
}