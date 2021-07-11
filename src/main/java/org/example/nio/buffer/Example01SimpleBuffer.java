package org.example.nio.buffer;

import java.nio.IntBuffer;

/**
 * @author zhangzicheng
 * @date 2021/05/25
 */
public class Example01SimpleBuffer {
    public static void main(String[] args) {
        //分配一个大小为8的新缓冲区
        IntBuffer buffer = IntBuffer.allocate(8);
        for (int i = 0; i < buffer.capacity(); i++) {
            //往缓冲区放入数据
            buffer.put(i);
        }
        //将限制设置为当前位置，然后将当前位置设置为0
        buffer.flip();
        //查看当前位置和限制位置之间是否有元素
        while (buffer.hasRemaining()) {
            //获取当前位置的元素，然后递增当前位置
            int i = buffer.get();
            System.out.println(i);
        }
    }
}
