package com.lms.api.direction.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class FormController {

    @GetMapping("/")
    public String main() {
         log.info("main");

        return "main";
    }


}
