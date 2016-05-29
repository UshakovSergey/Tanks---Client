package com.mygdx.game.net;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Client {

    private int serverPort = 4444;
    private InetAddress address = null;
    private DatagramSocket socket = null;
    private int playerID;
    private ArrayList<NetBullet> stubStore = new ArrayList<NetBullet>();

    public Client() {
        try {
            socket = new DatagramSocket();
            address = InetAddress.getByName("localhost");
            NetSupport.sendPacket(socket, address, serverPort, new Packet("ready", -1, 0, 0, 0, stubStore, null));
            Packet response = NetSupport.getPacket(socket);
            playerID = response.getPlayerId();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public int getServerPort() {
        return serverPort;
    }

    public InetAddress getAddress() {
        return address;
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public int getPlayerID() {
        return playerID;
    }
}