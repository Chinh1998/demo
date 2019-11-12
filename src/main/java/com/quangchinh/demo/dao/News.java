package com.quangchinh.demo.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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
    @ManyToOne
    @JoinColumn(name ="user_id")
    private User user;
    @OneToOne
    @JoinColumn(name ="major_id")
    @JsonProperty("major_id")
    private Majors majors;
}
