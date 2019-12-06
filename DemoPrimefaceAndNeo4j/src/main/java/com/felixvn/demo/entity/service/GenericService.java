/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.felixvn.demo.entity.service;

import java.util.List;
import java.util.Map;
import org.springframework.data.neo4j.conversion.Result;

/**
 *
 * @author Phuc
 */
public interface GenericService<T> {

    public T saveObject(T entity);
    
    public T save(T entity);
    
    public Iterable<T> save(Iterable<T> entitys);
    
    public void remove(T entity);

    public void delete(Long id);

    public void delete(T entity);

    public void deleteAll();
    
    public void removeMultiple(Iterable<T> list);

    public void deleteMultiple(Iterable<T> list);

    public T findOne(Long id);

    //public List<T> findAllByIds(Iterable<Long> mos);

    public T findByPropertyValue(String property, Object value);

    //public List<T> findAllByPropertyValue(String property, Object value);

    public List<T> findAll();
    
    public boolean exists(Long id);

    public long count();
    
    public <H> H fetch(H value);
    
    public <T> T convert(Object value,Class<T> cs);
    
    public Result<Map<String,Object>> query(String statement, Map<String,Object> params);
}
