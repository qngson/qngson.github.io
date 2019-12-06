/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.felixvn.demo.entity;

/**
 *
 * @author Phuc
 */
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.support.index.IndexType;

@NodeEntity
@TypeAlias("District")
public class District extends ModelObject{
	

	/* private declare property */
	@NotNull
	@Indexed(unique = true, indexType = IndexType.FULLTEXT, indexName = "DistrictCode")
	private String code;
	
	@Indexed(indexType = IndexType.FULLTEXT, indexName = "DistrictName")
	private String name;

	
	@RelatedTo(type = "DISTRICT_PROVINCE")
	private Province province;
	
	
	
	/* getter and setter method */
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

	

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

    public void setDistrict(Province province) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
