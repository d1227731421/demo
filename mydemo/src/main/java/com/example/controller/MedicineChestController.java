package com.example.controller;

import com.example.pojo.MedicineChest;
import com.example.service.MedicineChestService;
import com.example.util.Result;
import com.example.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author dsr
 * @date 2020-05-07
 **/

@RestController
@RequestMapping("/mchest")
public class MedicineChestController {
    @Autowired
    private MedicineChestService medicineChestService;
    @RequestMapping(value = "/add/{memberId}",method = RequestMethod.POST)
    public Result addMedicine(@PathVariable  Integer memberId, @RequestBody MedicineChest medicine){

        return medicineChestService.addMedicine(memberId,medicine);
    }
    @RequestMapping(value = "/update/{memberId}",method = RequestMethod.POST)
    public Result updateMedicine( @RequestBody MedicineChest medicine,@PathVariable  Integer memberId){
        return medicineChestService.updateMedicine(medicine,memberId);
    }
    @RequestMapping(value = "/del",method = RequestMethod.DELETE)
    public Result delMedicine( @RequestBody List<MedicineChest> medicineChest){
        return medicineChestService.delMedicine(medicineChest);
    }
    @RequestMapping(value = "/query/{memberId}",method = RequestMethod.GET)
    public Result queryMedicine(@PathVariable  Integer memberId){
        return medicineChestService.queryMedicine(memberId);
    }
}
