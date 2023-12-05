package com.chason.test01.entity.jp;

import lombok.Data;

@Data
public class JpWords {

    private int id;

    private String cn;

    private String jpRead;

    private String jp;

    private int classNumber;

    @Override
    public String toString() {
        return "JpWords{" +
                "id=" + id +
                ", cn='" + cn + '\'' +
                ", jpRead='" + jpRead + '\'' +
                ", jp='" + jp + '\'' +
                ", classNumber=" + classNumber +
                '}';
    }
}
