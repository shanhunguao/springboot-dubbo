package top.snailclimb.service;

import model.Test;
import model.User;

import java.util.List;

public interface HelloService {
     String sayHello(String name);
     List<User> find();
     List<Test> selectAll();
}
