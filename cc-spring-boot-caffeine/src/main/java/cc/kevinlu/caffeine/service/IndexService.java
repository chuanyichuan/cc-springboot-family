package cc.kevinlu.caffeine.service;

import java.util.List;

import cc.kevinlu.caffeine.pojo.IndexVO;

public interface IndexService {
    List<IndexVO> queryList(String name);
}
