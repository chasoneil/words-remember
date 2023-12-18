package com.chason.words.controller;


import com.chason.words.common.R;
import com.chason.words.common.error.ErrorCode;
import com.chason.words.entity.jp.*;
import com.chason.words.service.jp.ClassInfoService;
import com.chason.words.service.jp.GroupInfoService;
import com.chason.words.service.jp.JpWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chason
 * @since 2023-12-11
 */
@RestController
@RequestMapping("/jp")
public class JpWordsController {

    @Autowired
    private JpWordsService jpWordsService;

    @Autowired
    private ClassInfoService classInfoService;

    @Autowired
    private GroupInfoService groupInfoService;

    /**
     * list all japan words
     * @return
     */
    @GetMapping("/listAll")
    public R listAll() {
        List<JpWords> list;
        try {
            list = jpWordsService.list();

        } catch (Exception e) {
            return R.error(ErrorCode.REQUEST_BAD, e.getMessage());
        }

        return R.ok(list);
    }

    @PostMapping("/list")
    public R list(@RequestBody JpWordsSearchVO vo) {

        JpWordsPageVO pageVO = new JpWordsPageVO();
        try {
            pageVO.setCount(jpWordsService.listByLikeMapCount(vo));
            pageVO.setData(jpWordsService.listbyLikeMap(vo));
        } catch (Exception e) {
            return R.error(10200, e.getMessage());
        }
        return R.ok(pageVO);
    }

    @GetMapping("/list/class")
    public R listClass () {

        List<ClassInfo> classInfos;

        try {
            classInfos = classInfoService.list();
        } catch (Exception e) {
            return R.error(ErrorCode.REQUEST_BAD, e.getMessage());
        }

        return R.ok(classInfos);
    }

    @GetMapping("/list/group")
    public R listGroup () {

        List<GroupInfo> groupInfos;

        try {
            groupInfos = groupInfoService.list();
        } catch (Exception e) {
            return R.error(ErrorCode.REQUEST_BAD, e.getMessage());
        }

        return R.ok(groupInfos);
    }

    /**
     * save one word
     * @param jpWords
     * @return
     */
    @PostMapping("/add")
    public R save (@RequestBody JpWords jpWords) {

        boolean save;
        try {
            jpWords.setIsRem(0);
            save = jpWordsService.save(jpWords);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
        return save ? R.ok() : R.error();
    }


    /**
     * modify words by id
     * @param jpWords
     * @return
     */
    @PostMapping("/modify")
    public R modify (@RequestBody JpWords jpWords) {
        jpWordsService.updateById(jpWords);
        return R.ok();
    }

    /**
     * save or update jpWords
     * @param jpWords
     * @return
     */
    @PostMapping("/update")
    public R saveOrModify (@RequestBody JpWords jpWords) {

        JpWords saveWords = jpWordsService.getById(jpWords.getId());

        jpWords = convert(saveWords, jpWords);

        boolean updateRes = false;
        String errMsg = "";
        try {
            updateRes = jpWordsService.saveOrUpdate(jpWords);
        } catch (Exception e) {
            errMsg = e.getMessage();
        }

        return  updateRes ? R.ok() : R.error(ErrorCode.UPDATE_USER_ERROR, errMsg);
    }

    @PostMapping("/upload")
    public R uploadFile(@RequestParam("file") MultipartFile uploadFile) {
        return jpWordsService.readFile(uploadFile) ? R.ok() : R.error();
    }


    /**
     * delete words
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    public R delete (@PathVariable Integer id) {
        return jpWordsService.removeById(id) ? R.ok() : R.error();
    }

    private static JpWords convert (JpWords ori, JpWords tar) {

        ori.setClassId(tar.getClassId());
        ori.setGroupId(tar.getGroupId());
        ori.setWMean(tar.getWMean());
        ori.setWWrite(tar.getWWrite());
        ori.setWRead(tar.getWRead());
        return ori;
    }

}
