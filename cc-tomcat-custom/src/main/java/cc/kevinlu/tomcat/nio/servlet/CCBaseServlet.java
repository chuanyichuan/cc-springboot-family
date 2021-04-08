package cc.kevinlu.tomcat.nio.servlet;

import cc.kevinlu.tomcat.nio.base.CCRequest;
import cc.kevinlu.tomcat.nio.base.CCResponse;

public abstract class CCBaseServlet {

    public void service(CCRequest request, CCResponse response) {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            doGet(request, response);
        } else {
            doPost(request, response);
        }
    }

    public abstract void doGet(CCRequest request, CCResponse response);

    public abstract void doPost(CCRequest request, CCResponse response);

}
