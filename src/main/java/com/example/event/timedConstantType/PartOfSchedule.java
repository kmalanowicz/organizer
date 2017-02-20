package com.example.event.timedConstantType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
class PartOfSchedule {
    @Id
    @GeneratedValue
    private long id;
    @Getter
    @Setter
    DayOfWeek dayOfWeek;
    @Getter
    @Setter
    LocalTime time;
    @Getter
    @Setter
    LocalTime duration;

    //@ManyToOne
    //@JoinColumn(name = "tcEvent_id")
    //private TCEvent tcEvent;
}
