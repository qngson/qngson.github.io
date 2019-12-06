/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.felixvn.demo.entity.service;

import com.felixvn.demo.entity.Province;
import java.util.List;

/**
 *
 * @author Phuc
 */
public interface ProvinceService extends GenericService<Province>{

    public List<Province> findAllValid(String countryCode);
    public Province findByCode(String provinceCode);
    boolean checkUsed(Long provinceId);
    public List<Province> find(String code,String name,String countryCode);
    public void deleteProvince(Long provinceId);
    //public List<Province> findByRegion(String regionCode);
}

