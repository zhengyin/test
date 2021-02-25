# BIO、NIO、AIO

## BIO 同步阻塞IO

 BIO 关注的是是否阻塞，无论是java.net包下提供的Socket编程接口还是java.nio包下提供的Socket接口，都有BIO的实现，下面两段代码演示了这样的情况，也就是说不是java.nio包下的socket编程就一定是NIO。
 
* 同步阻塞IO示例
``` 
/**
 * 同步阻塞IO示例
 * @author zhengyin zhengyinit@outlook.com
 */
public class BioServerDemo1 {
    private final static ExecutorService EXECUTOR = Executors.newFixedThreadPool(10);

    /**
     * telnet 127.0.0.1 8100 测试
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8100);
        while (true){
            //阻塞到有结果返回
            Socket socket = serverSocket.accept();
            EXECUTOR.execute(() -> {
                try {
                    PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
                    printWriter.write("Hello Client\n");
                    printWriter.close();
                    socket.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            });
        }
    }

}
```
* nio库的，同步阻塞的IO示例
``` 
/**
 * nio库的，同步阻塞的IO示例
 * @author zhengyin zhengyinit@outlook.com
 */
public class BioServerDemo2 {
    private final static ExecutorService EXECUTOR = Executors.newFixedThreadPool(10);

    /**
     * 启动以后运行， telnet 127.0.0.1 8100 测试
     * @param args
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        try{
            serverSocket.socket().bind(new InetSocketAddress(8100));
            while(true){
                //阻塞到有结果返回
                SocketChannel socketChannel =  serverSocket.accept();
                EXECUTOR.execute(() -> {
                    try {
                        byte[] response = "Hello Client\n".getBytes();
                        ByteBuffer buffer = ByteBuffer.allocate(response.length);
                        //响应客户端消息
                        buffer.put(response);
                        buffer.flip();
                        if(buffer.hasRemaining()){
                            try {
                                socketChannel.write(buffer);
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                    }finally {
                        try {
                            socketChannel.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }finally{
            serverSocket.close();
        }
    }
}
```

## NIO 同步非阻塞IO / 多路复用IO（IO multiplexing） 
    
* 传统NIO模型和阻塞IO类比，内核会立即返回，返回后获得足够的CPU时间继续做其它的事情，但是用户进程需要不断的去询问内核数据是否就绪，这一阶段是阻塞的。
    
* 多路复用IO是为了弥补传统NIO模型的不足，当用户进程调用了select，那么整个进程会被block，而同时，kernel会“监视”所有select负责的socket，当任何一个socket中的数据准备好了，select就会返回。

* 同时多路复用IO将一个Socket分为了几个阶段，可以针对不同阶段进行相应的处理

> 更多细节参考 ： http://blog.chinaunix.net/uid-28458801-id-4464639.html
    
``` 
    SelectionKey.OP_CONNECT		连接就绪
    SelectionKey.OP_ACCEPT		接收就绪
    SelectionKey.OP_READ		读就绪
    SelectionKey.OP_WRITE		写就绪
```
    
### Nio多路复用的示例
```
/**
 * 多路复用的Nio示例
 * @author zhengyin zhengyinit@outlook.com
 */
public class NioMultiplexingServerDemo {
    private final static ExecutorService EXECUTOR = Executors.newFixedThreadPool(10);
    /**
     * 启动以后运行， telnet 127.0.0.1 8100 测试
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8100));

        //不阻塞
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            //阻塞，直到有就绪的Key
            selector.select();
            Iterator keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()) {
                SelectionKey selectionKey = (SelectionKey)keyIterator.next();
                try {
                    if(selectionKey.isValid()){

                        if(selectionKey.isAcceptable()){
                            try {
                                ServerSocketChannel ssc = (ServerSocketChannel)selectionKey.channel();
                                //产生一个新的 socketChannel，处理客户端的数据
                                SocketChannel socketChannel = ssc.accept();
                                socketChannel.configureBlocking(false);
                                //注册一个已读的事件监听
                                socketChannel.register(selector, SelectionKey.OP_READ);
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                        //读就绪
                        if(selectionKey.isReadable()){
                            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                            try {
                                byte[] response = "Hello Client\n".getBytes();
                                ByteBuffer buffer = ByteBuffer.allocate(response.length);
                                //响应客户端消息
                                buffer.put(response);
                                buffer.flip();
                                if(buffer.hasRemaining()){
                                    try {
                                        socketChannel.write(buffer);
                                    }catch (IOException e){
                                        e.printStackTrace();
                                    }
                                }
                            }finally {
                                try {
                                    socketChannel.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (selectionKey != null) {
                        selectionKey.cancel();
                        selectionKey.channel().close();
                    }
                }
                keyIterator.remove();
            }
        }
    }


}

```

