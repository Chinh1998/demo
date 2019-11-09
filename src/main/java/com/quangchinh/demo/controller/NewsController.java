package com.quangchinh.demo.controller;

import com.quangchinh.demo.dao.Comment;
import com.quangchinh.demo.dao.News;
import com.quangchinh.demo.dao.User;
import com.quangchinh.demo.dto.NewsDTO;
import com.quangchinh.demo.service.CommentService;
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

    @Autowired
    NewsController(NewsService newsService, UserService userService, CommentService commentService) {
        this.newsService = newsService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @GetMapping
    public List<News> getAllNews() {
        return newsService.getAll();
    }

    @PostMapping()
    public News createNews(@RequestBody NewsDTO newsDto) {
        String userId = newsDto.getUserId();
        User user = userService.getById(userId);

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
