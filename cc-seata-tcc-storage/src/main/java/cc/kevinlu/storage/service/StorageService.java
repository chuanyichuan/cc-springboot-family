package cc.kevinlu.storage.service;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import cc.kevinlu.storage.action.StorageTccAction;
import cc.kevinlu.storage.data.mapper.StorageMapper;
import cc.kevinlu.storage.data.model.StorageDO;
import cc.kevinlu.storage.data.model.StorageDOExample;
import io.seata.spring.annotation.GlobalTransactional;

@Service
public class StorageService {

    @Resource
    private StorageMapper    storageMapper;

    @Resource
    private StorageTccAction storageTccAction;

    @Transactional(rollbackFor = Exception.class)
    public boolean storage(String commodityCode, int count) {
        StorageDOExample example = new StorageDOExample();
        example.createCriteria().andCommodityCodeEqualTo(commodityCode);
        List<StorageDO> sList = storageMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(sList)) {
            throw new RuntimeException("commodity not exist!");
        }
        StorageDO storage = sList.get(0);
        if (count > storage.getCount()) {
            throw new RuntimeException("insufficient inventory!");
        }
        storage.setCount(storage.getCount() - count);
        return storageMapper.updateByPrimaryKeySelective(storage) == 1;
    }

    public BigDecimal price(String commodityCode) {
        StorageDOExample example = new StorageDOExample();
        example.createCriteria().andCommodityCodeEqualTo(commodityCode);
        List<StorageDO> sList = storageMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(sList)) {
            throw new RuntimeException("commodity not exist!");
        }
        return sList.get(0).getPrice();
    }

    @GlobalTransactional
    public boolean storageTcc(String commodityCode, int count) {
        if (!storageTccAction.prepare(null, commodityCode, count)) {
            throw new RuntimeException("decr storage error!");
        }
        return true;
    }

}
