package com.quangchinh.demo.repository;

import com.quangchinh.demo.dao.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CmtRepository extends JpaRepository<Comment, String> {
}
