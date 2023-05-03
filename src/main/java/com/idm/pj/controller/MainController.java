package com.idm.pj.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainController {
    @GetMapping("/")
    public ModelAndView showForm() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index.html");
        return mv;
    }


    @PostMapping("/process_form")
    public ModelAndView processForm(@RequestParam("type") String type) {
        ModelAndView mav = new ModelAndView();
        if (type != null && type.equals("j48")) {
            mav.setViewName("redirect:http://localhost:8888/j48classi");
        } else if (type != null && type.equals("nb")) {
            mav.setViewName("redirect:http://localhost:8888/nbclassi");
        } else {
            mav.addObject("error", "Invalid option selected.");
            mav.setViewName("index.html");
        }
        return mav;
    }

}
