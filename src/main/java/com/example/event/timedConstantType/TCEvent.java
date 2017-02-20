package com.example.event.timedConstantType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
class TCEvent {
    @Id
    @GeneratedValue
    private long id;
    @Getter
    @NotNull
    private String name;
    private String description;
    @Size(max = 15)
    @NotNull
    private String tag;
    @OneToMany(targetEntity = PartOfSchedule.class, cascade= CascadeType.ALL)
    @JoinColumn(name = "event_id")
    @OrderBy("id")
    @NotNull
    private List<PartOfSchedule> schedule = new LinkedList<>();
    @NotNull
    private String frequencyInMonth;
    @NotNull
    private LocalDate startPeriodDate;
    @NotNull
    private LocalDate endPeriodDate;

    TCEventFacade.TCEventDto dto() {
        return new TCEventFacade.TCEventDto(name, description, tag, frequencyInMonth, schedule, startPeriodDate, endPeriodDate);
    }

}
