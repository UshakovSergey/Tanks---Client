package com.mygdx.game;

import java.io.Serializable;

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

    public Packet(String whoSent,
                  String msg, float posX, float posY) {
        this.whoSent = whoSent;
        this.msg = msg;
        this.posX = posX;
        this.posY = posY;
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

