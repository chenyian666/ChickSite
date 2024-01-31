package org.yian.communication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpServer {
    public static void main(String[] args) throws SocketException {
        // 创建一个服务端对象
        DatagramSocket socket = new DatagramSocket(7788);
        // 创建一个接受数据的对象
        byte[] bytes = new byte[1024 * 64];
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
        while (true) {
            try {
                // 开始使用数据包来接收客户端发来的数据
                socket.receive(packet);
                // 获取了多少数据
                int length = packet.getLength();
                // 将接收到的数据直接打印出来
                System.out.println(new String(bytes, 0, length));
                System.out.println(packet.getLength());
                System.out.println(packet.getAddress().getHostAddress());
                System.out.println(packet.getPort());
                System.out.println("------------------------------------------");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
