package com.example.event.untimedDisposableType;

import org.springframework.data.repository.Repository;

import java.util.List;

interface UDEventRepository extends Repository<UDEvent, String> {
    UDEvent saveAndFlush(UDEvent event);
    List<UDEvent> findAll();
}
