package com.quangchinh.demo.controller;

import com.quangchinh.demo.dao.Comment;
import com.quangchinh.demo.dao.News;
import com.quangchinh.demo.dao.User;
import com.quangchinh.demo.dto.CommentDTO;
import com.quangchinh.demo.service.CmtService;
import com.quangchinh.demo.service.NewsService;
import com.quangchinh.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cmt")
public class CmtController {
    private final CmtService cmtService;
    private final UserService userService;
    private final NewsService newsService;

    @Autowired
    CmtController(CmtService cmtService, UserService userService, NewsService newsService){
        this.cmtService = cmtService;
        this.userService = userService;
        this.newsService = newsService;
    }
    @GetMapping
    public List<Comment> getAllComment() {
        return cmtService.getAll();
    }

    @PostMapping
    public Comment createComment(@RequestBody CommentDTO commentDTO) {
        String userId = commentDTO.getUserId();
        User user = userService.getById(userId);
        String newId = commentDTO.getNewsId();
        News news = newsService.getById(newId);

        if (user == null && news == null) {
            return null;
        }

        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setContent_cmt(commentDTO.getContent_cmt());
        comment.setUser(user);
        comment.setNews(news);

        return cmtService.create(comment);
    }

    @GetMapping("/{id}")
    public Comment getComment(@PathVariable String id) {
        return cmtService.getById(id);
    }

    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable String id, @RequestBody Comment comment) {
        comment.setId(id);
        return cmtService.updateComment(comment);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteComment(@PathVariable String id) {
        return cmtService.deleteComment(id);
    }
}
