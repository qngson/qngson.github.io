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
import org.springframework.data.neo4j.support.index.IndexType;

@NodeEntity
@TypeAlias("Country")
public class Country extends ModelObject{
	

	/* private declare property */
	@NotNull
	@Indexed(unique = true, indexType = IndexType.FULLTEXT, indexName = "CountryCode")
	private String code;
	
	@Indexed(indexType = IndexType.FULLTEXT, indexName = "CountryName")
	private String name;

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

}
