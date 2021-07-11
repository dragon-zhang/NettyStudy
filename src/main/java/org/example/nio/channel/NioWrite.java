package org.example.nio.channel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author zhangzicheng
 * @date 2021/07/11
 */
public class NioWrite {
    public static void main(String[] args) {
        URL url = ClassLoader.getSystemResource("test.txt");
        try (FileOutputStream fos = new FileOutputStream(url.getPath());
             FileChannel channel = fos.getChannel()) {
            ByteBuffer buffer = StandardCharsets.UTF_8.encode("你好你好你好你好你好");
            channel.write(buffer);
            buffer.flip();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
