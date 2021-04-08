package cc.kevinlu.tomcat.bio.process;

import cc.kevinlu.tomcat.bio.base.CCRequest;
import cc.kevinlu.tomcat.bio.base.CCResponse;
import cc.kevinlu.tomcat.bio.servlet.CCBaseServlet;

public class SecondServlet extends CCBaseServlet {
    @Override
    public void doGet(CCRequest request, CCResponse response) {
        doPost(request, response);
    }

    @Override
    public void doPost(CCRequest request, CCResponse response) {
        response.write("second servlet is called by bio!");
    }
}
