package com.example.dao;

import com.example.pojo.Reminder;
import com.example.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author dsr
 * @date 2020-05-06
 **/

public interface ReminderDao extends JpaRepository<Reminder,Integer>, JpaSpecificationExecutor<Reminder> {
    List<Reminder> findByUser(User user);
}
