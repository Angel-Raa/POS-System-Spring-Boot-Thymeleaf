package com.github.angel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author aguero
 */
@Controller
@RequestMapping({ "/", "/home" })
public class HomeController {

    @GetMapping
    public String home() {
        return "index";
    }

}
