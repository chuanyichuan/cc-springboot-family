package cc.kevinlu.tomcat.nio.base;

import java.util.List;
import java.util.Map;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

public class CCRequest {

    private String                method;

    private String                url;

    private String                body;

    private ChannelHandlerContext ctx;
    private HttpRequest           req;

    public CCRequest(ChannelHandlerContext ctx, HttpRequest req) {
        this.ctx = ctx;
        this.req = req;
        this.setUrl(req.uri());
        this.setMethod(req.method().name());
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

    public Map<String, List<String>> getParameters() {
        QueryStringDecoder decoder = new QueryStringDecoder(this.url);
        return decoder.parameters();
    }

    public String getParameter(String name) {
        Map<String, List<String>> params = getParameters();
        List<String> values = params.get(name);
        if (values == null || values.isEmpty()) {
            return null;
        }
        return values.get(0);
    }

}
