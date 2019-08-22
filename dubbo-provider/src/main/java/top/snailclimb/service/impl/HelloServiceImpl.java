package top.snailclimb.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import mapper.TestMapper;
import mapper.UserMapper;
import model.Test;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.snailclimb.service.HelloService;

import javax.annotation.Resource;
import java.util.List;

@Component
@Service
public class HelloServiceImpl implements HelloService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private TestMapper testMapper;

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

    public List<User> find(){
        List<User> users = userMapper.queryUser();
        return users;
    }

    @Override
    public List<Test> selectAll() {
        return testMapper.selectAll();
    }


}