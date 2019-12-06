/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.felixvn.demo.controller;

import com.felixvn.demo.entity.Country;
import com.felixvn.demo.entity.Province;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import org.neo4j.cypher.MissingIndexException;

/**
 *
 * @author Phuc
 */
@ManagedBean(name = "provinceController")
@ViewScoped
public class ProvinceController extends BaseController {
    private static final Logger logger = Logger.getLogger(ProvinceController.class); 
    private Province province;
    private List<Province> provinces;
    private String countryCode;
    private Map<String, String> mapCountry;
    private Map<Long, Country> countries;
    @PostConstruct
    public void init() {
        System.out.println("Start Init ProvinceController.......................................................");
        provinces = getProvinceService().findAllValid("VN");
        mapCountry = new HashMap<String, String>();
        mapCountry.put("VN", "Việt nam");
 
        //mapCountry.put("KHAC", "KHAC");
        countries = new HashMap<Long, Country>();
        reset();
        System.out.println("End Init ProvinceController.......................................................");

    }
    public void save() {
        logger.info("Begin save, code = " + province.getCode() + ", name = " + province.getName());
        //System.out.println("Begin save, code = " + province.getCode() + ", name = " + province.getName() +", country = " + countryCode);
        FacesContext context = FacesContext.getCurrentInstance();
        boolean validData = true;
        if(province.getCode() == null || province.getCode().isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mã bắt buộc nhập", null));
            validData = false;
        } else {
            Province pCheck = getProvinceService().findByCode(province.getCode());
            if(pCheck != null) {
                if(province.getId() != null) {
                    System.out.println("province.getId() = " + province.getId() + ", pCheck.getId() = " + pCheck.getId());
                    if(!province.getId().equals(pCheck.getId())) {
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mã " + province.getCode() +" đã có rồi", null));
                        validData = false;
                    }
                } else {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mã " + province.getCode() +" đã có rồi", null));
                    validData = false;
                }
            }
        }
        if(province.getName() == null || province.getName().isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tên bắt buộc nhập", null));
            validData = false;
        }
        if(countryCode == null || countryCode.isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Quốc gia bắt buộc nhập", null));
            validData = false;
        }
        if(validData) {
            
                Country country = null;
                try {
                    country = getCountryService().findByCode(countryCode);
                } catch(Exception ex) {
                    country = null;
                }
                if(country == null) {
                    country = new Country();
                    country.setCode(countryCode);
                    country.setName(mapCountry.get(countryCode));
                    country = getCountryService().save(country);
                }
                province.setCountry(country);
                getProvinceService().save(province);
                provinces = getProvinceService().findAllValid("VN");
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Lưu thành công", null));
                reset();
            
        }
    }
    
    public void delete(Province pro) {
        FacesContext context = FacesContext.getCurrentInstance();
        if(pro != null) {
            if(getProvinceService().checkUsed(pro.getId())) {
                System.out.println(getProvinceService().checkUsed(pro.getId()));
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dữ liệu đang sử dụng, không thể xóa", null));
            } else {
                getProvinceService().deleteProvince(pro.getId());
                provinces = getProvinceService().findAllValid("VN");
                reset();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Xóa dữ liệu thành công", null));
            }
        }
    }
    public void edit(Province pro) {
        province = pro;
        countryCode = getCountryService().fetch(pro.getCountry()).getCode();
    }
    public String fetchCoutry(Province pro) {
        //return getCountryService().fetch(pro.getCountry()).getName();
        
        if(pro != null && pro.getCountry() != null) {
            if(countries.get(pro.getCountry().getId()) == null) {
                countries.put(pro.getCountry().getId(), getCountryService().fetch(pro.getCountry()));
            }
            return countries.get(pro.getCountry().getId()).getName();
        }
        return "";

    }
    public void reset() {
        province = new Province();
        countryCode = "";
    }
    
    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public List<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<Province> provinces) {
        this.provinces = provinces;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Map<String, String> getMapCountry() {
        return mapCountry;
    }

    public void setMapCountry(Map<String, String> mapCountry) {
        this.mapCountry = mapCountry;
    }
    
}
