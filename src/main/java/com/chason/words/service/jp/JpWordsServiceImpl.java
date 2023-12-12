package com.chason.words.service.jp;

import com.chason.words.entity.jp.JpWords;
import com.chason.words.mapper.JpWordsMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chason
 * @since 2023-12-11
 */
@Service
public class JpWordsServiceImpl extends ServiceImpl<JpWordsMapper, JpWords> implements JpWordsService {

    @Autowired
    private JpWordsMapper jpWordsMapper;

    @Override
    public List<JpWords> listbyLikeMap(Map<String, Object> param) {
        return jpWordsMapper.listByLikeMap(param);
    }

    @Override
    public boolean readFile(MultipartFile file) {

        if (file == null) {
            return false;
        }

        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            int classId = -1;
            while ((line = reader.readLine()) != null) {

                if (line.startsWith("*")) {
                    classId = Integer.parseInt(line.replace("*", ""));
                    continue;
                }

                String[] split = line.split("#");
                JpWords words = new JpWords();

                if (classId != -1) {
                    words.setClassId(classId);
                }
                words.setWMean(split[0]);
                words.setWRead(split[2]);
                words.setWWrite(split[1]);
                words.setIsRem(0);
                this.save(words);
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
