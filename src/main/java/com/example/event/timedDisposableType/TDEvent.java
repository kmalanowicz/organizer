package com.example.event.timedDisposableType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
class TDEvent {
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
    private LocalDateTime startDateTime;
    @NotNull
    private LocalDateTime endDateTime;

    TDEventFacade.TDEventDto dto() {
        return new TDEventFacade.TDEventDto(name, description, tag, startDateTime, endDateTime);
    }


}
