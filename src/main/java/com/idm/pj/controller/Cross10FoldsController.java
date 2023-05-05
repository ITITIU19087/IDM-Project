package com.idm.pj.controller;


import com.idm.pj.service.Cross10FoldsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Cross10FoldsController {
    @Autowired
    Cross10FoldsService service;

    @GetMapping("/nb10")
    public String nbClassify() throws Exception {
        return service.nB10Folds();
    }

    @GetMapping("/j4810")
    public String j48Classify() throws Exception {
        return service.j4810Folds();
    }
    @GetMapping("/lr10")
    public String lRClassify() throws Exception {
        return service.lR10Folds();
    }
    @GetMapping("/rf10")
    public String rFClassify() throws Exception {
        return service.rF10Folds();
    }
}
