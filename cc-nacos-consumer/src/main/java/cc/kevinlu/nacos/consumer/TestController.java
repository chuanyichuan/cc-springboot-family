package cc.kevinlu.nacos.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/c_nacos")
@RestController
public class TestController {

    @Autowired
    private NacosFeign nacosFeign;

    @GetMapping("/info")
    public String info() {
        return nacosFeign.info();
    }

}
