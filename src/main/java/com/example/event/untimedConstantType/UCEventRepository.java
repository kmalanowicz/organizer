package com.example.event.untimedConstantType;

import org.springframework.data.repository.Repository;

import java.util.List;

interface UCEventRepository extends Repository<UCEvent, String> {
    UCEvent saveAndFlush(UCEvent event);
    List<UCEvent> findAll();
}
