package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.model.RegistrationRequest;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    @GetMapping("/admin")
    public String register(@RequestParam(value="registrationSuccess", required = false) String success, Model model){
        model.addAttribute("title", "Register");
        model.addAttribute("registrationSuccess", success);
        model.addAttribute("user", new RegistrationRequest());

        return "admin";
    }

    @PostMapping("/createAdmin")
    public String createAdmin(@ModelAttribute("user") RegistrationRequest registrationRequest, RedirectAttributes redirectAttributes){

        UserDto userDto = userService.registerAdmin(registrationRequest);

        redirectAttributes.addAttribute("registrationSuccess", "Success");

        return "redirect:/admin";
    }
}
