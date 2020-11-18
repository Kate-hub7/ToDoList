package com.edu_netcracker.sukhikh.Data.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;
import java.util.Calendar;

@Table(name="affair_entity")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class AffairEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private AffairType type;
    private String nameAffair;
    private String description;
    private boolean isFinished;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("affairs")
    private UserEntity user;

    private Calendar day;

    public AffairEntity(AffairType type, String nameAffair, String description, Calendar day, UserEntity userEntity){
        this.type = type;
        this.nameAffair = nameAffair;
        this.description = description;
        this.day = day;
        this.isFinished = false;
        this.user = userEntity;
    }

    public String isFinished(){
        return (isFinished)? "  \u2713" : "  \u2717";
    }

}
