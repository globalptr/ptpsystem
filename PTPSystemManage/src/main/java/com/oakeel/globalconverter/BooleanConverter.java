/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel.globalconverter;

import java.util.Objects;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;

/**
 *
 * @author root
 */

@FacesConverter("BooleanConverter")
public class BooleanConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value.equals("是"))
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Boolean temp=(Boolean)value;
        if(Objects.equals(temp, Boolean.TRUE))
            return "是";
        else
            return "否";
    }
    
}
