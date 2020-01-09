/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Laptop2
 */
@ManagedBean(name = "flip")
@ViewScoped
public class FlipRegisterBean implements Serializable {

    private String name;
    private String lastname;
    private String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Creates a new instance of FlipRegisterBean
     */
    public FlipRegisterBean() {
    }

    public void updateData() {

        RequestContext ctx = RequestContext.getCurrentInstance();
        ctx.update("data");
    }
}
