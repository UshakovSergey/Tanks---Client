package com.mygdx.game.tanks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.net.NetBullet;

public class PlayerTank extends Tank {

    private static float shotTime = 0;

    public PlayerTank(Vector2 position, Vector2 speed, int tankID) {
        super(position, speed, tankID);
        this.tankTexture = new Texture("RegularTank.tga");
        this.tankTextureSize = tankTexture.getWidth();
        this.Direction = ORIENTATION.NORTH;
        this.rotateAngle = 0;
        this.shotDirection = new Vector2(1, 0);
        this.loadTime = 0.4f;
    }


    public void draw(SpriteBatch batch) {
        batch.draw(tankTexture, Position.x, Position.y, tankTextureSize / 2, tankTextureSize / 2, tankTextureSize, tankTextureSize, 1, 1, rotateAngle, 0, 0, tankTextureSize, tankTextureSize, false, false);
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
        NetBullet bul = new NetBullet(Position.cpy().add(tankTextureSize/2, tankTextureSize/2), shotDirection.nor().scl(3));
        netBulletsStore.add(bul);
    }


}