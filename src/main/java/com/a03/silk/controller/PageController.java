package com.a03.silk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class PageController {

    @Autowired
    ServerProperties serverProperties;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("port")
    public String ActivePort(Model model) {
        model.addAttribute("port", serverProperties.getPort());
        return "active-port";
    }


}
