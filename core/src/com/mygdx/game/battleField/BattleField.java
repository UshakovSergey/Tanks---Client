package com.mygdx.game.battleField;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.net.Client;

public class BattleField extends ApplicationAdapter {
    private SpriteBatch batch;
    private Client client;
    private GameController gameController;


    @Override
    public void create() {
        client = new Client();
        batch = new SpriteBatch();
        gameController = new GameController(client);
    }

    @Override
    public void render() {
        update();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        gameController.draw(batch);
        batch.end();
    }


    public void update() {

        gameController.sendMyDataToServer();

        gameController.receiveDataFromServer();

        gameController.addNewPlayers();

        gameController.updateOtherPlayersPosition();

        gameController.updateBullets();

        gameController.updateBots();

    }

}