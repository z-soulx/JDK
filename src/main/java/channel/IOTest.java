package channel;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @program: JDK
 * @description: 新旧io测试(oio nio)
 * @author: soulx
 * @create: 2020-04-17 15:03
 **/
public class IOTest {
    public static void main(String[] args) throws Exception {
        FileInputStream inputStream = new FileInputStream(new File("/Users/soulx/Desktop/PG/pof/smjj-business-product/src/main/test/test.txt"));
        byte[] bytes = new byte[5];
//        inputStream.read(bytes);

        BufferedInputStream bis = new BufferedInputStream(inputStream);
        int read = bis.read();
        bis.read(bytes);
        System.out.println(bytes[1]);
//
//        FileChannel channel = inputStream.getChannel();
//        ByteBuffer byteBuffers =  ByteBuffer.allocate(5);
//         channel.read(byteBuffers);
//        System.out.println(byteBuffers.get(1));

    }
}
