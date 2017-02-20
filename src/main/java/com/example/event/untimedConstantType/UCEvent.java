package com.example.event.untimedConstantType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
class UCEvent {
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
    @NotNull
    private LocalDate startPeriodDate;
    private LocalDate endPeriodDate;
    @NotNull
    private boolean checked;
    @NotNull
    private TypeOfTimeInterval typeOfTimeInterval;
    @NotNull
    private int timesInInterval;

    UCEventFacade.UCEventDto dto() {
        return new UCEventFacade.UCEventDto(name, description, tag, startPeriodDate, endPeriodDate, checked, typeOfTimeInterval, timesInInterval);
    }

}
