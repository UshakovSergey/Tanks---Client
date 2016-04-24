package com.mygdx.game;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: US
 * Date: 21.04.16
 * Time: 15:58
 * To change this template use File | Settings | File Templates.
 */
public class Packet implements Serializable {

    private String whoSent = "";
    private String msg = "";
    private float posX = 0;
    private float posY = 0;
    private int rotateAngle = 0;
    private ArrayList<AbstractBullet> store = new ArrayList<AbstractBullet>();
    private ArrayList<PseudoTank> botSet = new ArrayList<PseudoTank>();


    public Packet(String whoSent, String msg, float posX, float posY, int rotateAngle, ArrayList<AbstractBullet> store, ArrayList<PseudoTank> botSet) {
        this.whoSent = whoSent;
        this.msg = msg;
        this.posX = posX;
        this.posY = posY;
        this.rotateAngle = rotateAngle;
        this.store = store;
        this.botSet = botSet;
    }

    public ArrayList<PseudoTank> getBotSet() {
        return botSet;
    }

    public void setBotSet(ArrayList<PseudoTank> botSet) {
        this.botSet = botSet;
    }

    public ArrayList<AbstractBullet> getStore() {
        return store;
    }

    public void setStore(ArrayList<AbstractBullet> store) {
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getWhoSent() {
        return whoSent;
    }

    public void setWhoSent(String whoSent) {
        this.whoSent = whoSent;
    }

}

