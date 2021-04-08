package cc.kevinlu.tomcat.nio;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import cc.kevinlu.tomcat.nio.base.CCRequest;
import cc.kevinlu.tomcat.nio.base.CCResponse;
import cc.kevinlu.tomcat.nio.servlet.CCBaseServlet;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class TomcatStarter {

    private int                        port           = 8080;

    private Map<String, CCBaseServlet> servletMapping = new HashMap<>();

    public static void main(String[] args) {
        new TomcatStarter().start();
    }

    //Tomcat的启动入口
    private void start0() {
        //1、加载web.properties文件，解析配置
        init();

        //Boss线程
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //Worker线程
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        //2、创建Netty服务端对象
        ServerBootstrap server = new ServerBootstrap();

        try {
            //3、配置服务端参数
            server.group(bossGroup, workerGroup)
                    //配置主线程的处理逻辑
                    .channel(NioServerSocketChannel.class)
                    //子线程的回调处理，Handler
                    .childHandler(new ChannelInitializer() {
                        @Override
                        protected void initChannel(Channel client) throws Exception {
                            //处理回调的逻辑

                            //链式编程，责任链模式

                            //处理响应结果的封装
                            client.pipeline().addLast(new HttpResponseEncoder());
                            //用户请求过来，要解码
                            client.pipeline().addLast(new HttpRequestDecoder());
                            //用户自己的业务逻辑
                            client.pipeline().addLast(new CcTomcatHandler());

                        }
                    })
                    //配置主线程分配的最大线程数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //保持长连接
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            //启动服务
            ChannelFuture f = server.bind(this.port).sync();

            System.out.println("GP Tomcat 已启动，监听端口是: " + this.port);

            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

    private void start() {
        EventLoopGroup masterGroup = null, workerGroup = null;
        try {
            // 加载配置文件
            init();

            masterGroup = new NioEventLoopGroup();

            workerGroup = new NioEventLoopGroup();

            ServerBootstrap server = new ServerBootstrap();

            server.group(masterGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {

                            ch.pipeline().addLast(new HttpResponseEncoder());
                            ch.pipeline().addLast(new HttpRequestDecoder());
                            ch.pipeline().addLast(new CcTomcatHandler());
                        }
                    }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = server.bind(this.port).sync();
            System.out.println("NIO监听端口已开, port = [" + this.port + "]");

            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            masterGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private void init() {
        try {
            String path = this.getClass().getResource("/").getPath();
            FileInputStream fis = new FileInputStream(new File(path + "/web-nio.properties"));

            Properties prop = new Properties();
            prop.load(fis);

            for (Object key : prop.keySet()) {
                String urlKey = String.valueOf(key);
                if (urlKey.endsWith(".url")) {
                    // 查找对应的className
                    String url = prop.getProperty(String.valueOf(key));
                    String className = prop.getProperty(urlKey.replaceAll("\\.url$", "") + ".className");
                    servletMapping.put(url, (CCBaseServlet) Class.forName(className).newInstance());
                }
            }
            if (prop.containsKey("server.port")) {
                this.port = Integer.parseInt(prop.getProperty("server.port"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class CcTomcatHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

            if (msg instanceof HttpRequest) {
                HttpRequest req = (HttpRequest) msg;

                CCRequest request = new CCRequest(ctx, req);
                CCResponse response = new CCResponse(ctx, req);

                String url = request.getUrl();

                if (servletMapping.containsKey(url)) {
                    servletMapping.get(url).service(request, response);
                } else {
                    response.write("404 - NOT FOUND!!");
                }
            }

        }
    }
}
