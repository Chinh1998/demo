package com.quangchinh.demo.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="cmt_Table")
public class Comment {

    @Id
    private  String id;
    private String content_cmt;
    @ManyToOne
    @JoinColumn (name="user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name="news_id")
    private News news;
}
