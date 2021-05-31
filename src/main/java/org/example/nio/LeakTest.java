package org.example.nio;

import java.nio.ByteBuffer;
import sun.nio.ch.DirectBuffer;

/**
 * @author zhangzicheng
 * @date 2021/05/25
 */
public class LeakTest {
    public static void main(String[] args) throws Exception {
        Thread.sleep(20000);
        //创建直接缓冲区，会尽可能地进行零拷贝
        System.out.println("start allocate");
        ByteBuffer buffer = ByteBuffer.allocateDirect(Integer.MAX_VALUE >> 1);
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }
        buffer.flip();
        Thread.sleep(10000);
        //回收内存
        System.out.println("start clean");
        //如果不主动回收，那么只有在在full gc的时候才会回收
        DirectBuffer directBuffer = (DirectBuffer) buffer;
        directBuffer.cleaner().clean();
        Thread.sleep(999000);
    }
}
