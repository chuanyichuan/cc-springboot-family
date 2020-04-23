package cc.kevinlu.deferred.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TimeServiceImpl implements TimeService {

    @Override
    public String schedule() {
        try {
            Thread.sleep(5000);
            log.info("time schedule executed");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "service finish!";
    }
}
