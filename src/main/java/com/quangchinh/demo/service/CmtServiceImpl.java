package com.quangchinh.demo.service;

import com.quangchinh.demo.dao.Comment;
import com.quangchinh.demo.repository.CmtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CmtServiceImpl implements CmtService  {

    private final CmtRepository cmtRepository;

    @Autowired
    public CmtServiceImpl(CmtRepository cmtRepository) {
        this.cmtRepository = cmtRepository;
    }
    @Override
    public Comment create(Comment comment) {
        return cmtRepository.save(comment);
    }

    @Override
    public List<Comment> getAll() {
        return cmtRepository.findAll();
    }

    @Override
    public Comment getById(String id) {
        Optional<Comment> cmtOptional= cmtRepository.findById(id);
        return cmtOptional.orElse(null);
    }

    @Override
    public Comment updateComment(Comment comment) {
        return cmtRepository.save(comment);
    }

    @Override
    public boolean deleteComment(String id) {
        cmtRepository.deleteById(id);
        return true;
    }
}
