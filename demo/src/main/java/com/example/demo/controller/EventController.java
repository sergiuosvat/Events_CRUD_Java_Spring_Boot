package com.example.demo.controller;

import com.example.demo.dto.EventTagDTO;
import com.example.demo.model.Event;
import com.example.demo.model.EventCategory;
import com.example.demo.model.Tag;
import com.example.demo.service.EventCategoryService;
import com.example.demo.service.EventService;
import com.example.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventCategoryService eventCategoryService;

    @Autowired
    private TagService tagService;

    @GetMapping
    public String displayAllEvents(@RequestParam(required = false) Integer categoryId, Model model)
    {
        if(categoryId == null){
            model.addAttribute("title", "All Events");
            model.addAttribute("events", eventService.findAll());
        } else {
            Optional<EventCategory> result = eventCategoryService.findById(categoryId);
            if(result.isEmpty()){
                model.addAttribute("title", "Invalid category ID: " + categoryId);
            } else{
                EventCategory category = result.get();
                model.addAttribute("title", "Events in category: " + category.getName());
                model.addAttribute("events", category.getEvents());
            }
        }
        return "events/index";
    }

    @GetMapping("create")
    public String renderCreateEventForm(Model model){
        model.addAttribute("title", "Create Event");
        model.addAttribute(new Event());
        model.addAttribute("categories",eventCategoryService.findAll());
        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute Event newEvent, Errors errors, Model model){
        if (errors.hasErrors()){
            model.addAttribute("title", "Create Event");
            return "events/create";
        }
        eventService.save(newEvent);
        return "redirect:/events";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model)
    {
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", eventService.findAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds){
        if(eventIds!= null){
            for(int id : eventIds)
            {
                eventService.deleteById(id);
            }
        }
        return "redirect:/events";
    }

    @GetMapping("detail")
    public String displayEventDetails(@RequestParam Integer eventId, Model model)
    {
        Optional<Event> result = eventService.findById(eventId);
        if(result.isEmpty()){
            model.addAttribute("title", "Invalid Event ID: " + eventId);
        } else{
            Event event = result.get();
            model.addAttribute("title",  event.getName() + " Details");
            model.addAttribute("event", event);
            model.addAttribute("tags", event.getTags());
        }
        return "events/detail";
    }

    @GetMapping("add-tag")
    public String displayAddTagForm(@RequestParam Integer eventId, Model model) {
        Optional<Event> result = eventService.findById(eventId);
        Event event = result.get();
        model.addAttribute("title", "Add Tag to: " + event.getName());
        model.addAttribute("tags", tagService.findAll());
        EventTagDTO eventTagDTO = new EventTagDTO();
        eventTagDTO.setEvent(event);
        model.addAttribute("eventTag",eventTagDTO);
        return "events/add-tag";
    }

    @PostMapping("add-tag")
    public String processAddTagForm(@ModelAttribute EventTagDTO eventTag, Model model, Errors errors){
        if(!errors.hasErrors()){
            Event event = eventTag.getEvent();
            Tag tag = eventTag.getTag();
            if(!event.getTags().contains(tag))
            {
                event.addTag(tag);
                eventService.save(event);
            }
            return "redirect:detail?eventId=" + event.getId();
        }
        return "redirect:events/add-tag";
    }
}
