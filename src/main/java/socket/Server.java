package socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server.
 *
 * @author skywalker
 */
public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress(8080));
        while (true) {
            Socket socket = ss.accept();
            new Thread(()->{
                InputStream inputStream = null;
                try {
                    inputStream = socket.getInputStream();

                int read = inputStream.read();
                System.out.println(read);
                } catch (IOException  e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }

}
