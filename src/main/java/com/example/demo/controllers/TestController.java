package com.example.demo.controllers;

import com.example.demo.models.SignUpRequest;
import com.example.demo.models.TestResult;
import com.example.demo.repo.TestRepo;
import com.example.demo.services.TestResultService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class TestController {
    final
    UserService userService;
    final
    TestResultService testResultService;

    public TestController(UserService userService, TestResultService testResultService) {
        this.userService = userService;
        this.testResultService = testResultService;
    }

    @GetMapping("/test")
    public String test(Model model) {
        if(userService.findUser(userService.getCurrentUsername()).getTestResult()==null) {
            model.addAttribute("testResult", new TestResult());
            return "test";
        }
        else
            return "redirect:/info";
    }
    @PostMapping("/test")
    public String test(TestResult testResult, Model model){
        model.addAttribute("testResult", testResult );
        testResult.setResult(testResult.getQ1()+testResult.getQ2()+testResult.getQ3()+testResult.getQ4()+testResult.getQ5()+testResult.getQ6()+testResult.getQ7()+testResult.getQ8()+testResult.getQ9()+testResult.getQ10());
        testResult.setUser(userService.findUser(userService.getCurrentUsername()));
        System.out.println(testResult.toString());
        testResultService.addTest(testResult);
        userService.findUser(userService.getCurrentUsername()).setTestResult(testResult);
        return "redirect:/info";
    }
}
