package com.mygdx.game.battleField;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.interfaces.DrawAble;
import com.mygdx.game.net.Client;
import com.mygdx.game.net.NetSupport;
import com.mygdx.game.net.Packet;
import com.mygdx.game.tanks.PlayerTank;

public class GameController implements DrawAble {
    private static Vector2 nullVector = new Vector2(0, 0);
    private Bullets bulletSet;
    private Bots botSet;
    private Players playerSet;
    private Client client;
    private Packet receivedPacket;

    public GameController(Client _client) {
        bulletSet = new Bullets();
        botSet = new Bots();
        playerSet = new Players(_client.getPlayerID());
        client = _client;
    }

    @Override
    public void draw(SpriteBatch batch) {
        bulletSet.draw(batch);
        botSet.draw(batch);
        playerSet.draw(batch);
    }

    public void sendMyDataToServer() {

        playerSet.getMyTank().update();

        // send my data  - отправляем координаты моего танка и координаты моих пуль
        Vector2 my = playerSet.getMyTank().getPosition();
        Packet myPacketToSend = new Packet("", playerSet.getMyTank().getTankId(), my.x, my.y, playerSet.getMyTank().getRotateAngle(), playerSet.getMyTank().getNetBulletsStore(), null);
        NetSupport.sendPacket(client.getSocket(), client.getAddress(), client.getServerPort(), myPacketToSend);
        playerSet.getMyTank().getNetBulletsStore().clear();

    }

    public void receiveDataFromServer() {
        receivedPacket = NetSupport.getPacket(client.getSocket());
    }


    public void addNewPlayers() {
        if (playerSet.getNumberOfPlayers() != receivedPacket.getPlayerList().size()) {
            for (int i = 0; i < receivedPacket.getPlayerList().size(); i++) {
                boolean tankNotExist = true;
                for (int j = 0; j < playerSet.getPlayerTankSet().size(); j++) {
                    tankNotExist = tankNotExist && (receivedPacket.getPlayerList().get(i).getPlayerID() != playerSet.getPlayerTankSet().get(j).getTankId());
                }
                if (tankNotExist)
                    playerSet.addPlayer(new PlayerTank(receivedPacket.getPlayerList().get(i).getPosition(), nullVector, receivedPacket.getPlayerList().get(i).getPlayerID()));
            }
        }

    }

    public void updateOtherPlayersPosition() {
        for (int i = 0; i < playerSet.getPlayerTankSet().size(); i++) {
            for (int j = 0; j < receivedPacket.getPlayerList().size(); j++) {
                if (playerSet.getPlayerTankSet().get(i).getTankId() == receivedPacket.getPlayerList().get(j).getPlayerID() && playerSet.getPlayerTankSet().get(i).getTankId() != playerSet.getMyTank().getTankId()) {
                    playerSet.getPlayerTankSet().get(i).setPosition(receivedPacket.getPlayerList().get(j).getPosition());
                    playerSet.getPlayerTankSet().get(i).setRotateAngle(receivedPacket.getPlayerList().get(j).getRotateAngle());
                }
            }
        }
    }

    public void updateBullets() {
        bulletSet.setBulletsSet(receivedPacket.getStore());
    }

    public void updateBots() {
        botSet.setBotsSet(receivedPacket.getBotSet());
    }
}
