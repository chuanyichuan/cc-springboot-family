package cc.kevinlu.tomcat.nio.base;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

public class CCResponse {

    private ChannelHandlerContext ctx;
    private HttpRequest           req;

    public CCResponse(ChannelHandlerContext ctx, HttpRequest req) {
        this.ctx = ctx;
        this.req = req;
    }

    public void write(String s) {
        if (s == null || s.length() == 0) {
            return;
        }

        try {
            FullHttpResponse response = new DefaultFullHttpResponse(
                    //设置HTTP版本号为1.1
                    HttpVersion.HTTP_1_1,
                    //设置返回HTTP状态码
                    HttpResponseStatus.OK,
                    //统一输出格式为UTF-8
                    Unpooled.wrappedBuffer(s.getBytes("UTF-8")));

            response.headers().set("Content-Type", "text/html");

            ctx.write(response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ctx.flush();
            ctx.close();
        }

    }

}
