package com.n2014758030.main.service;

import com.n2014758030.main.domain.Basic;
import com.n2014758030.main.repository.BasicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicService {

    private BasicRepository basicRepository;

    public BasicService(BasicRepository basicRepository) {
        this.basicRepository = basicRepository;
    }

    public void save(Basic basic){
        basicRepository.save(basic);
    }

    public List<Basic> findBasicByAll() {
        return basicRepository.findAll();
    }

    public Basic findBasicByIdx(Long idx) {

        return basicRepository.findById(idx).orElse(new Basic());
    }

    public void updateBasic(Basic basic) {
        basic = basicRepository.save(basic);
    }

    public void deleteBasic(Basic basic) {
        basicRepository.delete(basic);
    }
}
