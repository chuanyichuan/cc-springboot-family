package cc.kevinlu.tomcat.bio.base;

import java.io.InputStream;

public class CCRequest {

    private String method;

    private String url;

    private String body;

    public CCRequest(InputStream is) {
        try {
            byte[] buff = new byte[1024];
            int len;
            if ((len = is.read(buff)) > 0) {
                this.setBody(new String(buff, 0, len));
            }
            String[] header = this.body.split("\\n")[0].split("\\s");
            this.setMethod(header[0]);
            this.setUrl(header[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
