package com.example.demo.controllers;

import com.example.demo.models.SignUpRequest;
import com.example.demo.models.TestResult;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Controller
@RequestMapping("/registration")
public class UserController {

    final
    UserService userService;
    final BCryptPasswordEncoder bCryptPasswordEncoder;
    public UserController(BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
    }


    @GetMapping
    public String registration() {
        if (userService.findUser(userService.getCurrentUsername())==null)
            return "registration";
        return "redirect:/test";
    }

    @PostMapping()
    public String registration(@Valid SignUpRequest signUpRequest, Model model) {
        try {
            userService.addUser(signUpRequest, bCryptPasswordEncoder);
            return "redirect:/test";
        } catch (Exception e) {
            model.addAttribute("status", "Ошибка");
            return "registration";
        }
    }
}
