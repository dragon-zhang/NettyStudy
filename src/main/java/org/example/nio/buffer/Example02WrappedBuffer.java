package org.example.nio.buffer;

import java.nio.IntBuffer;

/**
 * @author zhangzicheng
 * @date 2021/05/25
 */
public class Example02WrappedBuffer {
    public static void main(String[] args) {
        //分配一个大小为8的新缓冲区
        IntBuffer buffer = IntBuffer.allocate(8);

        //使用包装的方式创建缓冲区
        int[] ints = new int[8];
        IntBuffer buffer2 = IntBuffer.wrap(ints);
    }
}
