package com.ww.service;

import com.ww.entity.StudentEntity;

import java.util.List;

/**
 * @author: Sun
 * @create: 2019-06-13 16:31
 * @version: v1.0
 */
public interface StudentService {

    /**
     * 条件查询学生列表
     * @return
     */
    List<StudentEntity> listStudent();
}
