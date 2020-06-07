package com.example.controller;

import com.example.pojo.Member;
import com.example.pojo.User;
import com.example.service.MemberService;
import com.example.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author dsr
 * @date 2020-05-06
 **/
@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result queryMember(@PathVariable  Integer id){
        return memberService.queryMember(id);
    }
    @RequestMapping(value = "/add/{id}",method = RequestMethod.POST)
    public Result addMember(@PathVariable Integer id, @RequestBody Member member){
        return memberService.addMember(id,member);
    }
    @RequestMapping(value = "/add/{id}",method = RequestMethod.PUT)
    public Result updateMember(@PathVariable Integer id, @RequestBody Member member){
        return memberService.updateMember(id,member);
    }
    @RequestMapping(value = "/del/{id}",method = RequestMethod.DELETE)
    public Result deleteMember(@PathVariable Integer id){
        return memberService.deleteMember(id);
    }
}
