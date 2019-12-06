/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.felixvn.demo.entity.service;

import com.felixvn.demo.entity.District;
import com.felixvn.demo.entity.Province;
import java.util.List;

/**
 *
 * @author Lapop1
 */
public interface DistrictService extends GenericService<District> {
    
    public List<District> findAllValid(String countryCode);
    public District findByCode(String districtCode);
    boolean checkUsed(Long districtId);
    void  deleteDistrict(Long districtId);
    public List<District> findByProvince(Long provinceId);
    
}
