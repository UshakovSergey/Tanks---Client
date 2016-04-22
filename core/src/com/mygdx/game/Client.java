package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: US
 * Date: 20.04.16
 * Time: 20:45
 * To change this template use File | Settings | File Templates.
 */
public class Client implements Runnable {

    private int serverPort = 4444;
    private InetAddress address = null;
    private DatagramSocket socket = null;
    private String id = "";

    public Client() {

        try {

            socket = new DatagramSocket();
            address = InetAddress.getByName("localhost");

            sendPacket(socket, address, serverPort, new Packet("don't know yet", "ready", 0, 0));

            Packet response = getPacket(socket);
            id = response.getMsg();
            System.out.println("my ID: " + id);

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Override
    public void run() {

        while (true) {

            Vector2 my = MainClass.plTank1.getPosition();
            Packet myPacketToSend = new Packet(id, "", my.x, my.y);
            sendPacket(socket, address, serverPort, myPacketToSend);

            Packet opponentPacket = getPacket(socket);
            Vector2 op = new Vector2(opponentPacket.getPosX(), opponentPacket.getPosY());
            MainClass.plTank2.setPosition(op);

            try {
                TimeUnit.MICROSECONDS.sleep(20);

            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
    // надо будет выносить в методы суперкласа, от него будут наследоваться классы клиент и сервер (те же методы в клиенте)
    public Packet getPacket(DatagramSocket socket) {

        byte[] incomingData = new byte[1024];
        DatagramPacket packet = new DatagramPacket(incomingData, 1024);

        try {
            socket.receive(packet);
            incomingData = packet.getData();

            byte[] limitedData = new byte[packet.getLength()]; // что бы пакет был поменьше если использую далее
            for (int i = 0; i < packet.getLength(); i++) {
                limitedData[i] = incomingData[i];
            }

            ByteArrayInputStream in = new ByteArrayInputStream(limitedData);
            ObjectInputStream obj = new ObjectInputStream(in);
            return (Packet) obj.readObject();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return null;
    }

    // надо будет выносить в методы суперкласа, от него будут наследоваться классы клиент и сервер (те же методы в клиенте)
    public void sendPacket(DatagramSocket socket, InetAddress address, int serverPort, Packet dataPacket) {

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);

            oos.writeObject(dataPacket);
            oos.flush();

            byte[] buf = baos.toByteArray();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, serverPort);
            socket.send(packet);

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

}