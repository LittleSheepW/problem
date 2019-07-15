package com.ww.repository;

import com.ww.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @author: Sun
 * @create: 2019-06-13 11:29
 * @version: v1.0
 */
@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

}
