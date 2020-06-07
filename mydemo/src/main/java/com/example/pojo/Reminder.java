package com.example.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;

/**
 * @author dsr
 * @date 2020-05-06
 **/
@Entity
@Table(name = "reminder")
@Setter
@Getter
public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "reminderName" ,length = 16)
    private String reminderName;
    @Column(name = "number")
    private Integer number;
    @Column(name = "time")
    private String time;
    @Column(name = "date")
    private String date;
    @Column(name = "flag")
    private boolean flag;
    @JsonIgnore
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user =new User();

}
