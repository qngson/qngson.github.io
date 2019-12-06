/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.felixvn.demo.entity.reporitory;

import com.felixvn.demo.entity.Province;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 *
 * @author Phuc
 */
@Lazy
public interface ProvinceRepository extends GraphRepository<Province>{
    @Query("Start n=node:CountryCode(code={0}) MATCH n<-[:PROVINCE_COUNTRY]-p WHERE p.deleted = false RETURN p")
    List<Province> findAllValid(String countryCode);
    @Query("START n=node:ProvinceCode(code={0}) RETURN n LIMIT 1")
    Province findByCode(String provinceCode);
    @Query("START n=node({0}) MATCH n<-[r]-o WITH count(n) as cc RETURN cc > 0")
    boolean checkUsed(Long provinceId);
     @Query("START n=node({0}) MATCH n-[r]->o DELETE r,n ")
    void deleteProvince(Long provinceId);
}
