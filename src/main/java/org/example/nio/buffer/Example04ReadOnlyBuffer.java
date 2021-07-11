package org.example.nio.buffer;

import java.nio.IntBuffer;

/**
 * @author zhangzicheng
 * @date 2021/05/25
 */
public class Example04ReadOnlyBuffer {
    public static void main(String[] args) {
        //创建母缓冲区
        IntBuffer buffer = IntBuffer.allocate(8);
        //初始化缓冲区的数据0-7
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put(i);
        }
        buffer.flip();
        IntBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        while (readOnlyBuffer.hasRemaining()) {
            int i = readOnlyBuffer.get();
            System.out.print(i + "\t");
        }
        System.out.println();

        try {
            readOnlyBuffer.put(0, 1);
        } catch (Exception e) {
            //只读缓冲区数据不可修改
            e.printStackTrace();
        }

        readOnlyBuffer.position(0);
        while (readOnlyBuffer.hasRemaining()) {
            int i = readOnlyBuffer.get();
            System.out.print(i + "\t");
        }
        System.out.println();

        //对原缓冲区的改动会直接影响到只读缓冲区
        buffer.put(0, 1);
        readOnlyBuffer.position(0);
        while (readOnlyBuffer.hasRemaining()) {
            int i = readOnlyBuffer.get();
            System.out.print(i + "\t");
        }
        System.out.println();
    }
}
