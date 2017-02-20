package com.example.event.untimedDisposableType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryUDEventRepository implements UDEventRepository {
    private ConcurrentHashMap<String, UDEvent> map = new ConcurrentHashMap<>();

    @Override
    public UDEvent saveAndFlush(UDEvent event) {
        map.put(event.getName(), event);
        return event;
    }

    @Override
    public List<UDEvent> findAll() {
        return new ArrayList<>(map.values());
    }
}
