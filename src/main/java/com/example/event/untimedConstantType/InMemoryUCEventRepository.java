package com.example.event.untimedConstantType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryUCEventRepository implements UCEventRepository {
    private ConcurrentHashMap<String, UCEvent> map = new ConcurrentHashMap<>();

    @Override
    public UCEvent saveAndFlush(UCEvent event) {
        map.put(event.getName(), event);
        return event;
    }

    @Override
    public List<UCEvent> findAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public UCEvent findByName(String name) {
        return map.get(name);
    }
}
