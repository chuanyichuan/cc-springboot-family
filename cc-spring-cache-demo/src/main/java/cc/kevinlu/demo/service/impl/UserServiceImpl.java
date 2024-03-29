package cc.kevinlu.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cc.kevinlu.demo.entity.User;
import cc.kevinlu.demo.repository.UserRepository;
import cc.kevinlu.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    //查询数据库后 数据添加到缓存
    @Override
    @Cacheable(cacheNames = "cacheManager", key = "'USER:'+#id", unless = "#result == null")
    public User getUser(Integer id) {
        return repository.getUser(id);
    }

    //清除一条缓存，key为要清空的数据
    @Override
    @CacheEvict(cacheNames = "cacheManager", key = "'USER:'+#id")
    public void deleteUser(Integer id) {
        repository.deleteById(id);
    }

    //修改数据后更新缓存
    @Override
    @CachePut(cacheNames = "cacheManager", key = "'USER:'+#updateUser.id", unless = "#result == null")
    public User updateUser(User updateUser) {
        return repository.save(updateUser);
    }

}
