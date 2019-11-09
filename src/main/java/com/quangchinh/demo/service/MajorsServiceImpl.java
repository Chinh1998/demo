package com.quangchinh.demo.service;

import com.quangchinh.demo.dao.Majors;
import com.quangchinh.demo.repository.MajorsRepository;

import java.util.List;
import java.util.Optional;

public class MajorsServiceImpl implements MajorsService {

    private final MajorsRepository majorsRepository;

    public MajorsServiceImpl(MajorsRepository majorsRepository) {
        this.majorsRepository = majorsRepository;
    }

    @Override
    public Majors create(Majors majors) {
        return majorsRepository.save(majors);
    }

    @Override
    public List<Majors> getAll() {
        return majorsRepository.findAll();
    }

    @Override
    public Majors getById(String id) {
        Optional<Majors> majorsOptional= majorsRepository.findById(id);
        return majorsOptional.orElse(null);
    }

    @Override
    public Majors updateMajors(Majors majors) {
        return majorsRepository.save(majors);
    }

    @Override
    public boolean deleteMajors(String id) {
        majorsRepository.deleteById(id);
        return true;
    }
}
