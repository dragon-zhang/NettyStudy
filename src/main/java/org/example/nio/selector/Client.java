package org.example.nio.selector;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author zhangzicheng
 * @date 2021/07/11
 */
public class Client {
    public static void main(String[] args) throws Exception {
        SocketChannel client = SocketChannel.open();
        //绑定端口
        client.connect(new InetSocketAddress(8888));
        client.write(ByteBuffer.wrap("hello".getBytes()));

        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        client.read(byteBuffer);
        System.out.println("from server -> " + new String(byteBuffer.array()));
    }
}
