package com.mygdx.game.net;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class NetSupport {

    public static Packet getPacket(DatagramSocket socket) {

        byte[] incomingData = new byte[2048];
        DatagramPacket packet = new DatagramPacket(incomingData, 2048);

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

    public static void sendPacket(DatagramSocket socket, InetAddress address, int serverPort, Packet dataPacket) {

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


    public static Packet getPacket2(DatagramSocket socket) {

        byte[] incomingData = new byte[2048];
        DatagramPacket packet = new DatagramPacket(incomingData, 2048);

        try {
            socket.receive(packet);
            incomingData = packet.getData();

            byte[] limitedData = new byte[packet.getLength()]; // что бы пакет был поменьше если использую далее
            for (int i = 0; i < packet.getLength(); i++) {
                limitedData[i] = incomingData[i];
            }
            ByteArrayInputStream in = new ByteArrayInputStream(limitedData);
            ObjectInputStream obj = new ObjectInputStream(in);
            Packet receivedPacket = (Packet) obj.readObject();
            receivedPacket.setClientAddress(packet.getAddress());
            receivedPacket.setClientPort(packet.getPort());
            return receivedPacket;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return null;
    }


}
