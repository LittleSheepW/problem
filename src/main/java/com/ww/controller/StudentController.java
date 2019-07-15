package com.ww.controller;

import com.ww.api.ApiResult;
import com.ww.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Sun
 * @create: 2019-07-12 13:39
 * @version: v1.0
 */

@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 测试JPA Example是否可以进行and查询？   对应0711 第1个问题
     * 答案：select studentbo0_.id as id1_0_, studentbo0_.create_time as create_t2_0_, studentbo0_.creator as creator3_0_, studentbo0_.name as name4_0_,
     * studentbo0_.student_id as student_5_0_ from student_bo studentbo0_ where studentbo0_.name=? and studentbo0_.id=1  是可以进行and查询的
     * @return
     */
    @RequestMapping(value = "/list")
    public ApiResult listStudent() {
        return ApiResult.success(studentService.listStudent());
    }
}
