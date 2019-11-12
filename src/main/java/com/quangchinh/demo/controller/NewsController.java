package com.quangchinh.demo.controller;

import com.quangchinh.demo.dao.Comment;
import com.quangchinh.demo.dao.Majors;
import com.quangchinh.demo.dao.News;
import com.quangchinh.demo.dao.User;
import com.quangchinh.demo.dto.NewsDTO;
import com.quangchinh.demo.service.CommentService;
import com.quangchinh.demo.service.MajorsService;
import com.quangchinh.demo.service.NewsService;
import com.quangchinh.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/news")
public class NewsController {

    private final NewsService newsService;
    private final UserService userService;
    private final CommentService commentService;
    private final MajorsService majorsService;

    @Autowired
    NewsController(NewsService newsService, UserService userService, CommentService commentService, MajorsService majorsService) {
        this.newsService = newsService;
        this.userService = userService;
        this.commentService = commentService;
        this.majorsService = majorsService;
    }

    @GetMapping
    public List<News> getAllNews() {
        return newsService.getAllShortenedContent();
    }

    @PostMapping()
    public News createNews(@RequestBody NewsDTO newsDto) {
        String userId = newsDto.getUserId();
        User user = userService.getById(userId);
        String majorId = newsDto.getMajorId();
        Majors majors = majorsService.getById(majorId);
        if (user == null) {
            return null;
        }
        News news = new News();
        news.setTitle(newsDto.getTitle());
        news.setImage(newsDto.getImage());
        news.setContent(newsDto.getContent());
        news.setView(newsDto.getView());
        news.setApproved(newsDto.isApproved());
        news.setUser(user);
        news.setMajors(majors);
        return newsService.create(news);
    }

    @GetMapping("/{id}")
    public News getNews(@PathVariable String id) {
        return newsService.getById(id);
    }

    @GetMapping("/{id}/comments")
    public List<Comment> getNewsComments(@PathVariable String id) {
        return commentService.getByNewsId(id);
    }

    @PutMapping("/{id}")
    public News updateNews(@PathVariable String id, @RequestBody News news) {
        news.setId(id);
        return newsService.updateNews(news);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteNews(@PathVariable String id) {
        return newsService.deleteNews(id);
    }
}
