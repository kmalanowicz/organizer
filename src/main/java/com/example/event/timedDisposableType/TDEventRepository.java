package com.example.event.timedDisposableType;

import org.springframework.data.repository.Repository;

import java.util.List;

interface TDEventRepository extends Repository<TDEvent, String> {
    TDEvent saveAndFlush(TDEvent event);
    List<TDEvent> findAll();
}
