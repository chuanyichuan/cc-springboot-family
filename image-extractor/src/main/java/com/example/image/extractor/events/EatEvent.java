package com.example.image.extractor.events;

import org.springframework.context.ApplicationEvent;

public class EatEvent extends ApplicationEvent {

    private Boolean finishEat;

    public EatEvent(Boolean source) {
        super(source);
        this.finishEat = source;
    }

    public void callGirlFriend() {
        System.out.println("吃完饭了，呼叫女朋友收拾！");
    }

    public void callBoyFriend() {
        System.out.println("吃完饭了，呼叫兄弟一起出去玩！");
    }

    public Boolean getFinishEat() {
        return finishEat;
    }
}
