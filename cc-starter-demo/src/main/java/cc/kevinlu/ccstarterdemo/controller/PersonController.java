package cc.kevinlu.ccstarterdemo.controller;

import java.util.Random;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.kevinlu.ccstarterdemo.PersonDTO;
import cc.kevinlu.threadpool.monitor.adapter.AdapterFactory;
import cc.kevinlu.threadpool.monitor.adapter.ThreadPoolTaskLoggerAdapter;
import cc.kevinlu.threadpool.monitor.executor.ThreadPoolExecutorWithMonitor;
import cc.kevinlu.threadpool.monitor.manager.ThreadPoolMonitorManager;

@RestController
public class PersonController {

    //    @Autowired
    //    private CcService ccService;

    //    @GetMapping("/info")
    //    public void info() {
    //        System.out.println(ccService.info());
    //    }

    @GetMapping("/info")
    public PersonDTO person() {
        PersonDTO d = new PersonDTO();
        d.setId(12L);
        d.setName("cc");
        d.setAge(18);
        d.setGender("male");
        d.setS(123L);
        return d;
    }

    @Resource
    private ThreadPoolMonitorManager manager;

    @GetMapping("/exe")
    public String exe() {
        ThreadPoolExecutorWithMonitor th = manager.getThreadPoolExecutor("first-monitor-pool");
        for (int i = 0; i < 100; i++) {
            th.execute(() -> {
                try {
                    Thread.sleep(new Random().nextInt(40000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        return "ok";
    }

    @GetMapping("/exe1")
    public String exe1() {
        ThreadPoolTaskLoggerAdapter comlog = AdapterFactory.getCommonLog();
        for (int i = 0; i < 100; i++) {
            comlog.execute("second-monitor-pool", () -> {
                System.out.println(System.currentTimeMillis());
            });
        }
        comlog.clearLog();
        return "ok";
    }

}
