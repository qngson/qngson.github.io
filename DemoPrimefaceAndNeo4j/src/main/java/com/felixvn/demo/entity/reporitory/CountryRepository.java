/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.felixvn.demo.entity.reporitory;

import com.felixvn.demo.entity.Country;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 *
 * @author Phuc
 */
@Lazy
public interface CountryRepository extends GraphRepository<Country>{
    
    @Query("START n=node:CountryCode(code={0}) RETURN n LIMIT 1")
    Country findByCode(String countryCode);
    
     
    

}
