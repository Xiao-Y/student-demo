package org.billow.business.controller;

import org.billow.model.User;
import org.billow.service.UserService;
import org.billow.tools.PageableTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author abel
 * @ClassName UserController
 * @date 2016年11月10日
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/findByName", method = RequestMethod.GET)
    public User findByName(User user) {
        return userService.findByName(user.getUsername());
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Page<User> findAll(User user) {
        Page<User> datas = userService.findAll(PageableTools.basicPage(0));
        print(datas);
        System.out.println(datas.getContent());
        return datas;
    }

    @RequestMapping(value = "/findByName2", method = RequestMethod.GET)
    public ResponseEntity<Object> findByName2(User user) {
        return new ResponseEntity<>(userService.findByName(user.getUsername()), HttpStatus.OK);
    }

    private void print(Page<User> datas) {
        System.out.println("总条数：" + datas.getTotalElements());
        System.out.println("总页数：" + datas.getTotalPages());
        for (User u : datas) {
            System.out.println(u.getId() + "====" + u.getUsername());
        }
    }
}