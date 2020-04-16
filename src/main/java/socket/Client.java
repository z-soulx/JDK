package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 客户端连接.
 *
 * @author skywalker
 */
public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket();
        socket.setTcpNoDelay(true);
        socket.connect(new InetSocketAddress("localhost", 8080));
        InputStream is = socket.getInputStream();

            OutputStream outputStream = socket.getOutputStream();
        byte[] data = new byte[8];
//        is.read(data, 0, 8);
        outputStream.write('a');
        //一端退出，但退出时并未关闭该连接，
        // 另一端如果在从连接中读数据则抛出该异常（Connection reset）。
        // 简单的说就是在连接断开后的读和写操作引起的。
        Thread.sleep(1000);
        System.out.println(new String(data));
    }

}