package com.example.dao;

import com.example.pojo.MedicineChest;
import com.example.pojo.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MedicineChestDao extends JpaRepository<MedicineChest,Integer>, JpaSpecificationExecutor<MedicineChest> {
    List<MedicineChest> findByMember(Member member);
}
