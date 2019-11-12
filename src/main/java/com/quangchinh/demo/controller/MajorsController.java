package com.quangchinh.demo.controller;

import com.quangchinh.demo.dao.Comment;
import com.quangchinh.demo.dao.Majors;
import com.quangchinh.demo.dao.News;
import com.quangchinh.demo.dao.User;
import com.quangchinh.demo.service.MajorsService;
import com.quangchinh.demo.service.NewsService;
import com.quangchinh.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/major")
public class MajorsController {

    private final MajorsService majorsService;
    private final NewsService newsService;

    @Autowired
    public MajorsController(MajorsService majorsService, NewsService newsService) {
        this.majorsService = majorsService;
        this.newsService = newsService;
    }

    @GetMapping
    public List<Majors> getAllMajors() {
        return majorsService.getAll();
    }

    @GetMapping("/news/{id}")
    public List<News> getNewsMajor(@PathVariable String id) {
        return newsService.getByMajorId(id);
    }

    @PostMapping
    public Majors createMajor(@RequestBody Majors majors) {
        return majorsService.create(majors);
    }
}

