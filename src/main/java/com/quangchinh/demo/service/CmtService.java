package com.quangchinh.demo.service;

import com.quangchinh.demo.dao.Comment;

import java.util.List;

public interface CmtService {
    Comment create(Comment comment);

    List<Comment> getAll();

    Comment getById(String id);

    Comment updateComment(Comment comment);

    boolean deleteComment(String id);
}
