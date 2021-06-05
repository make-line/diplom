package com.example.demo.controllers;

import com.example.demo.repo.BotRepo;
import com.example.demo.services.TestResultService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adminpage")
public class AdminController {
    final
    UserService userService;
    final
    TestResultService testResultService;
    final
    BotRepo botRepo;

    public AdminController(UserService userService, TestResultService testResultService, BotRepo botRepo) {
        this.userService = userService;
        this.testResultService = testResultService;
        this.botRepo = botRepo;
    }
    @GetMapping
    public  String info(Model model){
        model.addAttribute("users",userService.findAll());
        model.addAttribute("testResult",testResultService.findAll());
        model.addAttribute("bot",botRepo.findAll());
        if(userService.getCurrentUsername().equals("admin@admin.com"))
            return "admin";
        return "redirect:/info";
    }
    @GetMapping("/user/del/{id}")
    public String del(@PathVariable("id") long id){
        if(userService.getCurrentUsername().equals("admin@admin.com")) {
            userService.delUser(id);
            return "redirect:/adminpage";
        }
        return "redirect:/info";
    }
    @GetMapping("/bot/del/{id}")
    public String delbot(@PathVariable("id") long id){
        if(userService.getCurrentUsername().equals("admin@admin.com")) {
            botRepo.deleteById(id);
            return "redirect:/adminpage";
        }
        return "redirect:/info";
    }

}
