package com.quangchinh.demo.controller;

import com.quangchinh.demo.dao.*;
import com.quangchinh.demo.dto.NewsDTO;
import com.quangchinh.demo.helper.AuthenticationHelper;
import com.quangchinh.demo.service.CommentService;
import com.quangchinh.demo.service.MajorsService;
import com.quangchinh.demo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/news")
public class NewsController {

    private final NewsService newsService;
    private final CommentService commentService;
    private final MajorsService majorsService;
    private final AuthenticationHelper authenticationHelper;

    @Autowired
    NewsController(NewsService newsService, CommentService commentService, MajorsService majorsService,
                   AuthenticationHelper authenticationHelper) {
        this.newsService = newsService;
        this.commentService = commentService;
        this.majorsService = majorsService;
        this.authenticationHelper = authenticationHelper;
    }

    @GetMapping
    public List<News> getAllNews() {
        return newsService.getAllShortenedContent();
    }

    @GetMapping("/mypost")
    public List<News> getMyNews() {
        User user = authenticationHelper.getLoggedInUser();
        return newsService.getNewsByUserId(user.getId());
    }

    @GetMapping("/pendingpost")
    public List<News> getPendingNews() {
        return newsService.getPendingNews();
    }

    @GetMapping("/mypendingpost")
    public List<News> getMyPendingNews() {
        User user = authenticationHelper.getLoggedInUser();
        return newsService.getPendingPostByUserId(user.getId());
    }

    @GetMapping("/recent-post")
    public List<News> get5RecentNews() {
        return newsService.get5RecentNews();
    }

    @GetMapping("/most-views")
    public List<News> get5MostView() {
        return newsService.get5MostView();
    }

    @PostMapping("/create")
    public ResponseEntity<News> createNews(@RequestBody NewsDTO newsDto) {
        User user = authenticationHelper.getLoggedInUser();
        String majorId = newsDto.getMajorsId();
        Majors majors = majorsService.getById(majorId);
        if (user == null) {
            return null;
        }
        News news = new News();
        news.setTitle(newsDto.getTitle());
        news.setImage(newsDto.getImage());
        news.setContent(newsDto.getContent());
        news.setView(newsDto.getView());
        if (isAdmin(user)) {
            news.setApproved(true);
        }
        news.setUser(user);
        news.setMajors(majors);
        news.setCreateDate(new Date());
        News createdNews = newsService.create(news);
        return ResponseEntity.ok(createdNews);
    }

    @GetMapping("/{id}")
    public News getNews(@PathVariable String id) {
        News news = newsService.getById(id);
        news.setView(news.getView() + 1);
        newsService.updateNews(news);
        return news;
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

    @PutMapping("/approve/{newsId}")
    public News approvedNews(@PathVariable String newsId) {
        return newsService.approveNews(newsId);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteNews(@PathVariable String id) {
        commentService.deleteCommentByNewsId(id);
        return newsService.deleteNews(id);
    }

    private Boolean isAdmin(User user) {
        return user.getRoles().stream().anyMatch(role -> "ADMIN".equals(role.getName()));
    }
}
