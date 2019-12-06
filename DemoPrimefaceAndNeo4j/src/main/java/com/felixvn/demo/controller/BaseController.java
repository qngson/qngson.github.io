/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.felixvn.demo.controller;

import com.felixvn.demo.entity.service.CountryService;
import com.felixvn.demo.entity.service.DistrictService;
//import com.felixvn.demo.entity.service.DistrictService;
import com.felixvn.demo.entity.service.ProvinceService;
import com.felixvn.demo.entity.service.WardService;
import com.felixvn.demo.util.SpringJSFUtil;
import java.io.Serializable;

/**
 *
 * @author Phuc
 */
public abstract class BaseController implements Serializable{
    protected  CountryService getCountryService() {
        return SpringJSFUtil.getBean("countryService");
    }
    protected  ProvinceService getProvinceService() {
        return SpringJSFUtil.getBean("provinceService");
    }

    protected DistrictService getDistrictService(){
        return SpringJSFUtil.getBean("districtService");
    }
    
    protected WardService getWardService(){
        return SpringJSFUtil.getBean("wardService");
    }
   
}
