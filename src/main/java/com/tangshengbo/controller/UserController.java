package com.tangshengbo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangshengbo.model.Model;
import com.tangshengbo.model.User;
import com.tangshengbo.repository.UserRepository;
import com.tangshengbo.service.UserService;
import com.tangshengbo.util.ResponseMessage;
import com.tangshengbo.util.ResponseGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2016/12/20.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private Model model;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/name")
    public String getUserName() {
        return model.toString();
    }

    @RequestMapping("/users/{username}")
    public String userProfile(@PathVariable("username") String username) {
        return String.format("user %s", username);
    }

    @RequestMapping("/posts/{id}")
    public String post(@PathVariable("id") int id) {
        return String.format("post %d", id);
    }

    @RequestMapping("/getuser/{id}")
    public User getUser(@PathVariable("id") int id) {
        logger.info("UserController.getUser param:{}", id);
        //mybatis xml
        //User user = userService.selectUserById(id);
        //mybatis annotation
        User user = userService.findById(id);
        logger.info("UserController.getUser result:{}", user.toString());
        return user;
    }

    @RequestMapping("/sort")
    public List<User> sort() {
        List<User> users = userRepository.findAll(new Sort(Sort.Direction.DESC, "id"));
        return users;
    }

    //http://localhost:8090/user/page/0/5 第一个参数表示页数，从0开始计，第二个参数表示每页的数据量
    @RequestMapping("/page/{page}/{size}")
    public Page page(@PathVariable("page") int page, @PathVariable("size") int size) {
        Page users = userRepository.findAll(new PageRequest(page, size));
        return users;
    }

    @RequestMapping("/first/{username}")
    public List<User> findFirst10(@PathVariable("username") String username) {
        List<User> users = userRepository.findTop5Byusername(username);
        return users;
    }

    @RequestMapping("/pagehelper/{page}/{size}")
    public ResponseMessage listUserByPage(@PathVariable("page") int page, @PathVariable("size") int size) {
        PageHelper.startPage(page, size);
        List<User> users = userService.findAll();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return ResponseGenerator.genSuccessResult(pageInfo);
    }

    @RequestMapping("/save-batch/{count}")
    public void saveBatch(@PathVariable("count") int count) {
        userService.saveBatchUser(count);
    }

    @RequestMapping("/update-batch/{count}")
    public void updateBatch(@PathVariable("count") int count) {
        userService.updateBatchUser(count);
    }

    @RequestMapping("/update/{count}")
    public void update(@PathVariable("count") int count) {
        userService.updateUser(count);
    }
}
