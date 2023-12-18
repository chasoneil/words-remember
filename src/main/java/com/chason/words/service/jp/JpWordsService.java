package com.chason.words.service.jp;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chason.words.entity.jp.JpWords;
import com.chason.words.entity.jp.JpWordsSearchVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chason
 * @since 2023-12-11
 */
public interface JpWordsService extends IService<JpWords> {

    List<JpWords> listbyLikeMap(JpWordsSearchVO vo);

    boolean readFile(MultipartFile file);

    int listByLikeMapCount(JpWordsSearchVO vo);
}
