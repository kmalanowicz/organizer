package com.example.event;

import com.example.event.timedDisposableType.TDEventFacade;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/events/TD/{name}")
    TDEventFacade.TDEventDto eventTD(@PathVariable("name") String name) {
        return tdEventFacade.getTDEventByName(name);
    }
}
