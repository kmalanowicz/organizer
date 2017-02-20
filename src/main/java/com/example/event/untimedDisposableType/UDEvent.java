package com.example.event.untimedDisposableType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
class UDEvent {
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
    private LocalDateTime deadlineDateTime;
    private LocalTime howLongItTakes = LocalTime.of(0,0);

    UDEventFacade.UDEventDto dto() {
        return new UDEventFacade.UDEventDto(name, description, tag, deadlineDateTime, howLongItTakes);
    }


}
