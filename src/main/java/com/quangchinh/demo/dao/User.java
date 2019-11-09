package com.quangchinh.demo.dao;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="user_table")
public class User {

    @Id
    @GeneratedValue(generator ="uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String userName;
    private String password;
    private String nbphone;
    private  String mail;
    @ManyToMany
    private Set<Role> roles;
    public  User(){};
    public User(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.nbphone = user.getNbphone();
        this.mail = user.getMail();
    }
}
