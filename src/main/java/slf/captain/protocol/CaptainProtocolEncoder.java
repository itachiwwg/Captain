package slf.captain.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import slf.captain.protocol.messages.CaptainProtocolHeader;

public class CaptainProtocolEncoder extends MessageToByteEncoder<CaptainMessage> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, CaptainMessage captainMessage, ByteBuf byteBuf) {
        CaptainProtocolHeader header = captainMessage.getCaptainProtocolHeader();
        byteBuf.writeInt(header.getMagiCode());
        byteBuf.writeInt(header.getVersion());
        byteBuf.writeBytes(header.getSessionId().getBytes());
        byteBuf.writeInt(header.getContentLength());
        byteBuf.writeBytes(captainMessage.getContent().getBytes());
    }
}
