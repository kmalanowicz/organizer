package com.example.event.timedDisposableType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryTDEventRepository implements TDEventRepository {
    private ConcurrentHashMap<String, TDEvent> map = new ConcurrentHashMap<>();

    @Override
    public TDEvent saveAndFlush(TDEvent event) {
        map.put(event.getName(), event);
        return event;
    }

    @Override
    public List<TDEvent> findAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public TDEvent findByName(String name) {
        return map.get(name);
    }
}
