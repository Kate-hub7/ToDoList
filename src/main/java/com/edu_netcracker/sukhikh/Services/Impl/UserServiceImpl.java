package com.edu_netcracker.sukhikh.Services.Impl;

import com.edu_netcracker.sukhikh.Data.Entities.AffairEntity;
import com.edu_netcracker.sukhikh.Data.Entities.UserEntity;
import com.edu_netcracker.sukhikh.Data.Repositories.UserJpaRepository;
import com.edu_netcracker.sukhikh.Services.AffairService;
import com.edu_netcracker.sukhikh.Services.IdService;
import com.edu_netcracker.sukhikh.Services.UserService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Component
public class UserServiceImpl implements UserService {

    private final UserJpaRepository userJpaRepository;
    private final JdbcTemplate jdbcTemplate;

    private final IdService idService;
    private final AffairService affairService;

    public UserServiceImpl(IdService idService, AffairService affairService, UserJpaRepository userJpaRepository, JdbcTemplate template) {

        this.userJpaRepository = userJpaRepository;
        this.jdbcTemplate = template;
        this.idService = idService;
        this.affairService = affairService;

    }

    @Override
    public  String printAffairDay(Long id, Calendar day){

        UserEntity userEntity = userJpaRepository.getOne(id);
        List<AffairEntity> sublist = userEntity.getDairy().stream().filter(x -> x.getDay().equals(day)).collect(Collectors.toList());

        return printFunction(sublist);
    }

    @Override
    public String printDairy(Long id) {

        UserEntity userEntity = userJpaRepository.getOne(id);
        List<AffairEntity> toDoList = userEntity.getDairy();

        return printFunction(toDoList);
    }

    private String printFunction(List<AffairEntity> list){

        StringBuilder result = new StringBuilder();

        for (AffairEntity affairEntity : list) {
            result.append(affairEntity.getNameAffair()).append(affairEntity.isFinished()).append("\n");
            result.append("Type: ").append(affairEntity.getType().toString()).append("\n");
            result.append("Description: ").append(affairEntity.getDescription()).append("\n\n");
        }
        return result.toString();
    }


    @Override
    @Transactional
    public UserEntity createUser(String firstName, String lastName, String email) {

        UserEntity entity = new UserEntity(firstName, lastName, email);
        entity = userJpaRepository.saveAndFlush(entity);

        return entity;

    }


    @Override
    public UserEntity getById(Long id) {
        return userJpaRepository.findById(id).get();
    }

    @Override
    public List<UserEntity> findAll() {
        return userJpaRepository.findAll();
    }

}
