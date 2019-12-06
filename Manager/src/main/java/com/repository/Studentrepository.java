/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repository;

import com.entity.Student;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 *
 * @author Lapop1
 */
@Lazy
public interface Studentrepository extends GraphRepository<Student> {

    @Query("MATCH (n:Student) RETURN n")
    List<Student> findAllStudent();
     @Query("START n=node:StudentCode(code={0}) RETURN n LIMIT 1")
    Student findByCode(String studentCode);
     @Query("START n=node({0}) MATCH n<-[r]-o WITH count(n) as cc RETURN cc > 0")
    boolean checkUsed(Long studentId);
    @Query("START n=node({0}) MATCH n-[r]->o DELETE r,n ")
    void deleteStudent(String studentId);
}
