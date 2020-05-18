package cc.kevinlu.ccspringbootwar;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/cc")
    public String test1() {
        return "cc";
    }

}
