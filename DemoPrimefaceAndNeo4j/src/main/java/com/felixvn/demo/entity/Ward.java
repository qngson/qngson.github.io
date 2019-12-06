/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.felixvn.demo.entity;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.support.index.IndexType;

/**
 *
 * @author Lapop1
 */
@NodeEntity
@TypeAlias("Ward")
public class Ward extends ModelObject {
    
    @Indexed(unique = true, indexType = IndexType.FULLTEXT, indexName = "WardCode")
    private String code;
    
    @Indexed(indexType = IndexType.FULLTEXT, indexName = "WardName")
    private String name;
    
    @RelatedTo(type = "WARD_DISTRICT")
	private District district;
            
     @RelatedTo(type = "WARD_PROVINCE")
	private Province province; 

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

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
     
     
}
