/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serviceimpl;

import com.entity.Student;
import com.repository.Studentrepository;
import com.service.Studentservice;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lapop1
 */
@Service(value = "studentService")
public class StudentServiceImpl extends GenericServiceImpl<Student> implements Studentservice {

    private static Logger logger = Logger.getLogger(StudentServiceImpl.class);
    private final Studentrepository repository;

    @Autowired
    public StudentServiceImpl(Studentrepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<Student> findAllstudent() {
        return repository.findAllStudent();
    }

    @Override
    public boolean checkUsed(Long studentId) {
        return repository.checkUsed(studentId);
    }

    @Override
    public Student findByCode(String studentCode) {
        return repository.findByCode(studentCode);
    }

    @Override
    public void deleteStudent(Long studentId) {
        repository.delete(studentId);
    }

}
