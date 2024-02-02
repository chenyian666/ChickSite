package org.yian.communication;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

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

            // 使用数据输入流读取客户端发送过来的数据
//            String read = dataInputStream.readUTF();
            byte[] bytes = dataInputStream.readAllBytes();
            String read = new String(bytes, StandardCharsets.UTF_8);
            System.out.println(read);

            dataInputStream.close();
        }
    }
}
