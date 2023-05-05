package com.idm.pj.controller;

import com.idm.pj.service.ClassificationService;
import com.idm.pj.service.Cross10FoldsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainController {

    @Autowired
    ClassificationService service;

    @Autowired
    Cross10FoldsService cross10FoldsService;

    @GetMapping("/")
    public ModelAndView showForm() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index.html");
        return mv;
    }


    @PostMapping("/process_form")
    public ModelAndView processForm(@RequestParam("type") String type) throws Exception {
        ModelAndView mav = new ModelAndView();
        String result="";
        switch(type) {
            case "j48":
                result = service.j48Classi();
                break;
            case "nb":
                result = service.naiveBayes();
                break;
            case "lr":
                result = service.lRClassi();
                break;
            case "rf":
                result = service.rFClassi();
                break;
            case "nb10folds":
                result = cross10FoldsService.nB10Folds();
                break;
            case "j4810folds":
                result = cross10FoldsService.j4810Folds();
                break;
            case "lr10folds":
                result = cross10FoldsService.lR10Folds();
                break;
            case "rf10folds":
                result = cross10FoldsService.rF10Folds();
                break;
            default:
                mav.addObject("error", "Invalid option selected.");
                break;
        }
        mav.addObject("result",result);
        mav.setViewName("index.html");
        return mav;
    }
}
