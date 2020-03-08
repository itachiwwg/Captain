package slf.captain.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import slf.captain.protocol.*;
import slf.captain.protocol.messages.CaptainProtocolHeader;

import java.util.UUID;

public class CaptainClient {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 44,4));
                    pipeline.addLast(new CaptainProtocolEncoder());
                    pipeline.addLast(new CaptainProtocolDecoder());
                    pipeline.addLast(new ClientHandler());
                }
            });
            Channel channel = bootstrap.connect("127.0.0.1", 1989).sync().channel();
            int version = 1;
            int magicCode = 0xfe;
            int count = 1000;
            while (count > 0){
                String sessionId = UUID.randomUUID().toString();
                String content = " welcome to this protocol world!msg = " + count;

                CaptainProtocolHeader header = new CaptainProtocolHeader(magicCode, version, sessionId, content.length());
                CaptainMessage message = new CaptainMessage(header, content);
                channel.writeAndFlush(message);
                count--;
            }
            channel.closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
