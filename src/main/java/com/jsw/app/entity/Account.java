package com.jsw.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_ACC")
public class Account {
    @Id
    @GeneratedValue
    private int id;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<User> followors;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<User> followings;
}
