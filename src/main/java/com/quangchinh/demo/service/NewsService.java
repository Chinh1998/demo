package com.quangchinh.demo.service;

import com.quangchinh.demo.dao.Comment;
import com.quangchinh.demo.dao.News;

import java.util.List;

public interface NewsService {

    News create(News news);

    List<News> getAll();

    List<News> getAllShortenedContent();

    List<News> getByMajorId(String majorId);

    News getById(String id);

    News updateNews(News news);

    boolean deleteNews(String id);
}
