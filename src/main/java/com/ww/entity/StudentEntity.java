package com.ww.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author: Sun
 * @create: 2019-06-13 11:28
 * @version: v1.0
 */
@Entity
@Table
@Data
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer studentId;

    private String name;

    private Long createTime;

    private Integer creator;

}
