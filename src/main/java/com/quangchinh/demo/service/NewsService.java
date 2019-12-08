package com.quangchinh.demo.service;

import com.quangchinh.demo.dao.News;

import java.util.List;

public interface NewsService {

    News create(News news);

    List<News> getAll();

    List<News> getAllShortenedContent();

    List<News> getNewsByMajorsId(String id);

    News getById(String id);

    News updateNews(News news);

    boolean deleteNews(String id);
}
