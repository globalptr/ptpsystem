/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel.globalconverter;

import com.oakeel.ejb.entityAndDao.organization.OrganizationDaoLocal;
import com.oakeel.ejb.entityAndDao.organization.OrganizationEntity;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author root
 */
@FacesConverter("OrganizationConverter")
public class OrganizationConverter implements Converter{

    @EJB
    OrganizationDaoLocal organizationDaoLocal;
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return organizationDaoLocal.getOrganizationByUuid(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }
    
}
