package org.yian.communication;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @Description: UDP通讯的客户端
 * @Author: chenyian
 * @Date: 2024/1/30 16:25
 */
public class UdpClient {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        // 创建客户端
        // 创建数据包对象，封装要发出去的数据
        while (true) {
            try (DatagramSocket socket = new DatagramSocket()) {

                System.out.println("请输入要发送的消息:");
                String hostname = scanner.nextLine();
                if (hostname.toLowerCase(Locale.ROOT).equals("exit")) {
                    break;
                }
                InetAddress address = InetAddress.getByName("127.0.0.1");
                byte[] bytes = hostname.getBytes();
                DatagramPacket packet = new DatagramPacket(bytes, bytes.length,address,7788);
                // 正式开始发送数据
                socket.send(packet);
                System.out.println("Udp客户端消息已发送！");

            }
        }
    }
}
