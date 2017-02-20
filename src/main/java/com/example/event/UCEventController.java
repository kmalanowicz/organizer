package com.example.event;

import com.example.event.untimedConstantType.UCEventFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
class UCEventController {
    private UCEventFacade ucEventFacade;

    public UCEventController(UCEventFacade ucEventFacade) {
        this.ucEventFacade = ucEventFacade;
    }

    @PostMapping("/event/UC")
    UCEventFacade.UCEventDto eventUC(@RequestBody @Valid UCEventFacade.UCEventDto eventDto) {
        return ucEventFacade.addUCEvent(eventDto);
    }

    @GetMapping("/events/UC")
    List<UCEventFacade.UCEventDto> eventsUC() {
        return ucEventFacade.getUCEvents();
    }
}
