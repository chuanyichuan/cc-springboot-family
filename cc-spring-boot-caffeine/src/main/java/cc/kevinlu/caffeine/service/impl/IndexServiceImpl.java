package cc.kevinlu.caffeine.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cc.kevinlu.caffeine.pojo.IndexVO;
import cc.kevinlu.caffeine.service.IndexService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IndexServiceImpl implements IndexService {

    /**
     * 模拟db
     */
    private List<IndexVO> dbData = new ArrayList() {
        {
            add(new IndexVO("cc1", 1));
            add(new IndexVO("cc2", 2));
            add(new IndexVO("cc3", 3));
            add(new IndexVO("cc4", 4));
            add(new IndexVO("cc5", 5));
            add(new IndexVO("cc6", 6));
            add(new IndexVO("cc7", 7));
        }
    };

    @Override
    @Cacheable(value = "index_list", key = "#name")
    public List<IndexVO> queryList(String name) {
        log.info("query data within name = [{}]", name);
        return dbData.stream().filter(v -> v.getName().contains(name)).collect(Collectors.toList());
    }
}
