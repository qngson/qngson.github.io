/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.felixvn.demo.entity.service;

import com.felixvn.demo.entity.District;
import com.felixvn.demo.entity.Province;
import com.felixvn.demo.entity.Ward;
import java.util.List;

/**
 *
 * @author Lapop1
 */
public interface WardService extends GenericService<Ward> {
     public List<Ward> findAllValid(String countryCode);
      boolean checkUsed(Long wardId);
      public Ward findByCode(String wardCode);
      public List<Ward> findAllWard();
      public void deleteWard(Long wardId);
}
