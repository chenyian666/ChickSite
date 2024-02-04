package org.yian.communication;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TcpServer {
    public static void main(String[] args) throws IOException {

        // 创建ServerSocket的对象，为服务器注册端口
        try (ServerSocket serverSocket = new ServerSocket(9977)) {
            System.out.println("--------------服务端启动--------------");
            // 等待客户端的请求
            Socket socket = serverSocket.accept();

            // 从socket通信管道得到一个字节输入流
            InputStream inputStream = socket.getInputStream();

            // 把原始的字节输入流包装成数据输入流
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            while (true) {
                try {
                    // 使用数据输入流读取客户端发送过来的数据
                    String read = dataInputStream.readUTF();
//            byte[] bytes = dataInputStream.readAllBytes();
//            String read = new String(bytes, StandardCharsets.UTF_8);
                    Instant receiveTime = Instant.now();
                    String[] split = read.split(",");
                    System.out.println(split[0]);
                    System.out.println("消息接收时间戳：" + receiveTime);
                    Instant sendTime = Instant.parse(split[1]);
                    long delay = ChronoUnit.MILLIS.between(sendTime, receiveTime);
                    System.out.println("消息延迟时间：" + delay + "毫秒");
                } catch (Exception e) {
                    System.out.println(socket.getRemoteSocketAddress() + "用户已离线");
                    dataInputStream.close();
                    break;
                }
            }
        }
    }

}
