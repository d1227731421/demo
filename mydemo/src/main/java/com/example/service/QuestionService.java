package com.example.service;

import com.example.dao.QuestionDao;
import com.example.pojo.Question;
import com.example.util.Result;
import com.example.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dsr
 * @date 2020-05-10
 **/
@Service
public class QuestionService {
    @Autowired
    private QuestionDao questionDao;

    public Result saveQuestion(Question question){
        questionDao.save(question);
        return new Result(true, StatusCode.OK,"提交成功");
    }
}