## AIO 异步IO

* Linux下的asynchronousIO，其实用得不多，从内核2.6版本才开始引入。用户进程发起read操作之后，立刻就可以开始去做其它的事。而另一方面，从kernel的角度，当它受到一个asynchronous read之后，首先它会立刻返回，所以不会对用户进程产生任何block。
    
``` 

/**
 * Aio Server 示例
 * @author zhengyin zhengyinit@outlook.com
 */
public class AioServerDemo {

    public static void main(String[] args) throws IOException,InterruptedException {
        AsynchronousChannelGroup group = AsynchronousChannelGroup.withCachedThreadPool(Executors.newFixedThreadPool(10), 3);
        AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open(group);
        serverChannel.bind(new InetSocketAddress(8100));
        ListenerHandler listenerHandler = new ListenerHandler(serverChannel);
        listenerHandler.doAccept(new AcceptCompileHandler());

        while (true){
            TimeUnit.SECONDS.sleep(1);
        }
    }


    public static class ListenerHandler extends Thread{
        private AsynchronousServerSocketChannel serverChannel;
        public ListenerHandler(AsynchronousServerSocketChannel serverChannel) {
            this.serverChannel = serverChannel;
        }
        public void doAccept(AcceptCompileHandler acceptCompileHandler){
            this.serverChannel.accept(this,acceptCompileHandler);
        }
        public void doAccept(ListenerHandler listenerHandler,AcceptCompileHandler acceptCompileHandler){
            this.serverChannel.accept(listenerHandler, acceptCompileHandler);
        }
    }

    private static class AcceptCompileHandler implements CompletionHandler<AsynchronousSocketChannel, ListenerHandler> {
        @Override
        public void completed(AsynchronousSocketChannel socketChannel, ListenerHandler listenerHandler) {
            //继续监听客户端连接
            listenerHandler.doAccept(listenerHandler, this);
            //读数据
            ByteBuffer buffer = ByteBuffer.allocate(128);
            socketChannel.read(buffer, buffer, new ReadCompileHandler(socketChannel));
        }
        @Override
        public void failed(Throwable exc, ListenerHandler listenerHandler) {
            exc.printStackTrace();
        }
    }

    private static class  ReadCompileHandler implements CompletionHandler<Integer, ByteBuffer> {
        private AsynchronousSocketChannel socketChannel;
        public ReadCompileHandler(AsynchronousSocketChannel socketChannel) {
            this.socketChannel = socketChannel;
        }

        @Override
        public void completed(Integer readSize, ByteBuffer buffer) {
            try {
                write("Hello Client\n");
            }finally {
                try {
                    socketChannel.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        @Override
        public void failed(Throwable exc, ByteBuffer attachment) {
            exc.printStackTrace();
        }

        private void write(String msg){
            byte[] bytes = msg.getBytes();
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
            buffer.put(bytes);
            buffer.flip();
            socketChannel.write(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer buffer) {
                    //没有发送完，接着发
                    if(buffer.hasRemaining()){
                        socketChannel.write(buffer, buffer, this);
                    }
                }
                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    exc.printStackTrace();
                }
            });
        }
    }
}

```