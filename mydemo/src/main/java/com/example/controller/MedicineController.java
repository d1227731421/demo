package com.example.controller;

import com.example.pojo.Medicine;
import com.example.service.MedicineService;
import com.example.util.Result;
import com.example.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author dsr
 * @date 2020-05-07
 **/
@RestController
@RequestMapping("/med")
public class MedicineController {
    @Autowired
    private MedicineService medicineService;
    @RequestMapping(value = "/{userId}",method = RequestMethod.POST)
    public Result addMedicine(@PathVariable Integer userId,@RequestBody Medicine medicine){
        return medicineService.addMedicine(userId,medicine);
    }
    @RequestMapping(value = "/{userId}",method = RequestMethod.PUT)
    public Result updateMedicine(@PathVariable Integer userId,@RequestBody Medicine medicine){
        return medicineService.updateMedicine(userId,medicine);
    }
    @RequestMapping(value = "/query/{userId}",method = RequestMethod.POST)
    public Result queryMedicine(@RequestBody Medicine medicine,@PathVariable Integer userId){
        return medicineService.queryMedicine(userId,medicine);
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result delMedicine(@PathVariable Integer id){
        return medicineService.delMedicine(id);
    }

}
