package com.example.dao;

import com.example.pojo.Member;
import com.example.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MemberDao extends JpaRepository<Member,Integer>, JpaSpecificationExecutor<Member> {
    List<Member> findByUser(User user);
}
