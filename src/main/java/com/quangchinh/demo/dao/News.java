package com.quangchinh.demo.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "news_Table")
public class News {

    @Id
    @GeneratedValue(generator ="uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String title;
    private String image;
    private String content;
    private int view;
    private boolean approved;
    Date createDate;
    @ManyToOne
    @JoinColumn(name ="user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name ="majors_id")
    private Majors majors;
}
