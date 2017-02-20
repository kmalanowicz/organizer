package com.example.event;

import com.example.event.timedDisposableType.TDEventFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
class TDEventController {

    private TDEventFacade tdEventFacade;

    public TDEventController(TDEventFacade tdEventFacade) {
        this.tdEventFacade = tdEventFacade;
    }

    @PostMapping("/event/TD")
    TDEventFacade.TDEventDto eventTD(@RequestBody @Valid TDEventFacade.TDEventDto eventDto) {
        return tdEventFacade.addTDEvent(eventDto);
    }

    @GetMapping("/events/TD")
    List<TDEventFacade.TDEventDto> eventsTD() {
        return tdEventFacade.getTDEvents();
    }
}
