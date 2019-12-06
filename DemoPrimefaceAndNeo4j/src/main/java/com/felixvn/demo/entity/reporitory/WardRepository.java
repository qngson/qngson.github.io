/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.felixvn.demo.entity.reporitory;

import com.felixvn.demo.entity.Province;
import com.felixvn.demo.entity.Ward;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 *
 * @author Lapop1
 */
@Lazy
public interface WardRepository extends GraphRepository<Ward>{
    @Query("MATCH (n:Ward) RETURN n")
    List<Ward> findAllWard();
    @Query("Start n=node:WardCode(code={0}) MATCH n-[:WARD_PROVINCE]->p  RETURN p")
    List<Ward> findByCountry(String countryCode);
    
    @Query("START n=node({0}) MATCH n<-[r]-o WITH count(n) as cc RETURN cc > 0")
    boolean checkUsed(Long wardId);
    
    @Query("START n=node:WardCode(code={0}) RETURN n LIMIT 1")
    Ward findByCode(String wardCode);
    
    @Query("START n=node({0}) MATCH n-[r]->o DELETE r,n ")
    void deleteWard(Long wardId);

   
    
}
