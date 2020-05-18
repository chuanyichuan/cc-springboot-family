package cc.kevinlu.image.extractor.events;

import org.springframework.context.ApplicationEvent;

public class PlayEvent extends ApplicationEvent {

    private Boolean completed;

    public PlayEvent(Boolean source) {
        super(source);
        this.completed = source;
    }

    public void prepare() {
        System.out.println("拾掇玩耍装备!");
    }

    public Boolean getCompleted() {
        return completed;
    }
}
