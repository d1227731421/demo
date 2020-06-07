package com.example.dao;

import com.example.pojo.Medicine;
import com.example.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MedicineDao extends JpaRepository<Medicine,Integer>, JpaSpecificationExecutor<Medicine> {
    List<Medicine> findByUserAndType(User user,String type);
}
