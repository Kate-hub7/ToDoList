package com.edu_netcracker.sukhikh.Services;

import com.edu_netcracker.sukhikh.Data.Entities.AffairEntity;
import com.edu_netcracker.sukhikh.Data.Entities.AffairType;
import com.edu_netcracker.sukhikh.Data.Entities.UserEntity;

import java.util.Calendar;

public interface AffairService {

    public AffairEntity addAffair(AffairType type, String name, String description, Calendar day, UserEntity userEntity);
    public void deleteAffair(Long id);
    public void finishAffair(Long id);
}
