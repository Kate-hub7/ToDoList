package com.edu_netcracker.sukhikh.Controllers.Info;

import com.edu_netcracker.sukhikh.Data.Entities.AffairType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
@NoArgsConstructor
public class AffairInfo {

    private Long id;
    private Long user;
    private String day;
    private String type;
    private String name;
    private String description;

}
