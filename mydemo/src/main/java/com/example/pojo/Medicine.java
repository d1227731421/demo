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
@Table(name = "medicine")
@Setter
@Getter
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "medicineName" ,length = 16)
    private String medicineName;
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
    private Integer surplus;;
    @Column(name = "type")
    private String type;
    @Column(name = "description")
    private String description;
    @JsonIgnore
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user=new User();
}
