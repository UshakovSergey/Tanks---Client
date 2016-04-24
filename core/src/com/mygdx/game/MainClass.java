package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.VertexBufferObjectWithVAO;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public class MainClass extends ApplicationAdapter {
    SpriteBatch batch;
    public static PlayerTank plTank1;
    public static PlayerTank plTank2;
    protected Client client = null;
    final int NUMBER_OF_BOTS = 15;
    public static Array<BotTank> botList = new Array<BotTank>();

    @Override
    public void create() {
        batch = new SpriteBatch();
        plTank1 = new PlayerTank(new Vector2(0, 0), new Vector2(0, 0));
        plTank2 = new PlayerTank(new Vector2(50, 50), new Vector2(0, 0));

        client = new Client();
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

        // send my data
        Vector2 my = plTank1.getPosition();
        ArrayList<AbstractBullet> myStoreToSend = new ArrayList<AbstractBullet>();
        if (plTank1.getStore().size() > 0) {

            for (int i = 0; i < plTank1.getStore().size(); i++) {

                myStoreToSend.add(new AbstractBullet(plTank1.getStore().get(i).getBulletPosition(), plTank1.getStore().get(i).getBulletSpeed()));
            }
        }
        Packet myPacketToSend = new Packet(client.getId(), "", my.x, my.y, plTank1.getRotateAngle(), myStoreToSend, null);
        client.sendPacket(client.getSocket(), client.getAddress(), client.getServerPort(), myPacketToSend);


        // receive data
        Packet receivedPacket = client.getPacket(client.getSocket());
        Vector2 opponent = new Vector2(receivedPacket.getPosX(), receivedPacket.getPosY());
        plTank2.setPosition(opponent);
        plTank2.setRotateAngle(receivedPacket.getRotateAngle());

        ArrayList<Bullet> opponentStore = new ArrayList<Bullet>();
        if (receivedPacket.getStore().size() > 0) {
            for (int i = 0; i < receivedPacket.getStore().size(); i++) {

                Bullet bul = new Bullet(receivedPacket.getStore().get(i).getBulletPosition(), receivedPacket.getStore().get(i).getBulletSpeed());

                opponentStore.add(bul);
            }
        }
        plTank2.setStore(opponentStore);
    }

}