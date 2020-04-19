package channel;
import java.io.*;
import java.net.Socket;
/**
 * @program: JDK
 * @description: socket模拟http请求
 * \n是换行，英文是New line。\r是回车，英文是Carriage return。
 * 不同的系统结束符 不同
 * @author: soulx
 * @create: 2020-04-18 19:04
 **/
public class HttpHelper {
    Socket socket = null;

    public void createSocket() {
        try {
            socket = new Socket("www.javathinker.org", 80);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void communcate() {
        // 注意这里必须制定请求方式 地址 注意空格
        // 不需要域名http://www.javathinker.org/  直接传输后面的url即可
//        StringBuffer sb = new StringBuffer("GET http://www.javathinker.org/ HTTP/1.1\r\n");
//        StringBuffer sb = new StringBuffer("GET / HTTP/1.1\r\n");
        StringBuffer sb = new StringBuffer("GET /category_server/ HTTP/1.1\r\n");
        // 以下为请求头
        sb.append("Host: www.javathinker.org\r\n");
        sb.append("User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0\r\n");
        sb.append("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n");
        sb.append("Accept-Language: zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        // 注意这里不要使用压缩 否则返回乱码
        sb.append("Accept-Encoding: \r\n");
        sb.append("Connection: keep-alive\r\n");
        sb.append("Upgrade-Insecure-Requests: 1\r\n");
        // 注意这里要换行结束请求头
        sb.append("\r\n");
        System.out.println(sb.toString());
        try {
            OutputStream os = socket.getOutputStream();
            os.write(sb.toString().getBytes());

            InputStream is = socket.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int len = -1;
            while ((len = is.read(bytes)) != -1) {
                baos.write(bytes, 0, len);
            }
            System.out.println(new String(baos.toByteArray()));
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HttpHelper client = new HttpHelper();
        client.createSocket();
        client.communcate();
    }
    public  void httpSend2(String[] args) {
        try {
            String url = "192.168.1.103";
            Socket socket = new Socket(url, 8085);
            PrintWriter dos = new PrintWriter(socket.getOutputStream());
            String postData = "imsi=460123&nonce=111111+&deviceid=135&status=1";
            dos.println("POST /syncuser/ HTTP/1.1");
            dos.println("HOST:192.168.1.103");
            dos.println("Content-Type:application/x-www-form-urlencoded");
            dos.println("Content-Length:" + postData.length());
            dos.println("Connection:Close");
            dos.println();
            dos.println(postData);
            dos.flush();
            dos.close();
            // 以上只进行了发送操作
            socket.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
