package com.example.event;

import com.example.event.timedConstantType.TCEventFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
class TCEventController {

    private TCEventFacade tcEventFacade;

    public TCEventController(TCEventFacade tcEventFacade) {
        this.tcEventFacade = tcEventFacade;
    }

    @PostMapping("/event/TC")
    TCEventFacade.TCEventDto eventTC(@RequestBody @Valid TCEventFacade.TCEventDto eventDto) {
        return tcEventFacade.addTCEvent(eventDto);
    }

    @GetMapping("/events/TC")
    List<TCEventFacade.TCEventDto> eventsTC() {
        return tcEventFacade.getTCEvents();
    }
}
