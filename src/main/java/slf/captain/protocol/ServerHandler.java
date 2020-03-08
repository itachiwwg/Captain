package slf.captain.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<FFMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FFMessage ffMessage) throws Exception {
        System.out.println("Receive Msg = " + ffMessage.toString());
        channelHandlerContext.writeAndFlush(ffMessage);
    }
}
