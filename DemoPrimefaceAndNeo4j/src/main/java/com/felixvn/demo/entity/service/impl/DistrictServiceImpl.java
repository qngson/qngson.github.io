/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.felixvn.demo.entity.service.impl;

import com.felixvn.demo.entity.District;
import com.felixvn.demo.entity.Province;
import com.felixvn.demo.entity.reporitory.DistrictRepository;
import com.felixvn.demo.entity.reporitory.ProvinceRepository;
import com.felixvn.demo.entity.service.DistrictService;
import com.felixvn.demo.entity.service.ProvinceService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lapop1
 */
@Service(value = "districtService")
public class DistrictServiceImpl extends GenericServiceImpl<District> implements DistrictService {

    private static Logger logger = Logger.getLogger(DistrictServiceImpl.class);
    private final DistrictRepository repository;

    @Autowired
    public DistrictServiceImpl(DistrictRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<District> findAllValid(String countryCode) {
        return repository.findByCountry(countryCode);
    }

    @Override
    public District findByCode(String districtCode) {
        return repository.findByCode(districtCode);
    }

    @Override
    public boolean checkUsed(Long districtId) {
        return repository.checkUsed(districtId);
    }

    @Override
    public void deleteDistrict(Long districtId) {
       repository.deleteDistrict(districtId);
    
    }
    @Override
    public List<District> findByProvince(Long provinceId) {
        return repository.findByProvinceId(provinceId);
    }

    

   

    

}
