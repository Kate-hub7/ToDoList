package com.edu_netcracker.sukhikh.Services.Impl;

import com.edu_netcracker.sukhikh.Data.Entities.AffairEntity;
import com.edu_netcracker.sukhikh.Data.Entities.AffairType;
import com.edu_netcracker.sukhikh.Data.Entities.UserEntity;
import com.edu_netcracker.sukhikh.Data.Repositories.AffairJpaRepository;
import com.edu_netcracker.sukhikh.Services.AffairService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

@Service
@Component
@Transactional
public class AffairServiceImpl implements AffairService {

    private final AffairJpaRepository affairJpaRepository;
    private final JdbcTemplate jdbcTemplate;

    public AffairServiceImpl(AffairJpaRepository affairJpaRepository, JdbcTemplate template) {

        this.affairJpaRepository = affairJpaRepository;
        this.jdbcTemplate = template;
    }

    @Override
    public AffairEntity addAffair(AffairType type, String name, String description, Calendar day, UserEntity userEntity) {

        AffairEntity newAffair  = new AffairEntity(type, name, description, day, userEntity);
        newAffair = affairJpaRepository.saveAndFlush(newAffair);
        return newAffair;
    }

    @Override
    public void deleteAffair(Long id) {
        affairJpaRepository.deleteById(id);
    }

    @Override
    public void finishAffair(Long id) {
        affairJpaRepository.getOne(id).setFinished(true);
    }
}
