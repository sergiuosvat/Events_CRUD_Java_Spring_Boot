package com.utcn.codingevents.controllers;

import com.utcn.codingevents.data.EventCategoryRepository;
import com.utcn.codingevents.data.EventRepository;
import com.utcn.codingevents.data.TagRepository;
import com.utcn.codingevents.models.Event;
import com.utcn.codingevents.models.EventCategory;
import com.utcn.codingevents.models.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("tags")
public class TagController {
    @Autowired
    TagRepository tagRepository;

    @GetMapping
    public String displayTags(Model model)
    {
        model.addAttribute("title", "All Tags");
        model.addAttribute("tags", tagRepository.findAll());
        return "tags/index";
    }

    @GetMapping("create")
    public String renderCreateTagForm(Model model){
        model.addAttribute("title", "Create Tag");
        model.addAttribute(new Tag());
        return "tags/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Tag tag, Errors errors, Model model){
        if (errors.hasErrors()){
            model.addAttribute("title", "Create Tag");
            return "tags/create";
        }
        tagRepository.save(tag);
        return "redirect:/tags";
    }
}
