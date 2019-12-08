package com.quangchinh.demo.service;

import com.quangchinh.demo.dao.News;
import com.quangchinh.demo.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public News create(News news) {
        return newsRepository.save(news);
    }

    @Override
    public List<News> getAll() {
        return newsRepository.findAll();
    }

    @Override
    public List<News> getAllShortenedContent() {
        List<News> allNews = getAll();
        return allNews.stream().map(news -> {
            News shortenedNews = new News();
            shortenedNews.setId(news.getId());
            shortenedNews.setTitle(news.getTitle());
            shortenedNews.setImage(news.getImage());
            shortenedNews.setApproved(news.isApproved());
            String shortenedContent = news.getContent().substring(0, 150) + "...";
            shortenedNews.setContent(shortenedContent);
            shortenedNews.setView(news.getView());
            return shortenedNews;
        }).collect(Collectors.toList());
    }

    @Override
    public List<News> getNewsByMajorsId(String id) {
        return newsRepository.findNewsByMajorsId(id);
    }

    @Override
    public News getById(String id) {
        Optional<News> newsOptional = newsRepository.findById(id);
        return newsOptional.orElse(null);
    }

    @Override
    public News updateNews(News news) {
        return newsRepository.save(news);
    }

    @Override
    public boolean deleteNews(String id) {
        newsRepository.deleteById(id);
        return true;
    }
}
