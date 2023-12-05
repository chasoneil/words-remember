package com.chason.words.controller;

import com.chason.words.common.R;
import com.chason.words.entity.jp.JpWords;
import com.chason.words.service.jp.JpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/jp")
public class JpWordsController {

    @Autowired
    private JpService jpService;

    @GetMapping("/list")
    @ResponseBody
    public R list () {
        List<JpWords> all = jpService.findAll();
        return R.ok(all);
    }

    @GetMapping("/add")
    public String add () {

        JpWords jpWords = new JpWords();
        jpWords.setClassNumber(1);
        jpWords.setCn("我");
        jpWords.setJpRead("わたし");
        jpWords.setJp("私");

        int res = jpService.addJpWords(jpWords);
        if (res == 1) {
            return "insert success!";
        }

        return "insert failed!";
    }

    @GetMapping("/delete")
    public String delete () {

        int res = jpService.delete(1);
        if (res == 1) {
            return "delete success!";
        }

        return "delete failed!";
    }

}
