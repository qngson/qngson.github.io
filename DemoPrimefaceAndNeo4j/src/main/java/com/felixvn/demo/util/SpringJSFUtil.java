/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.felixvn.demo.util;

import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;

/**
 *
 * @author Phuc
 */
public class SpringJSFUtil {
    private static final Logger logger = Logger.getLogger(SpringJSFUtil.class);

    public static <T> T getBean(String beanName) {
        if (beanName == null) {
            return null;
        }
        return getValue("#{" + beanName + "}");
    }
    private static <T> T getValue(String expression) {
        FacesContext context = FacesContext.getCurrentInstance();
        return (T) context.getApplication().evaluateExpressionGet(context,
                expression, Object.class);
    }
}
