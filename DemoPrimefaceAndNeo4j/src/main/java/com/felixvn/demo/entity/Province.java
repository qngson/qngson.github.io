package com.felixvn.demo.entity;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.support.index.IndexType;

@NodeEntity
@TypeAlias("Province")
public class Province extends ModelObject {

    /* private declare property */
    @Indexed(unique = true, indexType = IndexType.FULLTEXT, indexName = "ProvinceCode")
    private String code;

    @Indexed(indexType = IndexType.FULLTEXT, indexName = "ProvinceName")
    private String name;

    @RelatedTo(type = "PROVINCE_COUNTRY")
    private Country country;

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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

}
