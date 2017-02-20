package com.example.event.timedConstantType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryTCEventRepository implements TCEventRepository {
    private ConcurrentHashMap<String, TCEvent> map = new ConcurrentHashMap<>();

    @Override
    public TCEvent saveAndFlush(TCEvent event) {
        map.put(event.getName(), event);
        return event;
    }

    @Override
    public List<TCEvent> findAll() {
        return new ArrayList<>(map.values());
    }
}
