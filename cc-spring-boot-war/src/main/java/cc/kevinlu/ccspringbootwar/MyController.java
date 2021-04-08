package cc.kevinlu.ccspringbootwar;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MyController {

    private volatile AtomicLong counter = new AtomicLong(1);

    @GetMapping("/cc")
    public String test1() {
        log.info("cc: " + counter.getAndIncrement());
        return "cc";
    }

}
