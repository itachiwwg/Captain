package slf.captain.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import slf.captain.protocol.messages.CaptainMessage;

public class ClientHandler extends SimpleChannelInboundHandler<CaptainMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CaptainMessage captainMessage) {
        System.out.println(captainMessage.toString());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {

    }
}
