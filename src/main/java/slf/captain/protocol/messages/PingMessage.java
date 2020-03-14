package slf.captain.protocol.messages;

import slf.captain.protocol.MessageEnums;

public class PingMessage {

    private CaptainProtocolHeader header;

    private String msgType = MessageEnums.PING.name();

    private String seqId;

    public PingMessage(){

    }

    public CaptainProtocolHeader getHeader() {
        return header;
    }

    public void setHeader(CaptainProtocolHeader header) {
        this.header = header;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getSeqId() {
        return seqId;
    }

    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }
}
