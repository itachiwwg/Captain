package slf.captain.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<CaptainMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CaptainMessage captainMessage) {
        System.out.println("Receive Msg = " + captainMessage.toString());
        channelHandlerContext.writeAndFlush(captainMessage);
    }
}
