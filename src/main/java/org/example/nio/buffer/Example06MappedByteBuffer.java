package org.example.nio.buffer;

import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author zhangzicheng
 * @date 2021/07/11
 */
public class Example06MappedByteBuffer {
    public static void main(String[] args) throws Exception {
        URL url = ClassLoader.getSystemResource("test.txt");
        RandomAccessFile file = new RandomAccessFile(url.getPath(), "rw");
        FileChannel channel = file.getChannel();

        //把文件关联到缓冲区
        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 1024);
        //只要操作缓冲区，文件内容也会随之改变
        buffer.put("Hello World !".getBytes());
        channel.close();
    }
}
