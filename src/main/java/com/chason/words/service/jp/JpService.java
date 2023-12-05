package com.chason.words.service.jp;


import com.chason.words.entity.jp.JpWords;

import java.util.List;

public interface JpService {

    List<JpWords> findAll();

    List<JpWords> findByClassNumber(int classNumber);

    int addJpWords(JpWords jpWords);

    int delete(int id);

}
