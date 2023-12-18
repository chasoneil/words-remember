package com.chason.words.service.jp;

import com.chason.words.entity.jp.JpWords;
import com.chason.words.entity.jp.JpWordsPageVO;
import com.chason.words.entity.jp.JpWordsSearchVO;
import com.chason.words.mapper.JpWordsMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chason.words.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
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
    public List<JpWords> listbyLikeMap(JpWordsSearchVO vo) {
        return jpWordsMapper.listByLikeMap(parseParam(vo, "data"));
    }

    @Override
    public int listByLikeMapCount(JpWordsSearchVO vo) {
        return jpWordsMapper.listByLikeMapCount(parseParam(vo, "count"));
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

                if (StringUtil.isEmpty(line)) {
                    continue;
                }

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
                if (split[1].equalsIgnoreCase("-")) {
                    words.setWWrite(split[2]);
                } else {
                    words.setWWrite(split[1]);
                }
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

    private Map<String, Object> parseParam (JpWordsSearchVO vo, String type) {

        Map<String, Object> param = new HashMap<>();

        if (!StringUtil.isEmpty(vo.getClassId())) {
            param.put("class_id", Integer.parseInt(vo.getClassId()));
        }

        if (!StringUtil.isEmpty(vo.getGroupId())) {
            param.put("group_id", Integer.parseInt(vo.getGroupId()));
        }

        if (!StringUtil.isEmpty(vo.getWMean())) {
            param.put("w_mean", vo.getWMean());
        }

        if (type.equals("data")) {
            param.put("pageNo", vo.getPageNo());
            param.put("pageSize", vo.getPageSize());
        }

        return param;
    }
}
