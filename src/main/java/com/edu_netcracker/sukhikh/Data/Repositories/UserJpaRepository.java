package com.edu_netcracker.sukhikh.Data.Repositories;

import com.edu_netcracker.sukhikh.Data.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

}

