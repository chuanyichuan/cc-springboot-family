package com.example.image.extractor.events;

import java.util.Objects;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EatEventListener implements ApplicationListener<EatEvent>, ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void onApplicationEvent(EatEvent event) {
        if (Objects.isNull(event)) {
            System.out.println("暂无事件!");
            return;
        }
        if (event.getFinishEat()) {
            event.callGirlFriend();
            System.out.println("收拾完毕");
            event.callBoyFriend();
            System.out.println("走起~");
            PlayEvent playEvent = new PlayEvent(true);
            this.applicationEventPublisher.publishEvent(playEvent);
        } else {
            System.out.println("还未吃完饭");
        }
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
