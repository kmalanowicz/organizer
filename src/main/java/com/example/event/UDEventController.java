package com.example.event;

import com.example.event.untimedDisposableType.UDEventFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
