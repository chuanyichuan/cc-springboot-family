package cc.kevinlu.knife4j.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.kevinlu.knife4j.pojo.IndexParams;
import cc.kevinlu.knife4j.pojo.IndexResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Api测试Tag")
public class ApiController {

    @GetMapping(value = "/index")
    @ApiOperation(value = "首页测试接口", httpMethod = "GET")
    public IndexResult index(IndexParams params) {
        return IndexResult.builder().name(params.getName()).build();
    }

}
