package cn.codeyang.yang.framework.transmission.http;

public class InvalidResponseStatus extends Throwable {

    private final int statusCode;

    public InvalidResponseStatus(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
