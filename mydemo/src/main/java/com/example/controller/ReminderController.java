package com.example.controller;

import com.example.pojo.Reminder;
import com.example.service.ReminderService;
import com.example.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author dsr
 * @date 2020-05-07
 **/
@RestController
@RequestMapping("/remind")
public class ReminderController {
    @Autowired
    private ReminderService reminderService;
    @RequestMapping(value = "/{userId}",method = RequestMethod.POST)
    public Result addReminder(@PathVariable Integer userId, @RequestBody Reminder reminder){
        return reminderService.addReminder(userId,reminder);
    }
    @RequestMapping(value = "/{userId}",method = RequestMethod.PUT)
    public Result updateReminder(@PathVariable Integer userId, @RequestBody Reminder reminder){
        return reminderService.updateReminder(userId,reminder);
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result delReminder( @PathVariable Integer id){
        return reminderService.delReminder(id);
    }
    @RequestMapping(value = "/{userId}",method = RequestMethod.GET)
    public Result queryReminder(@PathVariable Integer userId){
        return reminderService.queryReminder(userId);
    }
}
