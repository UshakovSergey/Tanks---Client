package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.VertexBufferObjectWithVAO;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class MainClass extends ApplicationAdapter {
    SpriteBatch batch;
    public static PlayerTank plTank1;
    public static PlayerTank plTank2;
    final int NUMBER_OF_BOTS = 15;
    Array<BotTank> botList = new Array<BotTank>();

    @Override
    public void create() {
        batch = new SpriteBatch();
        plTank1 = new PlayerTank(new Vector2(0, 0), new Vector2(0, 0));
        plTank2 = new PlayerTank(new Vector2(50, 50), new Vector2(0, 0));

        Thread thread = new Thread(new Client());
        thread.start();

        for (int i = 0; i < NUMBER_OF_BOTS; i++) {
            botList.add(new BotTank(new Vector2(BotTank.rn.nextInt(800), BotTank.rn.nextInt(600)), new Vector2(BotTank.rn.nextInt(4) - 1.5f, BotTank.rn.nextInt(4) - 1.5f)));
        }

    }

    @Override
    public void render() {
        update();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        plTank1.draw(batch);
        plTank2.draw(batch);
        for (int i = 0; i < botList.size; i++) {
            botList.get(i).draw(batch);
        }

        batch.end();

    }


    public void update() {
                              // мой танк
        plTank1.update();
        for (int i = 0; i < botList.size; i++) {
            botList.get(i).update();
            if (plTank1.store.size() > 0) {
                for (int j = 0; j < plTank1.store.size(); j++) {
                    if (botList.get(i).Position.cpy().add(botList.get(i).tankTextureSize / 2, botList.get(i).tankTextureSize / 2).sub(plTank1.store.get(j).getBulletPosition()).len() < botList.get(i).tankTextureSize / 2 && !plTank1.store.get(j).isDestroyed()) {
                        botList.get(i).destroy();
                        plTank1.store.get(j).destroy();
                    }
                }
            }
        }

        //plTank2.update();                     // танк опонента , апдейтим с сервера
        for (int i = 0; i < botList.size; i++) {
            botList.get(i).update();
            if (plTank2.store.size() > 0) {
                for (int j = 0; j < plTank2.store.size(); j++) {
                    if (botList.get(i).Position.cpy().add(botList.get(i).tankTextureSize / 2, botList.get(i).tankTextureSize / 2).sub(plTank2.store.get(j).getBulletPosition()).len() < botList.get(i).tankTextureSize / 2 && !plTank2.store.get(j).isDestroyed()) {
                        botList.get(i).destroy();
                        plTank2.store.get(j).destroy();
                    }
                }
            }
        }


        if (botList.size > 0) {                  //умри бот
            for (int i = 0; i < botList.size; i++) {
                if (!botList.get(i).isAlive()) {
                    botList.get(i).destroyDebounce += Gdx.graphics.getDeltaTime();
                    if (botList.get(i).destroyDebounce > 0.4) {
                        botList.removeIndex(i);
                    }
                }
            }
        }
    }
}