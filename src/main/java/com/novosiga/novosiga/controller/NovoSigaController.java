package com.novosiga.novosiga.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NovoSigaController {

    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/novosiga";
    }

    @GetMapping("/novosiga")
    public String index(Model model) {
        return "novosiga/index";
    }

}

