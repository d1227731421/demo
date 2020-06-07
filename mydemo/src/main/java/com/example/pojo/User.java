package com.example.pojo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

/**
 * @author dsr
 * @date 2020-05-06
 **/
@Entity
@Table(name = "user")
@Setter
@Getter
@DynamicUpdate
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "username",length = 16)
    private String username;
    @Column(name = "phone",unique = true,length = 11)
    private String phone;
    @Column(name = "password",length = 16)
    private String password;
    @OneToMany(mappedBy ="user",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private List<Member> memberList;
    @OneToMany(mappedBy ="user",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private List<Reminder> reminderList;
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private List<Medicine> medicineList;

}
