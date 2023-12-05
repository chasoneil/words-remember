package com.chason.test01.service.jp;


import com.chason.test01.entity.jp.JpWords;
import com.chason.test01.mapper.JpWordsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpServiceImpl implements JpService {

    @Autowired
    private JpWordsMapper jpWordsMapper;

    @Override
    public List<JpWords> findAll() {
        return jpWordsMapper.findAll();
    }

    @Override
    public List<JpWords> findByClassNumber(int classNumber) {
        return jpWordsMapper.findByClassNumber(classNumber);
    }

    @Override
    public int addJpWords(JpWords jpWords) {
        return jpWordsMapper.insert(jpWords);
    }

    @Override
    public int delete(int id) {
        return jpWordsMapper.removeById(id);
    }


}
