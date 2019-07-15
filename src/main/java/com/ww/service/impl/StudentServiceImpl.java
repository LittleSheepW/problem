package com.ww.service.impl;

import com.ww.entity.StudentEntity;
import com.ww.repository.StudentRepository;
import com.ww.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Sun
 * @create: 2019-06-13 16:31
 * @version: v1.0
 */
@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<StudentEntity> listStudent() {
        StudentEntity studentBo = new StudentEntity();
        studentBo.setId(1);
        studentBo.setName("sun");
        return studentRepository.findAll(Example.of(studentBo));
    }
}
