/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.felixvn.demo.entity.service.impl;

import com.felixvn.demo.entity.Province;
import com.felixvn.demo.entity.reporitory.ProvinceRepository;
import com.felixvn.demo.entity.service.ProvinceService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

/**
 *
 * @author Phuc
 */
@Service(value = "provinceService")
public class ProvinceServiceImpl extends GenericServiceImpl<Province> implements ProvinceService {

    private static Logger logger = Logger.getLogger(ProvinceServiceImpl.class);
    private final ProvinceRepository repository;

    @Autowired
    public ProvinceServiceImpl(ProvinceRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<Province> findAllValid(String countryCode) {
    return repository.findAllValid(countryCode);
    }
    @Override
    public Province findByCode(String provinceCode) {
        return repository.findByCode(provinceCode);
    }
    @Override
    public boolean checkUsed(Long provinceId) {
        return repository.checkUsed(provinceId);
    }
    @Override
    public void deleteProvince(Long provinceId){
        repository.deleteProvince(provinceId);
    }
    @Override
    public List<Province> find(String code,String name, String coutryCode) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("coutryCode", coutryCode);
        String START = "START n=node:CountryCode(code={coutryCode}) MATCH n<-[:PROVINCE_COUNTRY]-pro";
        
        if(code != null && !code.trim().isEmpty()) {
            params.put("code", code);
            START = "START pro=node:ProvinceCode(code={code}) ";
        } 
        
        String MATCH = "";
        
        String WHERE = "";
        
        if(name != null && !name.trim().isEmpty()){
            name = ".*"+name.trim()+".*";
            params.put("name", name);            
            WHERE = " WHERE LOWER(pro.name) =~ LOWER({name}) ";
        }        
        
        String RETURN = " RETURN pro";
        String ORDER_BY = " ORDER BY pro.order, pro.name ";
        //String SKIP = "";
        //String LIMIT = "";
        String squery = START + MATCH + WHERE + RETURN + ORDER_BY ;
        return repository.query(squery, params).as(List.class);
    }
}
