package com.example.controller;

import com.example.pojo.User;
import com.example.service.UserService;
import com.example.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author dsr
 * @date 2020-05-06
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(  @RequestBody User user){
        return userService.login(user.getPhone(),user.getPassword());
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Result register(@RequestBody User user){
        return userService.register(user);
    }
    @RequestMapping(value = "/{userId}",method = RequestMethod.GET)
    public  Result queryMedicineName(@PathVariable Integer userId){
        return userService.queryMedicineName(userId);
    }
    @RequestMapping(value = "/reduceMedicine")
    public Result reduceMedicine(String userId,String number,String name){
        return userService.reduceMedicine(Integer.parseInt(userId),Integer.parseInt(number),name);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Result update(@RequestBody User user){
        return userService.update(user);
    }
}
