package cc.kevinlu.springbootbloomfilter.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lua")
public class LuaController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/search/{id}")
    public Boolean search(@PathVariable(name = "id") String id) {
        String script = "return redis.call('BF.EXISTS',KEYS[1],ARGV[1])";
        DefaultRedisScript<Boolean> redisScript = new DefaultRedisScript<>(script, Boolean.class);
        Boolean r = redisTemplate.execute(redisScript, Collections.singletonList("bf2"), "cc" + id);
        return r;
    }

    @GetMapping("/add/{id}")
    public Boolean add(@PathVariable(name = "id") String id) {
        String script = "return redis.call('bf.add',KEYS[1],ARGV[1])";
        RedisScript<Boolean> redisScript = new DefaultRedisScript<>(script, Boolean.class);
        Boolean r = redisTemplate.execute(redisScript, Collections.singletonList("bf2"), "cc" + id);
        return r;
    }

    /*public static void main(String[] args) throws Exception {
    
        FooBar fooBar = new FooBar(3);
        Thread t1 = new Thread(() -> {
            try {
                fooBar.foo();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "cc1");
        Thread t2 = new Thread(() -> {
            try {
                fooBar.bar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "cc2");
    
        t2.start();
        t1.start();
    
    }*/

}

/*
class FooBar {
    private int       n;

    private Lock      lock = new ReentrantLock();
    private Condition cf   = lock.newCondition();
    private Condition cb   = lock.newCondition();

    public FooBar(int n) {
        this.n = n;
    }

    public void foo() throws InterruptedException {

        lock.lock();
        for (int i = 0; i < n; i++) {
            System.out.print("foo");
            cb.signal();
            cf.await();
        }
        lock.unlock();
    }

    public void bar() throws InterruptedException {

        lock.lock();
        for (int i = 0; i < n; i++) {
            System.out.print("bar");
            cf.signal();
            cb.await();
        }
        lock.unlock();
    }
}*/

/*

class FooBar {
    private int       n;

    private Lock      lock      = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean   flag      = false;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo() throws InterruptedException {

        lock.lock();
        for (int i = 0; i < n; i++) {
            if (flag) {
                condition.await();
            }
            System.out.print("foo");
            flag = true;
            condition.signal();
        }
        lock.unlock();
    }

    public void bar() throws InterruptedException {

        lock.lock();
        for (int i = 0; i < n; i++) {
            if (!flag) {
                condition.await();
            }
            System.out.print("bar");
            flag = false;
            condition.signal();
        }
        lock.unlock();
    }
}
*/

/*

class FooBar {
    private int       n;

    private Lock      lock      = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean   flag      = false;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo() throws InterruptedException {

        lock.lock();
        for (int i = 0; i < n; i++) {
            while (flag) {
                condition.await();
            }
            System.out.println("foo");
            flag = true;
            condition.signal();
        }
        lock.unlock();
    }

    public void bar() throws InterruptedException {

        lock.lock();
        for (int i = 0; i < n; i++) {
            while (!flag) {
                condition.await();
            }
            System.out.println("bar");
            flag = false;
            condition.signal();
        }
        lock.unlock();
    }
}
*/
