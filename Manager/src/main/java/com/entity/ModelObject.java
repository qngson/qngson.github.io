/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import java.util.Date;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.GraphProperty;

/**
 *
 * @author Phuc
 */
public abstract class ModelObject implements Serializable{
   /* private declare property */
    @GraphId
    protected Long id;

    @GraphProperty(propertyType = Long.class)
    protected Date createdDate;

    @GraphProperty(propertyType = Long.class)
    protected Date lastModified;
    
    protected boolean deleted;
    
    @GraphProperty(propertyType = Long.class)
    protected Date deleteTime;
    
    /* getter and setter method */
    
    public Long getId() {
     return id;
    }
    
    public void setId(Long id) {
     this.id = id;
    }
    
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    /**
     * @return the deleted
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * @param deleted the deleted to set
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    
}
