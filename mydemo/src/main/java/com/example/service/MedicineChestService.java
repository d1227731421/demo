package com.example.service;

import com.example.dao.MedicineChestDao;
import com.example.pojo.Medicine;
import com.example.pojo.MedicineChest;
import com.example.pojo.Member;
import com.example.util.Result;
import com.example.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dsr
 * @date 2020-05-07
 **/
@Service
public class MedicineChestService {
    @Autowired
    private MedicineChestDao medicineChestDao;

    public Result addMedicine(Integer memberId, MedicineChest medicineChest){
        medicineChest.getMember().setId(memberId);
        medicineChestDao.save(medicineChest);
        return new Result(true, StatusCode.OK,"添加成功");
    }
    public Result updateMedicine(MedicineChest medicine, Integer memberId){
        medicine.getMember().setId(memberId);
        medicineChestDao.save(medicine);
        return new Result(true, StatusCode.OK,"修改成功");
    }
    public Result delMedicine(List<MedicineChest> medicineChest){
        medicineChestDao.deleteAll(medicineChest);
        return new Result(true, StatusCode.OK,"删除成功");
    }

    public Result queryMedicine(Integer memberId) {
        Member member = new Member();
        member.setId(memberId);
        return new Result(true, StatusCode.OK,"查询成功",medicineChestDao.findByMember(member));
    }

}
