package slf.captain.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import slf.captain.protocol.messages.CaptainProtocolHeader;

import java.util.List;

public class CaptainProtocolDecoder extends ByteToMessageDecoder {

    private final static int HEADER_LENGTH = 48;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) {
        if (HEADER_LENGTH > byteBuf.readableBytes()) {
            return;
        }
        int magiCode = byteBuf.readInt();
        int version = byteBuf.readInt();
        byte[] sessionId = new byte[36];
        byteBuf.readBytes(sessionId);
        String sessionIdStr = new String(sessionId);
        int contentLen = byteBuf.readInt();
        if (contentLen > byteBuf.readableBytes()) {
            //byteBuf.setIndex(byteBuf.readerIndex() - contentLen, byteBuf.writerIndex());
            return;
        }
        byte[] content = new byte[contentLen];
        byteBuf.readBytes(content);

        CaptainProtocolHeader header = new CaptainProtocolHeader(magiCode, version, sessionIdStr, contentLen);
        CaptainMessage message = new CaptainMessage();
        message.setCaptainProtocolHeader(header);
        message.setContent(new String(content));
        list.add(message);
    }
}
