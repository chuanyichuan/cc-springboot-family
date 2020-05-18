package cc.kevinlu.image.extractor.events;

import org.springframework.stereotype.Service;

@Service
public class PlayServiceImpl implements PlayService {

    @Override
    public void startPlay() {
        System.out.println("开始玩耍,具体玩耍内容是打乒乓球!");
    }
}
