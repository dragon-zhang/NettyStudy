package org.example.nio;

import java.nio.IntBuffer;

/**
 * @author zhangzicheng
 * @date 2021/05/25
 */
public class Example03BufferSlice {
    public static void main(String[] args) {
        //创建母缓冲区
        IntBuffer buffer = IntBuffer.allocate(8);
        //初始化缓冲区的数据0-7
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put(i);
        }
        buffer.flip();
        while (buffer.hasRemaining()) {
            //获取当前位置的元素，然后递增当前位置
            int i = buffer.get();
            System.out.print(i + "\t");
        }
        System.out.println();

        //创建子缓冲区
        buffer.position(3);
        buffer.limit(5);
        IntBuffer slice = buffer.slice();
        //改变子缓冲区内容
        for (int i = 0; i < slice.capacity(); i++) {
            slice.put(i, slice.get(i) * 10);
        }

        //重置
        buffer.position(0);
        buffer.limit(buffer.capacity());
        while (buffer.hasRemaining()) {
            //获取当前位置的元素，然后递增当前位置
            int i = buffer.get();
            System.out.print(i + "\t");
        }
        System.out.println();
        //从输出结果可以发现，母子缓冲区的内容是共享的
    }
}
