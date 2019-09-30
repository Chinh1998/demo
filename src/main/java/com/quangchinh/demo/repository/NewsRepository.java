package com.quangchinh.demo.repository;

import com.quangchinh.demo.dao.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, String> {
}
