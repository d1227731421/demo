package com.example.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

/**
 * @author dsr
 * @date 2020-05-06
 **/
@Entity
@Table(name = "medicineChest")
@Setter
@Getter
public class MedicineChest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "medicineChestName",length = 16)
    private String medicineChestName;
    @Column(name = "disease" )
    private String disease;
    @Column(name = "usages")
    private String usages;
    @Column(name = "expiryDate")
    private Date expiryDate;
    @Column(name = "taboo" )
    private String taboo;
    @Column(name = "storage" )
    private String storage;
    @Column(name = "surplus")
    private Integer surplus;
    @JsonIgnore
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member=new Member();
    
}
