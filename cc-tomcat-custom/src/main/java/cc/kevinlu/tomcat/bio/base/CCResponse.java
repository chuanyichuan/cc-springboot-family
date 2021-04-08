package cc.kevinlu.tomcat.bio.base;

import java.io.IOException;
import java.io.OutputStream;

public class CCResponse {

    private OutputStream out;

    public CCResponse(OutputStream os) {
        this.out = os;
    }

    public void write(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\n").append("Content-Type: text/html;\n").append("\r\n").append(s);
        try {
            this.out.write(sb.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
