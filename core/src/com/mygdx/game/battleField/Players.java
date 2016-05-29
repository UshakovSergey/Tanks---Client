package com.mygdx.game.battleField;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.interfaces.DrawAble;
import com.mygdx.game.tanks.PlayerTank;
import com.mygdx.game.tanks.Tank;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Players implements DrawAble {

    private List<Tank> playerTankSet = new ArrayList<Tank>();
    private int numberOfPlayers;
    private Tank myTank;

    public Players(int id) {
        myTank = new PlayerTank(new Vector2(new Random().nextInt(200)+ 100, 0), new Vector2(0, 0), id);
        addPlayer(myTank);
    }

    @Override
    public void draw(SpriteBatch batch) {
        for (int i = 0; i < playerTankSet.size(); i++) {
            playerTankSet.get(i).draw(batch);
        }
    }

    public  void addPlayer(Tank tank){
        playerTankSet.add(tank);
        numberOfPlayers = playerTankSet.size();
    }

    public Tank getMyTank() {
        return myTank;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public List<Tank> getPlayerTankSet() {
        return playerTankSet;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }
}
