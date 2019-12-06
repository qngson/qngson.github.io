/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.felixvn.demo.entity.service.impl;

import com.felixvn.demo.entity.Country;
import com.felixvn.demo.entity.reporitory.CountryRepository;
import com.felixvn.demo.entity.service.CountryService;
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
@Service(value = "countryService")
public class CountryServiceImpl extends GenericServiceImpl<Country> implements CountryService {

    private static Logger logger = Logger.getLogger(CountryServiceImpl.class);
    private final CountryRepository repository;

    @Autowired
    public CountryServiceImpl(CountryRepository repository) {
        super(repository);
        this.repository = repository;
    }
    @Override
    public Country findByCode(String countryCode) {
        return repository.findByCode(countryCode);
    }
    
}
