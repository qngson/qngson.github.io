/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.entity.Student;
import java.util.List;
import org.springframework.data.neo4j.annotation.Query;

/**
 *
 * @author Lapop1
 */
public interface Studentservice extends GenericService<Student> {

    public List<Student> findAllstudent();

    public Student findByCode(String studentCode);

    boolean checkUsed(Long studentId);

    public void deleteStudent(Long studentId);

    
}
