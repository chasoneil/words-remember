package com.chason.words.service.index;

import org.springframework.stereotype.Service;

@Service
public class IndexServiceImpl implements IndexService {

    @Override
    public String getIndex() {
        return "index page1";
    }

}
