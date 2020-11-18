package com.edu_netcracker.sukhikh.Services.Impl;

import com.edu_netcracker.sukhikh.Services.IdService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class IdServiceImpl implements IdService {

    Long id = Long.valueOf(0);

    @Override
    public Long getNextId() {
        return ++id;
    }
}
