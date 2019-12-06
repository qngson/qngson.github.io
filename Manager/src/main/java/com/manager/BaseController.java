/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manager;


import com.service.Studentservice;
import com.util.SpringJSFUtil;
import java.io.Serializable;

/**
 *
 * @author Phuc
 */
public abstract class BaseController implements Serializable{
   
    
    protected Studentservice getStudentService(){
        return SpringJSFUtil.getBean("studentService");
    }
   
}
