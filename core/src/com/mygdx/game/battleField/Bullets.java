package com.mygdx.game.battleField;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.interfaces.DrawAble;
import com.mygdx.game.net.NetBullet;

import java.util.ArrayList;

public class Bullets implements DrawAble {

    private static Texture bulletTexture = new Texture("bullet.png");
    private static int bulletTextureSize = bulletTexture.getWidth();
    private static ArrayList<NetBullet> bulletsSet = new ArrayList<NetBullet>();

    public static void setBulletsSet(ArrayList<NetBullet> bulletsSet) {
        Bullets.bulletsSet = bulletsSet;
    }

    public void draw(SpriteBatch batch) {
        for (int i = 0; i < bulletsSet.size(); i++) {
            batch.draw(bulletTexture, bulletsSet.get(i).getPosition().x, bulletsSet.get(i).getPosition().y, bulletTextureSize / 2, bulletTextureSize / 2, bulletTextureSize, bulletTextureSize, 1, 1, 0, 0, 0, bulletTextureSize, bulletTextureSize, false, false);
        }
    }
}