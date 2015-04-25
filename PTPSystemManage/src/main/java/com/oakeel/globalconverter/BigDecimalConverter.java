/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel.globalconverter;

import java.math.BigDecimal;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author root
 */
@FacesConverter("BigDecimalConverter")
public class BigDecimalConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        BigDecimal bd=new BigDecimal(value); 
        return bd;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        BigDecimal bd=(BigDecimal)value; 
        return bd.toString();
    }
    
}
