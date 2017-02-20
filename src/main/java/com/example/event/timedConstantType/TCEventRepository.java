package com.example.event.timedConstantType;

import org.springframework.data.repository.Repository;

import java.util.List;

interface TCEventRepository extends Repository<TCEvent, String> {
    TCEvent saveAndFlush(TCEvent entity);

    List<TCEvent> findAll();
}
