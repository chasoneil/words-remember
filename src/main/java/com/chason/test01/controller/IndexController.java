package com.chason.test01.controller;

import com.chason.test01.service.index.IndexServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private IndexServiceImpl indexService;

    @GetMapping()
    public String welcome () {
        return "welcome to springboot demo";
    }

    @GetMapping("/page")
    public String getIndexPage () {
        return indexService.getIndex();
    }



}
