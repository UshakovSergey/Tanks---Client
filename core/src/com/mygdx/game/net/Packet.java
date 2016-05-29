package com.mygdx.game.net;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;


public class Packet implements Serializable {

    private String msg = "";
    private int playerId;
    private float posX;
    private float posY;
    private int rotateAngle;
    private ArrayList<NetBullet> store = new ArrayList<NetBullet>();
    private ArrayList<NetBotTank> botSet = new ArrayList<NetBotTank>();
    private ArrayList<NetPlayerTank> playerList = new ArrayList<NetPlayerTank>();
    private int clientPort;
    private InetAddress clientAddress;


    public Packet(String msg, int playerId, float posX, float posY, int rotateAngle, ArrayList<NetBullet> store, ArrayList<NetBotTank> botSet) {
        this.msg = msg;
        this.playerId = playerId;
        this.posX = posX;
        this.posY = posY;
        this.rotateAngle = rotateAngle;
        this.store = store;
        this.botSet = botSet;
    }

    public ArrayList<NetPlayerTank> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(ArrayList<NetPlayerTank> playerList) {
        this.playerList = playerList;
    }

    public int getClientPort() {
        return clientPort;
    }

    public void setClientPort(int clientPort) {
        this.clientPort = clientPort;
    }

    public InetAddress getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(InetAddress clientAddress) {
        this.clientAddress = clientAddress;
    }

    public ArrayList<NetBotTank> getBotSet() {
        return botSet;
    }

    public void setBotSet(ArrayList<NetBotTank> botSet) {
        this.botSet = botSet;
    }

    public ArrayList<NetBullet> getStore() {
        return store;
    }

    public void setStore(ArrayList<NetBullet> store) {
        this.store = store;
    }

    public int getRotateAngle() {
        return rotateAngle;
    }

    public void setRotateAngle(int rotateAngle) {
        this.rotateAngle = rotateAngle;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}

