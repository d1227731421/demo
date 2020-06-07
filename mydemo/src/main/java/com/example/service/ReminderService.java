package com.example.service;

import com.example.dao.ReminderDao;
import com.example.pojo.Reminder;
import com.example.pojo.User;
import com.example.util.Result;
import com.example.util.StatusCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dsr
 * @date 2020-05-07
 **/
@Service
public class ReminderService {
    @Autowired
    private ReminderDao reminderDao;
    public Result addReminder(Integer userId, Reminder reminder){
        reminder.getUser().setId(userId);
        reminderDao.save(reminder);
        return new Result(true, StatusCode.OK,"添加成功");
    }
    public Result updateReminder(Integer userId, Reminder reminder){
        reminder.getUser().setId(userId);
        reminderDao.save(reminder);
        return new Result(true, StatusCode.OK,"修改成功");
    }
    public Result delReminder(Integer id){
        reminderDao.deleteById(id);
        return new Result(true, StatusCode.OK,"删除成功");
    }
    public Result queryReminder(Integer userId){
        User user = new User();
        user.setId(userId);
        return new Result(true, StatusCode.OK,"查询成功",reminderDao.findByUser(user));
    }
}
