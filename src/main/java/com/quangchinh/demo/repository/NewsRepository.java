package com.quangchinh.demo.repository;

import com.quangchinh.demo.dao.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, String> {
    List<News> findNewsByMajorsId(String majorId);

    List<News> findNewsByUserId(String userName);

    List<News> findTop5ByOrderByCreateDateDesc();

    List<News> findTop5ByOrderByViewDesc();
}
