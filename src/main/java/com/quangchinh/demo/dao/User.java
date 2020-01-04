package com.quangchinh.demo.dao;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String username;
    private String password;
    private int phone;
    private String email;
    private String functionuser;
    @ManyToMany
    Set<Role> roles;
}
