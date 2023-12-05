package com.chason.test01.service.jp;


import com.chason.test01.entity.jp.JpWords;

import java.util.List;

public interface JpService {

    List<JpWords> findAll();

    List<JpWords> findByClassNumber(int classNumber);

    int addJpWords(JpWords jpWords);

    int delete(int id);

}
