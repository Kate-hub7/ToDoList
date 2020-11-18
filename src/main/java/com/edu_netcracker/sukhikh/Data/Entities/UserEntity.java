package com.edu_netcracker.sukhikh.Data.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "user_entity")
@Entity(name = "userEntity")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserEntity{

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("user")
    private List<AffairEntity> dairy;

    public UserEntity(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName  = lastName;
        this.email = email;
    }

}

