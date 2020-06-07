package com.example.service;

import com.example.dao.MedicineDao;
import com.example.pojo.Medicine;
import com.example.util.Result;
import com.example.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dsr
 * @date 2020-05-07
 **/
@Service
public class MedicineService {
    @Autowired
    private MedicineDao medicineDao;

    public Result addMedicine(Integer userID,Medicine medicine){
        medicine.getUser().setId(userID);
        medicineDao.save(medicine);
        return new Result(true, StatusCode.OK,"添加成功");
    }
    public Result updateMedicine(Integer userID,Medicine medicine){
        medicine.getUser().setId(userID);
        medicineDao.save(medicine);
        return new Result(true, StatusCode.OK,"修改成功");
    }
    public Result queryMedicine(Integer userID,Medicine medicine){
        medicine.getUser().setId(userID);
        return new Result(true, StatusCode.OK,"查询成功",
                medicineDao.findByUserAndType(medicine.getUser(),medicine.getType()));
    }
    public Result delMedicine(Integer id){
        medicineDao.deleteById(id);
        return new Result(true, StatusCode.OK,"删除成功");
    }
}
