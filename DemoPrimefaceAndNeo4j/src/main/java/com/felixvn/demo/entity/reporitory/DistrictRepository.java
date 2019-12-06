/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.felixvn.demo.entity.reporitory;

import com.felixvn.demo.entity.District;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 *
 * @author Phuc
 */
@Lazy
public interface DistrictRepository extends GraphRepository<District>{
    @Query("MATCH (n:District) RETURN n")
    List<District> findAllDistrict();
    @Query("Start n=node:CountryCode(code={0}) MATCH n<-[:PROVINCE_COUNTRY]-p<-[:DISTRICT_PROVINCE]-d RETURN d")
    List<District> findByCountry(String countryCode);
    @Query("START n=node({0}) MATCH n<-[:DISTRICT_PROVINCE]-d RETURN d")
    List<District> findByProvinceId(Long provinceId);
    @Query("START n=node:ProvinceCode(code={0}) MATCH n<-[:DISTRICT_PROVINCE]-d RETURN d")
    List<District> findByProvinceCode(String provinceCode);
    @Query("START n=node:DistrictCode(code={0}) RETURN n LIMIT 1")
    District findByCode(String districtCode);
    @Query("START n=node({0}) MATCH n<-[r]-o WITH count(n) as cc RETURN cc > 0")
    boolean checkUsed(Long districtId);
    @Query("START n=node({0}) MATCH n-[r]->o DELETE r,n ")
    void deleteDistrict(Long districtId);

}
