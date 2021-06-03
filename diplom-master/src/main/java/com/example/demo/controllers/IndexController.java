package com.example.demo.controllers;

//import com.example.demo.services.UserService;
import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @Autowired
    UserService userService;
//    final
//    UserService userService;
//
//    public IndexController(UserService userService) {
//        this.userService = userService;
//    }

    @GetMapping("/home")
    public String greeting() {
        return "index";
    }
    @GetMapping("/info")
    public  String info(Model model){
        // System.out.println(userService.findUser(userService.getCurrentUsername()).getRoleList().get(0));
        //if(userService.getCurrentUsername().equals("admin@admin.com")){
//        if(userService.findUser(userService.getCurrentUsername()).getRoleList().get(0).getRole().equals("ADMIN")){
//            return "redirect:/adminpage";
//        }
//        else {
            User user=userService.findUser(userService.getCurrentUsername());
            System.out.println(user.getTestResult().toString());
            if(user.getTestResult().getResult() >4) model.addAttribute("result", "КРУТА "+user.getTestResult().getResult()+"/5");
            if(user.getTestResult().getResult()>= 3 &&user.getTestResult().getResult()<=4) model.addAttribute("result", "НОРМ "+user.getTestResult().getResult()+"/5");
            if(user.getTestResult().getResult()<3) model.addAttribute("result", "НУ ТАКОЕ "+user.getTestResult().getResult()+"/5");
            model.addAttribute("name",user.getName());
            return "info";
//        }
    }
}
