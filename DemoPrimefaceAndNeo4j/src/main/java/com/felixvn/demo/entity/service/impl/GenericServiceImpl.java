/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.felixvn.demo.entity.service.impl;

import com.felixvn.demo.entity.ModelObject;
import com.felixvn.demo.entity.service.GenericService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public abstract class GenericServiceImpl<T extends ModelObject> implements GenericService<T>, Serializable{

    GraphRepository<T> repository;
    
    @Autowired private Neo4jTemplate neo4jTemplate;
    
    public GenericServiceImpl(GraphRepository<T> repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public T saveObject(T entity) {
        return repository.save(entity);
    }
    @Transactional
    @Override
    public T save(T entity) {
        if(entity.getId() == null){
            entity.setCreatedDate(new Date());
        }else{
            entity.setLastModified(new Date());
        }
        return repository.save(entity);
    }
    @Transactional
    @Override
    public Iterable<T> save(Iterable<T> entities) {
        return repository.save(entities);
    }

    @Transactional
    @Override
    public void remove(T entity){
        entity.setDeleted(Boolean.TRUE);
        entity.setLastModified(new Date());
        repository.save(entity);
    }
    
    @Transactional
    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }
    @Transactional
    @Override
    public void delete(Long id){
         repository.delete(id);
    }

    @Transactional
    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
    @Transactional
    @Override
    public void removeMultiple(Iterable<T> list){
        for (T t : list) {
            t.setDeleted(Boolean.TRUE);
            t.setLastModified(new Date());
        }
        repository.save(list);
    }
    
    @Transactional
    @Override
    public void deleteMultiple(Iterable<T> list) {
        repository.delete(list);
    }

    @Override
    public T findOne(Long id) {
        return repository.findOne(id);
    }
    
    
    /*
    @Override
    public List<T> findAllByIds(Iterable<Long> mos) {
        List<T> list = IteratorUtils.toList(repository.findAll(mos).iterator());
        return list;
    }*/

    @Override
    public T findByPropertyValue(String property, Object value) {
        return repository.findByPropertyValue(property, value);
    }
    /*
    @Override
    public List<T> findAllByPropertyValue(String property, Object value) {
        Result<T> eResult = repository.findAllByPropertyValue(property, value);
        return IteratorUtils.toList(eResult.iterator());
    }(*/
    @Transactional
    @Override
    public List<T> findAll() {
        List<T> result = new ArrayList<T>();
        Result<T> eResult = repository.findAll();
        for (T t: eResult) {
            result.add(t);
        }
        return result;
    }

    @Override
    public boolean exists(Long id) {
        return repository.exists(id);
    }

    @Override
    public long count() {
        return repository.count();
    }
    @Override
    @Transactional
    public <H> H fetch(H value){
        return neo4jTemplate.fetch(value);
    }
    @Override 
    public <T> T convert(Object value,Class<T> cs){
        return neo4jTemplate.convert(value,cs);
    }
    @Override
    @Transactional
    public Result<Map<String,Object>> query(String statement, Map<String,Object> params){
        return neo4jTemplate.query(statement, params);
    }
}
