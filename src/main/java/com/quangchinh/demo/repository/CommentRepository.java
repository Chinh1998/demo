package com.quangchinh.demo.repository;

import com.quangchinh.demo.dao.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, String> {

    List<Comment> findAllByNewsId(String newId);
}
