package cc.kevinlu.image.extractor.events;

import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlayEventListener implements ApplicationListener<PlayEvent> {

    @Resource
    private PlayService playService;

    @Override
    public void onApplicationEvent(PlayEvent event) {
        if (Objects.isNull(event)) {
            return;
        }
        if (event.getCompleted()) {
            event.prepare();
            playService.startPlay();
        }
    }
}
