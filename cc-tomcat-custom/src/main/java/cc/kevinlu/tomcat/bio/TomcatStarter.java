package cc.kevinlu.tomcat.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import cc.kevinlu.tomcat.bio.base.CCRequest;
import cc.kevinlu.tomcat.bio.base.CCResponse;
import cc.kevinlu.tomcat.bio.servlet.CCBaseServlet;

public class TomcatStarter {

    private ServerSocket               socket;

    private int                        port           = 8080;

    private Map<String, CCBaseServlet> servletMapping = new HashMap<>();

    public static void main(String[] args) {
        new TomcatStarter().start();
    }

    private void start() {
        try {
            // 加载配置文件
            init();
            // 启动socket
            socket = new ServerSocket(port);
            System.out.println("监听端口已开, port = [" + this.port + "]");
            while (true) {
                Socket client = socket.accept();
                // 接收请求
                process(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void process(Socket client) throws Exception {
        InputStream is = client.getInputStream();
        OutputStream os = client.getOutputStream();
        CCRequest request = new CCRequest(is);
        CCResponse response = new CCResponse(os);

        String url = request.getUrl();
        url = url.split("\\?")[0];
        // 转发请求
        if (servletMapping.containsKey(url)) {
            servletMapping.get(url).service(request, response);
        } else {
            os.write("404 - NOT FOUND!!".getBytes(StandardCharsets.UTF_8));
        }

        os.flush();
        os.close();
        is.close();
        client.close();
    }

    private void init() throws Exception {
        String path = this.getClass().getResource("/").getPath();
        FileInputStream fis = new FileInputStream(new File(path + "/web-bio.properties"));

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
    }

}
