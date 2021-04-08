package cc.kevinlu.caffeine.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cc.kevinlu.caffeine.pojo.IndexVO;
import cc.kevinlu.caffeine.service.IndexService;

@RestController
public class IndexController {

    @Resource
    private IndexService indexService;

    @GetMapping(value = "/list/{name}")
    public List<IndexVO> queryList(@PathVariable(name = "name") String name) {
        return indexService.queryList(name);
    }

}
