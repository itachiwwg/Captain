package slf.captain.protocol.messages;

import slf.captain.protocol.MessageEnums;

public class PongMessage {

    private String pingSeqId;

    private final String msgType = MessageEnums.PONG.name();

    private CaptainProtocolHeader header;

    public PongMessage() {
    }

    public String getPingSeqId() {
        return pingSeqId;
    }

    public void setPingSeqId(String pingSeqId) {
        this.pingSeqId = pingSeqId;
    }

    public String getMsgType() {
        return msgType;
    }

    public CaptainProtocolHeader getHeader() {
        return header;
    }

    public void setHeader(CaptainProtocolHeader header) {
        this.header = header;
    }
}
