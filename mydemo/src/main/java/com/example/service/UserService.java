package com.example.service;

import com.example.dao.UserDao;
import com.example.pojo.Medicine;
import com.example.pojo.MedicineChest;
import com.example.pojo.Member;
import com.example.pojo.User;
import com.example.util.Result;
import com.example.util.StatusCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * @author dsr
 * @date 2020-05-06
 **/
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public Result login(String phone, String passwd) {
        if(StringUtils.isEmpty(phone))
            return new Result(false, StatusCode.ERROR,"账号不能为空",null);
        if(StringUtils.isEmpty(passwd))
            return new Result(false, StatusCode.ERROR,"密码不能为空",null);

        User user = userDao.findByPhoneAndPassword(phone, passwd);
        if(user==null)
            return new Result(false, StatusCode.ERROR,"账号或密码错误",null);
        return new Result(true, StatusCode.OK,"登录成功",user);
    }

    public Result register(User user){
        if(userDao.existsByPhone(user.getPhone()))
            return new Result(false, StatusCode.ERROR,"该号码已经被注册",null);
        userDao.save(user);
        return new Result(true, StatusCode.OK,"注册",null);
    }
    public Result queryMedicineName(Integer userId){
        Optional<User> byId = userDao.findById(userId);
        if(byId.isPresent()){
            User user = byId.get();
            List<Medicine> medicineList = user.getMedicineList();
            List<String> name = medicineList.stream().map(medicine -> medicine.getMedicineName()).collect(Collectors.toList());
            List<Member> memberList = user.getMemberList();
            for (Member member : memberList) {
                List<MedicineChest> medicineChestList = member.getMedicineChestList();
                List<String> collect = medicineChestList.stream().map(medicineChest -> medicineChest.getMedicineChestName()).collect(Collectors.toList());
                name.addAll(collect);
            }
            name = name.stream().distinct().collect(Collectors.toList());
            return new Result(true,StatusCode.OK,"查询成功",name);
        }
        return new Result(false,StatusCode.ERROR,"查询失败");
    }

    public Result reduceMedicine(Integer userId,Integer number,String name){
        Optional<User> byId = userDao.findById(userId);
        List<String> messageList=new ArrayList<>();
        if(byId.isPresent()){
            User user = byId.get();
            List<Medicine> medicineList = user.getMedicineList();
            for (Medicine medicine : medicineList) {
                if(medicine.getMedicineName().equals(name))
                {
                    Integer flag =medicine.getSurplus()>number?number:medicine.getSurplus();
                    if(!flag.equals(number))
                    {
                        messageList.add(medicine.getMedicineName()+"不够了");
                    }
                    userDao.updateMedicine(userId,flag,name);
                }
            }
            List<Member> memberList = user.getMemberList();
            for (Member member : memberList) {
                List<MedicineChest> medicineChestList = member.getMedicineChestList();
                medicineChestList.forEach(e->{
                    if(e.getMedicineChestName().equals(name))
                    {
                        Integer flag=e.getSurplus()>number?number:e.getSurplus();
                        if(!flag.equals(number))
                        {
                            messageList.add(e.getMedicineChestName()+"不够了");
                        }
                        userDao.updateMedicineChest(member.getId(),flag,name);
                    }
                });
            }
            return new Result(true,StatusCode.OK,"成功",messageList);
        }
        return new Result(false,StatusCode.ERROR,"失败",messageList);
    }

    public Result update(User user) {
        userDao.save(user);
        return new Result(true,StatusCode.OK,"修改成功");
    }
}
