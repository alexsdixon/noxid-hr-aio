package net.alexdixon.noxidhraio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class LoginController {


    @RequestMapping(value = "login")
    public String displaysLoginPage (Model model) {

        model.addAttribute("title", "Please LOGIN");

        return "/login";
    }

    @RequestMapping(value = "")
    public String index (Model model) {

        model.addAttribute("title", "HR AIO");

        return "/index";
    }

}