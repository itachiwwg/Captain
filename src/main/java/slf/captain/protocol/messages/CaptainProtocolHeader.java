package slf.captain.protocol.messages;

public class CaptainProtocolHeader {
    // len = 4;
    private int magiCode;

    // len = 4
    private int version;

    // len = 36
    private String sessionId;

    // len = 4
    private int contentLength;

    public CaptainProtocolHeader(){

    }

    public CaptainProtocolHeader(int m, int v, String s, int c) {
        this.magiCode = m;
        this.version = v;
        this.sessionId = s;
        this.contentLength = c;
    }

    public int getMagiCode() {
        return magiCode;
    }

    public void setMagiCode(int magiCode) {
        this.magiCode = magiCode;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }
}
