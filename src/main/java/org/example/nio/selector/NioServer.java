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
public class NioServer {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel server = ServerSocketChannel.open();
        //配置非阻塞模式
        server.configureBlocking(false);
        //绑定端口
        server.socket().bind(new InetSocketAddress(8888));

        Selector selector = Selector.open();
        //注册感兴趣的事件
        server.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            try {
                //Selector.select()会一直阻塞，直到至少有1个事件发生
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    SelectableChannel channel = key.channel();
                    if (key.isAcceptable()) {
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) channel;
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        //客户端发送数据到服务端，对应服务端的读事件
                        SocketChannel socketChannel = (SocketChannel) channel;
                        ByteBuffer buffer = ByteBuffer.allocate(100);
                        int length = socketChannel.read(buffer);
                        if (length > 0) {
                            buffer.flip();
                            String attach = new String(buffer.array());
                            System.out.println("read from client -> " + attach);
                            SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_WRITE);
                            selectionKey.attach(attach);
                        } else {
                            socketChannel.close();
                        }
                        buffer.clear();
                    } else if (key.isWritable()) {
                        //服务端发送数据到客户端，对应服务端的写事件
                        SocketChannel socketChannel = (SocketChannel) channel;
                        String attachment = key.attachment().toString();
                        socketChannel.write(ByteBuffer.wrap(attachment.getBytes()));
                        System.out.println("send to client -> " + attachment);
                        socketChannel.close();
                    }
                    //防止下次重复处理
                    iterator.remove();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
