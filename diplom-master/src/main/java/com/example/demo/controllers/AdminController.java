package com.example.demo.controllers;

import com.example.demo.services.TestResultService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adminpage")
public class AdminController {
    final
    UserService userService;
    final
    TestResultService testResultService;

    public AdminController(UserService userService, TestResultService testResultService) {
        this.userService = userService;
        this.testResultService = testResultService;
    }
    @GetMapping
    public  String info(Model model){
        model.addAttribute("users",userService.findAll());
        model.addAttribute("testResult",testResultService.findAll());
        if(userService.getCurrentUsername().equals("admin@admin.com"))
            return "admin";
        return "redirect:/info";
    }
}
