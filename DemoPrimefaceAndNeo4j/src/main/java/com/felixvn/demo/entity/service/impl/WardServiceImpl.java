/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.felixvn.demo.entity.service.impl;

import com.felixvn.demo.entity.Province;
import com.felixvn.demo.entity.Ward;
import com.felixvn.demo.entity.reporitory.DistrictRepository;
import com.felixvn.demo.entity.reporitory.WardRepository;
import com.felixvn.demo.entity.service.WardService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lapop1
 */
@Service(value = "wardService")
public class WardServiceImpl extends GenericServiceImpl<Ward> implements WardService {

    private static Logger logger = Logger.getLogger(WardServiceImpl.class);
    private final WardRepository repository;

    @Autowired
    public WardServiceImpl(WardRepository repository) {
        super(repository);
        this.repository = repository;
    }
    

    @Override
    public List<Ward> findAllValid(String countryCode) {
//        List<Ward> wards = new ArrayList<Ward>();
//        Ward w1 = new Ward();
//        w1.setCode("1");
//        w1.setName("abc");
//        
//        Ward w2 = new Ward();
//        w2.setCode("2");
//        w2.setName("egh");
//        
//        wards.add(w1);
//        wards.add(w2);
//        return wards;
            return repository.findAllWard();

    }

    @Override
    public boolean checkUsed(Long wardId) {
        return repository.checkUsed(wardId);
    }

    @Override
    public Ward findByCode(String wardCode) {
        return repository.findByCode(wardCode);
    }

    @Override
    public List<Ward> findAllWard() {
        return repository.findAllWard();
    }

    @Override
    public void deleteWard(Long wardId) {
        
        repository.deleteWard(wardId);
    }

    
    
}
