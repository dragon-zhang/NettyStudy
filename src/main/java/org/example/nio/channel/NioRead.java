package org.example.nio.channel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author zhangzicheng
 * @date 2021/07/11
 */
public class NioRead {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        URL url = ClassLoader.getSystemResource("test.txt");
        try (FileInputStream fin = new FileInputStream(url.getPath());
             FileChannel channel = fin.getChannel()) {
            ByteBuffer bf = ByteBuffer.allocate(256);
            int length = -1;
            while ((length = channel.read(bf)) != -1) {
                //注意，读取后，将位置置为0，将limit置为容量, 以备下次读入到字节缓冲中，从0开始存储
                bf.clear();
                String line = new String(bf.array(), 0, length);
                System.out.println(line);
                sb.append(line);
            }
            System.out.println("result:" + sb.toString().length());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
