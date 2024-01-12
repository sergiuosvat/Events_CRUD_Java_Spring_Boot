package com.example.demo.controller;

import com.example.demo.model.EventCategory;

import com.example.demo.service.EventCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("eventCategories")
public class EventCategoryController {

    @Autowired
    private EventCategoryService eventCategoryService;

    @GetMapping
    public String displayAllCategories(Model model)
    {
        model.addAttribute("title", "All Categories");
        model.addAttribute("categories", eventCategoryService.findAll());
        return "eventCategories/index";
    }

    @GetMapping("create")
    public String renderCreateCategoryForm(Model model){
        model.addAttribute("title", "Create Category");
        model.addAttribute(new EventCategory());
        return "eventCategories/create";
    }

    @PostMapping("create")
    public String processCreateEventCategoryForm(@ModelAttribute EventCategory eventCategory, Errors errors, Model model){
        if (errors.hasErrors()){
            model.addAttribute("title", "Create Category");
            return "eventCategories/create";
        }
        eventCategoryService.save(eventCategory);
        return "redirect:/eventCategories";
    }

}
