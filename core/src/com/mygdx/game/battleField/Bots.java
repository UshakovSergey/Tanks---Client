package com.mygdx.game.battleField;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.interfaces.DrawAble;
import com.mygdx.game.net.NetBotTank;

import java.util.ArrayList;

public class Bots implements DrawAble {

    private static Texture botTexture = new Texture("TANK.tga");
    private static Texture boom = new Texture("Boom1.png");
    private static ArrayList<NetBotTank> botsSet = new ArrayList<NetBotTank>();
    private Texture texture = botTexture;


    public static void setBotsSet(ArrayList<NetBotTank> botsSet) {
        Bots.botsSet = botsSet;
    }

    public void draw(SpriteBatch batch) {

        for (int i = 0; i < botsSet.size(); i++) {

            if (!botsSet.get(i).isAlive()) {
                texture = boom;
            } else {
                texture = botTexture;
            }
            batch.draw(texture, botsSet.get(i).getPosition().x, botsSet.get(i).getPosition().y, texture.getWidth() / 2, texture.getWidth() / 2, texture.getWidth(), texture.getWidth(), 1, 1, botsSet.get(i).getAngle(), 0, 0, texture.getWidth(), texture.getWidth(), false, false);

        }
    }

}
