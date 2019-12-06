/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.felixvn.demo.controller;

import com.felixvn.demo.entity.Country;
import com.felixvn.demo.entity.District;
import com.felixvn.demo.entity.Province;
import static com.sun.faces.facelets.util.Path.context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import static org.aspectj.bridge.context.CompilationAndWeavingContext.reset;

/**
 *
 * @author Lapop1
 */
@ManagedBean(name = "districtController")
@ViewScoped
public class DistrictController extends BaseController {

    private static final Logger logger = Logger.getLogger(DistrictController.class);
    private District district;
    private List<District> districts;
    private List<Province> provinces;
    private Long provinceId;
    String districtCode;
    private Map<Long, Province> province;
    private Map<Long, Country> country;

    @PostConstruct
    public void init() {

        System.out.println("start");
        provinces = getProvinceService().findAllValid("VN");
        districts = getDistrictService().findAllValid("VN");

        System.out.println("List Districts " + districts.size());
        System.out.println("List provinces " + provinces.size());
        province = new HashMap<Long, Province>();
        country = new HashMap<Long, Country>();
        reset();
        System.out.println("end");
    }

    public void reset() {
        district = new District();
    }

    public void save() {
        System.out.println("Begin save, code = " + district.getCode() + ", name = " + district.getName());

        FacesContext context = FacesContext.getCurrentInstance();
        boolean validData = true;
        if (district.getCode() == null || district.getCode().isEmpty()) {

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mã Quận Huyện bắt buộc nhập", null));
            validData = false;
        } else {
            District dCheck = getDistrictService().findByCode(district.getCode());
            if (dCheck != null) {
                if (district.getId() != null) {
                    System.out.println("district.getId() = " + district.getId() + ", dCheck.getId() = " + dCheck.getId());
                    if (!district.getId().equals(dCheck.getId())) {
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mã " + district.getCode() + " đã có rồi", null));
                        validData = false;
                    } else {
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mã " + district.getCode() + " đã có rồi", null));
                        validData = false;
                    }
                }
            }
        }
        if (district.getName() == null || district.getName().isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tên Quận Huyện bắt buộc nhập", null));
            validData = false;
        }
        if (provinceId == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tỉnh Thành bắt buộc nhập", null));
            validData = false;
        }
        if (validData) {
            Province proTmp = getProvinceService().findOne(provinceId);
            district.setProvince(proTmp);
            district = getDistrictService().save(district);

            districts = getDistrictService().findAllValid("VN");

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Lưu thành công", null));
            reset();
        }

    }

    public String fetchDistrict(District dist) {
        // return getDistrictService().fetch(dist.getProvince()).getName();

        if (dist != null && dist.getProvince() != null) {
            if (province.get(dist.getProvince().getId()) == null) {
                province.put(dist.getProvince().getId(), getDistrictService().fetch(dist.getProvince()));
            }
            return province.get(dist.getProvince().getId()).getName();
        }
        return "";
    }

    public Map<String, String> fetchMoreInfo(District dist) {
        // return getDistrictService().fetch(dist.getProvince()).getName();
        Map<String, String> result = new HashMap<String, String>();
        result.put("provinceName", "");
        result.put("countryName", "");
        if (dist != null && dist.getProvince() != null) {

            if (province.get(dist.getProvince().getId()) == null) {
                province.put(dist.getProvince().getId(), getDistrictService().fetch(dist.getProvince()));
            }
            result.put("provinceName", province.get(dist.getProvince().getId()).getName());

            if (province.get(dist.getProvince().getId()).getCountry() != null) {
                Province pTmp = province.get(dist.getProvince().getId());

                if (country.get(pTmp.getCountry().getId()) == null) {
                    country.put(pTmp.getCountry().getId(), getCountryService().findOne(pTmp.getCountry().getId()));
                }
                result.put("countryName", country.get(pTmp.getCountry().getId()).getName());

            }
        }
        return result;
    }

    public void delete(District dist) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (dist != null) {
            if (getDistrictService().checkUsed(dist.getId())) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dữ liệu đang sử dụng, không thể xóa", null));
            } else {
                getDistrictService().deleteDistrict(dist.getId());
                districts = getDistrictService().findAllValid("VN");
                reset();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Xóa dữ liệu thành công", null));
            }

        }
    }

    public void edit(District dist) {
        district = dist;
        provinceId = dist.getProvince().getId();

    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public Map<Long, Province> getProvince() {
        return province;
    }

    public void setProvince(Map<Long, Province> province) {
        this.province = province;
    }

    public List<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<Province> provinces) {
        this.provinces = provinces;
    }

    public void b (){
        System.out.println("abc");
    }
    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }
    public int add(int a, int b) {
        return a+b;
    }

    public int add2(int a, int b) {
        return a+b;
    }

}
