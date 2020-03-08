package slf.captain.service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.concurrent.Future;
import slf.captain.protocol.CaptainProtocolDecoder;
import slf.captain.protocol.CaptainProtocolEncoder;
import slf.captain.protocol.ServerHandler;

import java.util.concurrent.TimeUnit;

public class FrontServer {

    private static final int PORT = 1989;

    // only 1 thread for accpetor is enough;
    private final EventLoopGroup acceptor = new NioEventLoopGroup(1);

    // default thread num = num_of_cores * 2;
    private final EventLoopGroup works = new NioEventLoopGroup();

    private ServerBootstrap serverBootstrap;

    // true : running; false : stop
    private boolean isRunning = false;

    public FrontServer() {

    }

    private void init() throws InterruptedException {
        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(acceptor, works);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);
        serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) {
                ChannelPipeline pipeline = ch.pipeline();

                // 添加编解码器, 由于ByteToMessageDecoder的子类无法使用@Sharable注解,
                // 这里必须给每个Handler都添加一个独立的Decoder.
                pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 44, 4));
                pipeline.addLast(new CaptainProtocolEncoder());
                pipeline.addLast(new CaptainProtocolDecoder());

                // 添加逻辑控制层
                pipeline.addLast(new ServerHandler());
            }
        });
        ChannelFuture channelFuture = serverBootstrap.bind(PORT).sync();
        if (channelFuture.isSuccess()) {
            System.out.println("Server Started Success!");
            isRunning = true;
        }
    }

    public synchronized void start() {
        try {
            init();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void stop() {
        if (!isRunning) {
            throw new RuntimeException("Server is NOT running!");
        }
        try {
            boolean tasks = this.works.shutdownGracefully().await(10, TimeUnit.SECONDS);
            if (!tasks) {
                throw new RuntimeException("Can't stop work threads");
            }

            Future<?> future = this.acceptor.shutdownGracefully().await();
            if (!future.isSuccess()) {
                throw new RuntimeException("Can't stop acceptor thread");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
