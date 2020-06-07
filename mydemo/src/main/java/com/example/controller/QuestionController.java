package com.example.controller;

import com.example.pojo.Question;
import com.example.service.QuestionService;
import com.example.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dsr
 * @date 2020-05-10
 **/
@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result saveQuestion(@RequestBody Question question){
        return questionService.saveQuestion(question);
    }
}
