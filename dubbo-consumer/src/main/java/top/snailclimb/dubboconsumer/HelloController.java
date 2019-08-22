package top.snailclimb.dubboconsumer;

import com.alibaba.dubbo.config.annotation.Reference;
import model.Test;
import model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.snailclimb.service.HelloService;

import java.util.List;

@RestController
public class HelloController {

    /**
     * @Reference注解
     * 是dubbo特有的注解，
     * 意味着注入zookeeper里面相应的服务
     */
    @Reference
    private HelloService helloService;


    @RequestMapping("/hello")
    public String hello() {
        String hello = helloService.sayHello("world");
        System.out.println(helloService.sayHello("SnailClimb"));
        return hello;
    }

    @RequestMapping("/find")
    public List<User> find() {
        List<User> users = helloService.find();
        return users;
    }

    @RequestMapping("/test")
    public List<Test> tests() {
        List<Test> tests = helloService.selectAll();
        return tests;
    }

}