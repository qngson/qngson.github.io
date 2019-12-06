/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.felixvn.demo.entity.service;

import com.felixvn.demo.entity.Country;
import java.util.List;

/**
 *
 * @author Phuc
 */
public interface CountryService extends GenericService<Country>{
    public Country findByCode(String countryCode);
    
   
}

