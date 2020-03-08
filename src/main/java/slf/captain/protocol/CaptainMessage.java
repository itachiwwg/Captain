package slf.captain.protocol;

import slf.captain.protocol.messages.CaptainProtocolHeader;

/**
 *
 *
 *
 */
public class CaptainMessage {

    private CaptainProtocolHeader captainProtocolHeader;

    private String content;

    public CaptainMessage() {

    }

    public CaptainMessage(CaptainProtocolHeader header, String content) {
        this.captainProtocolHeader = header;
        this.content = content;
    }

    public CaptainProtocolHeader getCaptainProtocolHeader() {
        return captainProtocolHeader;
    }

    public void setCaptainProtocolHeader(CaptainProtocolHeader captainProtocolHeader) {
        this.captainProtocolHeader = captainProtocolHeader;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format("[magic_code = %d, version=%d, sessionId=%s, contentLength=%d, content=%s]",
                captainProtocolHeader.getMagiCode(), captainProtocolHeader.getVersion(), captainProtocolHeader.getSessionId(),
                captainProtocolHeader.getContentLength(), this.content);
    }
}
