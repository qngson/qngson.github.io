/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

/**
 *
 * @author Lapop1
 */
@NodeEntity
@TypeAlias("Student")
public class Student extends ModelObject {
    
    @NotNull
    @Indexed(unique = true, indexType = IndexType.FULLTEXT, indexName = "StudentCode")
    private String code;
    @Indexed(indexType = IndexType.FULLTEXT, indexName = "StudentName")
    private String name;
    @Indexed(indexType = IndexType.FULLTEXT, indexName = "StudentBirth")
    private String birth;
    @Indexed(indexType = IndexType.FULLTEXT, indexName = "StudentProvince")
    private String province;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
    
    
    
}
