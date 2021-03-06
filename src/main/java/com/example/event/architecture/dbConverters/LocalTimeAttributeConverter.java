package com.example.event.architecture.dbConverters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Time;
import java.time.LocalTime;

@Converter(autoApply = true)
public class LocalTimeAttributeConverter implements AttributeConverter<LocalTime, Time> {

    @Override
    public Time convertToDatabaseColumn(LocalTime locTime) {
        return (locTime == null ? null : Time.valueOf(locTime));
    }

    @Override
    public LocalTime convertToEntityAttribute(Time sqlData) {
        return (sqlData == null ? null : sqlData.toLocalTime());
    }
}