package com.example.codefellowship.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;
@Controller
public class GeneralController {
    @GetMapping("/")
    public RedirectView getHomePage(){
        return new RedirectView( "/songs");
    }
}
