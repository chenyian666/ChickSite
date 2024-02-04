package org.yian.communication;

import java.awt.*;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Scanner;

public class TcpClient {
    public static void main(String[] args) throws IOException {
        // 创建Socket对象，并同时请求服务端程序的连接
        try (Socket socket = new Socket("127.0.0.1", 9977)) {
            // 从Socket通信管道中得到一个字节输出流对象，用来发送数据给服务端
            OutputStream outputStream = socket.getOutputStream();
            // 把低级的字节输出流包装成数据输出流
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            // 开始写数据出去
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("请发送:");
                String next = scanner.nextLine();
                if (next.equals("exit")) {
                    break;
                }
                Instant sendTime = Instant.now();
                System.out.println("消息发送时间戳：" + sendTime);
                dataOutputStream.writeUTF(next+","+sendTime);
                dataOutputStream.flush();
            }
            dataOutputStream.close();
        }
    }
}
