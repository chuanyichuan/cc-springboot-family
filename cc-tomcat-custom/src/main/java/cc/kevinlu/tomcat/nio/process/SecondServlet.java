package cc.kevinlu.tomcat.nio.process;

import cc.kevinlu.tomcat.nio.base.CCRequest;
import cc.kevinlu.tomcat.nio.base.CCResponse;
import cc.kevinlu.tomcat.nio.servlet.CCBaseServlet;

public class SecondServlet extends CCBaseServlet {
    @Override
    public void doGet(CCRequest request, CCResponse response) {
        doPost(request, response);
    }

    @Override
    public void doPost(CCRequest request, CCResponse response) {
        response.write("second servlet is called by nio!");
    }
}
