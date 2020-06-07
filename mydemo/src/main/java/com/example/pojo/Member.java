package com.example.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author dsr
 * @date 2020-05-06
 **/
@Entity
@Table(name = "member")
@Setter
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "memberName",length = 16 )
    private String memberName;
    @JsonIgnore
    @ManyToOne(optional=false,fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user=new User();
    @OneToMany(mappedBy = "member",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private List<MedicineChest> medicineChestList;
}
