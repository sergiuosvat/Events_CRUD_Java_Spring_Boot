package com.utcn.codingevents.controllers;

import com.utcn.codingevents.data.EventData;
import com.utcn.codingevents.models.Event;
import com.utcn.codingevents.models.EventType;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("events")
public class EventController {


    @GetMapping
    public String displayAllEvents(Model model)
    {
        model.addAttribute("title", "All Events");
        model.addAttribute("events", EventData.getAll());
        return "events/index";
    }

    //this will live at /events/create
    @GetMapping("create")
    public String renderCreateEventForm(Model model){
        model.addAttribute("title", "Create Event");
        model.addAttribute(new Event());
        model.addAttribute("types",EventType.values());
        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, Errors errors, Model model){
        if (errors.hasErrors()){
            model.addAttribute("title", "Create Event");
            return "events/create";
        }
        EventData.add(newEvent);
        return "redirect:/events";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model)
    {
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds){
        if(eventIds!= null){
            for(int id : eventIds)
            {
                EventData.remove(id);
            }
        }
        return "redirect:/events";
    }
}
