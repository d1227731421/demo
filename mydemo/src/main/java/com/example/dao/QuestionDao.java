package com.example.dao;

import com.example.pojo.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface QuestionDao extends JpaRepository<Question,Integer>, JpaSpecificationExecutor<Question> {
}
