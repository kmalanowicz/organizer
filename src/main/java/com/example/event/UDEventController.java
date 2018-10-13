package com.example.event;

import com.example.event.untimedDisposableType.UDEventFacade;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
class UDEventController {

    private UDEventFacade udEventFacade;

    public UDEventController(UDEventFacade udEventFacade) {
        this.udEventFacade = udEventFacade;
    }

    @PostMapping("/event/UD")
    UDEventFacade.UDEventDto eventUD(@RequestBody @Valid UDEventFacade.UDEventDto eventDto) {
        return udEventFacade.addUDEvent(eventDto);
    }

    @GetMapping("/events/UD")
    List<UDEventFacade.UDEventDto> eventsUD() {
        return udEventFacade.getUDEvents();
    }

    @GetMapping("/events/UD/{name}")
    UDEventFacade.UDEventDto eventUD(@PathVariable("name") String name) {
        return udEventFacade.getUDEventByName(name);
    }
}
