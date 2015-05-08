/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel.globalconverter;

import com.oakeel.ejb.entityAndEao.resource.ResourceTypeEnum;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author root
 */
@FacesConverter("ResourceTypeConverter")
public class ResourceTypeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        ResourceTypeEnum[] types=ResourceTypeEnum.values();
        for (ResourceTypeEnum type : types) {
            if (type.toString().equals(value)) {
                return type;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        ResourceTypeEnum type=(ResourceTypeEnum)value;
        return type.toString();
    }
    
}
