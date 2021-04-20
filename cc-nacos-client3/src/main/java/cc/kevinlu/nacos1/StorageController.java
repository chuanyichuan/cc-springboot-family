package cc.kevinlu.nacos1;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.kevinlu.nacos1.service.StorageService;

@RequestMapping("/storage")
@RestController
public class StorageController {

    @Resource
    private StorageService storageService;

    @GetMapping("/des")
    public boolean storage(String commodityCode, int count) {
        return storageService.storage(commodityCode, count);
    }

    @GetMapping("/price")
    public BigDecimal price(String commodityCode) {
        return storageService.price(commodityCode);
    }

}
