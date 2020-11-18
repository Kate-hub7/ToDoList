package com.edu_netcracker.sukhikh.Services;

import com.edu_netcracker.sukhikh.Data.Entities.AffairEntity;
import com.edu_netcracker.sukhikh.Data.Entities.AffairType;
import com.edu_netcracker.sukhikh.Data.Entities.UserEntity;

import java.util.Calendar;
import java.util.List;

public interface UserService {

    public String printDairy(Long id);
    public String printAffairDay(Long id, Calendar day);
    public UserEntity createUser(String firstName, String lastName, String email);
    public UserEntity getById(Long id);
    public List<UserEntity> findAll();

}
