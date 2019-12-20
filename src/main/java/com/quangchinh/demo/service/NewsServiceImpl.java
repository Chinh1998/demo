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
        List<News> majorNews= newsRepository.findNewsByMajorsId(id);
        return majorNews.stream().map(majorNew ->{
            News shortenedNews = new News();
            shortenedNews.setId(majorNew.getId());
            shortenedNews.setTitle(majorNew.getTitle());
            shortenedNews.setImage(majorNew.getImage());
            shortenedNews.setApproved(majorNew.isApproved());
            String shortenedContent = majorNew.getContent().substring(0, 150) + "...";
            shortenedNews.setContent(shortenedContent);
            shortenedNews.setView(majorNew.getView());
            return shortenedNews;
        }).collect(Collectors.toList());
    }

    @Override
    public List<News> getNewsByUserId(String userId) {
        List<News> myNews= newsRepository.findNewsByUserId(userId);
        return myNews.stream().map(myNew ->{
            News shortenedNews = new News();
            shortenedNews.setId(myNew.getId());
            shortenedNews.setTitle(myNew.getTitle());
            shortenedNews.setImage(myNew.getImage());
            shortenedNews.setApproved(myNew.isApproved());
            String shortenedContent = myNew.getContent().substring(0, 150) + "...";
            shortenedNews.setContent(shortenedContent);
            shortenedNews.setView(myNew.getView());
            return shortenedNews;
        }).collect(Collectors.toList());
    }

    @Override
    public List<News> get5RecentNews() {
        return newsRepository.findTop5ByOrderByCreateDateDesc();
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

    @Override
    public List<News> get5MostView() {
        return newsRepository.findTop5ByOrderByViewDesc();
    }
}
