package org.example.nio.buffer;

import java.nio.ByteBuffer;
import sun.nio.ch.DirectBuffer;

/**
 * @author zhangzicheng
 * @date 2021/05/25
 */
public class Example05ZeroCopyBuffer {
    public static void main(String[] args) {
        //创建直接缓冲区，会尽可能地进行零拷贝
        ByteBuffer buffer = ByteBuffer.allocateDirect(8);
        //初始化缓冲区的数据0-7
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) ('0' + i));
        }
        buffer.flip();
        while (buffer.hasRemaining()) {
            byte i = buffer.get();
            System.out.print(new String(new byte[]{i}) + "\t");
        }
        System.out.println();

        //回收内存
        DirectBuffer directBuffer = (DirectBuffer) buffer;
        directBuffer.cleaner().clean();
    }
}
