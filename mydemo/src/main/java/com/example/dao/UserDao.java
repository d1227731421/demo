package com.example.dao;

import com.example.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dsr
 * @date 2020-05-06
 **/

public interface UserDao extends JpaRepository<User,Integer> , JpaSpecificationExecutor<User> {
    User findByPhoneAndPassword(String phone,String password);
    boolean existsByPhone(String phone);
    @Modifying
    @Transactional
    @Query(value = "UPDATE medicine set surplus=(surplus-?2) WHERE user_id=?1 and medicine_name=?3",nativeQuery = true)
    void updateMedicine(Integer userId,Integer number,String name);
    @Modifying
    @Transactional
    @Query(value = "UPDATE medicine_chest set surplus=(surplus-?2) WHERE  member_id=?1 and medicine_chest_name=?3",nativeQuery = true)
    void updateMedicineChest(Integer memberId,Integer number,String name);
}
