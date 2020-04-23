package cc.kevinlu.deferred.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import cc.kevinlu.deferred.service.TimeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RequestController {

    @Resource
    private TimeService timeService;

    @GetMapping("/block")
    public String block() {
        log.info("block start!");
        String r = timeService.schedule();
        log.info("block finish!");
        return r;
    }

    @GetMapping("/callable")
    public Callable<String> callable() {
        log.info("callable start!");
        Callable<String> r = timeService::schedule;
        log.info("callable finish!");
        return r;
    }

    @GetMapping("/deferred")
    public DeferredResult<String> deferred() {
        log.info("deferred start!");
        DeferredResult<String> deferredResult = new DeferredResult<>();
        CompletableFuture.supplyAsync(timeService::schedule).whenCompleteAsync((r, e) -> deferredResult.setResult(r));
        Callable<String> r = timeService::schedule;
        log.info("deferred finish!");
        return deferredResult;
    }

}
