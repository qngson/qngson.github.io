/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.felixvn.demo.controller;

import com.felixvn.demo.entity.District;
import com.felixvn.demo.entity.Province;
import com.felixvn.demo.entity.Ward;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import static org.neo4j.kernel.impl.util.StringLogger.logger;

/**
 *
 * @author Lapop1
 */
@ManagedBean(name = "wardController")
@ViewScoped
public class WardController extends BaseController {

    private static final Logger logger = Logger.getLogger(WardController.class);
    private Ward ward;
    private List<Ward> wards;
    private List<District> district;
    private List<Province> province;
    private Long provinceId;
    private Map<Long, District> districts;
    private Map<Long, Province> provinces;
    private Long districtId;

    @PostConstruct
    public void init() {
        System.out.println("int start");
        //district = getDistrictService().findAllValid("VN");

        wards = getWardService().findAllValid("VN");
        province = getProvinceService().findAllValid("VN");
        provinces = new HashMap<Long, Province>();
        districts = new HashMap<Long, District>();
        //System.out.println("district " + district.size());

        reset();
    }

    public void save() {

        System.out.println("Begin save, code = " + ward.getCode() + ", name = " + ward.getName());
        FacesContext context = FacesContext.getCurrentInstance();
        boolean validData = true;
        if (ward.getCode() == null || ward.getCode().isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mã bắt buộc nhập", null));
            validData = false;
        } else {
            Ward wCheck = getWardService().findByCode(ward.getCode());
            if (wCheck != null) {
                if (ward.getId() != null) {
                    System.out.println("ward.getId() = " + ward.getId() + ", wCheck.getId() = " + wCheck.getId());
                    if (!ward.getId().equals(wCheck.getId())) {
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mã " + ward.getCode() + " đã có rồi", null));
                        validData = false;
                    } else {
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mã " + ward.getCode() + " đã có rồi", null));
                        validData = false;
                    }
                }
            }
        }
        if (ward.getName() == null || ward.getName().isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tên Phường Xã bắt buộc nhập", null));
            validData = false;
        }
        if (provinceId == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tỉnh Thành Phố bắt buộc nhập", null));
            validData = false;
        }
        if (districtId == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Quận Huyện bắt buộc nhập", null));
            validData = false;
        }
        if (validData) {
           
            Province proTmp = getProvinceService().findOne(provinceId);
            District disTmp = getDistrictService().findOne(districtId);
            ward.setProvince(proTmp);
            ward.setDistrict(disTmp);
            ward = getWardService().save(ward);
            wards = getWardService().findAllValid("VN");
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Lưu thành công", null));
            reset();
        }

    }
    public void delete(Ward ward){
        FacesContext context = FacesContext.getCurrentInstance();
        if (ward != null) {
            if (getWardService().checkUsed(ward.getId())) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dữ liệu đang sử dụng, không thể xóa", null));
            }else {
                getWardService().deleteWard(ward.getId());
                wards = getWardService().findAllValid("VN");
                reset();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Xóa dữ liệu thành công", null));
            }
        }
    }

    public void edit(Ward war){
        ward = war;
        provinceId = war.getProvince().getId();
        districtId = war.getDistrict().getId();
    }
    public void changeProvince() {
        System.out.println("changeProvince");
        System.out.println(provinceId);
        district = getDistrictService().findByProvince(provinceId);
        System.out.println("Size : " + district.size());
        System.out.println("end");
    }

    public String fetchProvince(Ward war) {
        //return getWardService().fetch(war.getProvince()).getName();
        

        if (war != null && war.getProvince() != null) {
            if (provinces.get(war.getProvince().getId()) == null) {
                provinces.put(war.getProvince().getId(), getWardService().fetch(war.getProvince()));
            }
            return provinces.get(war.getProvince().getId()).getName();
        }
        return "";
    }

    public String fetchDistrict(Ward war) {
       
        if (war != null && war.getDistrict() != null) {
            if (districts.get(war.getDistrict().getId()) == null) {
                districts.put(war.getDistrict().getId(), getWardService().fetch(war.getDistrict()));
            }
            return districts.get(war.getDistrict().getId()).getName();
        }
        return "";
    }

    /*public Map<String, String> fetchMoreInfo(Ward ward){
        Map<String, String> result = new HashMap<String, String>();
        result.put("provinceName", "");
        result.put("districtName", "");
        
         if (ward != null && ward.getProvince() != null) {
        
             if (provinces.get(ward.getProvince().getId()) == null) {
                 provinces.put(ward.getProvince().getId(), getDistrictService().fetch(ward.getProvince()));
             }
             result.put("provinceName", provinces.get(ward.getProvince().getId()).getName());
               
         }
        return result;
    }*/

    public void reset() {
        ward = new Ward();
        district = new ArrayList<District>();

    }

    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }

    public List<Ward> getWards() {
        return wards;
    }

    public void setWards(List<Ward> wards) {
        this.wards = wards;
    }

    public List<District> getDistrict() {
        return district;
    }

    public void setDistrict(List<District> district) {
        this.district = district;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Map<Long, District> getDistricts() {
        return districts;
    }

    public void setDistricts(Map<Long, District> districts) {
        this.districts = districts;
    }

    public List<Province> getProvince() {
        return province;
    }

    public void setProvince(List<Province> province) {
        this.province = province;
    }

    public Map<Long, Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(Map<Long, Province> provinces) {
        this.provinces = provinces;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

}
