package com.example.service;

import com.example.dao.MemberDao;
import com.example.pojo.Member;
import com.example.pojo.User;
import com.example.util.Result;
import com.example.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dsr
 * @date 2020-05-06
 **/
@Service
public class MemberService {
    @Autowired
    private MemberDao memberDao;

    public Result queryMember(Integer id){
        User user = new User();
        user.setId(id);
        List<Member> byUser = memberDao.findByUser(user);
        return new Result(true, StatusCode.OK,"查询成功",byUser);
    }

    public Result addMember(Integer id, Member member) {
        member.getUser().setId(id);
        memberDao.save(member);
        return new Result(true, StatusCode.OK,"添加成功",null );
    }

    public Result updateMember(Integer id, Member member) {
        member.getUser().setId(id);
        memberDao.save(member);
        return new Result(true, StatusCode.OK,"修改成功" );
    }

    public Result deleteMember(Integer id) {
        memberDao.deleteById(id);
          return new Result(true, StatusCode.OK,"删除成功" );
    }
}

