package cc.kevinlu.canal.entry;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import cc.kevinlu.canal.pojo.Manager;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

@Component
@CanalTable("msg_manager")
public class ManagerEntryHandler implements EntryHandler<Manager> {

    @Override
    public void insert(Manager manager) {
        System.out.println("---insert---: " + JSONObject.toJSONString(manager));
    }

    @Override
    public void update(Manager before, Manager after) {
        System.out.println("---update before---: " + JSONObject.toJSONString(before));
        System.out.println("---update after---: " + JSONObject.toJSONString(after));
    }

    @Override
    public void delete(Manager manager) {
        System.out.println("---delete---: " + JSONObject.toJSONString(manager));
    }

}
