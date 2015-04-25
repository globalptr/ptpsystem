/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author root
 */
@ManagedBean
@RequestScoped
public class LayoutBean {

    /**
     * Creates a new instance of LayoutBean
     */
    public LayoutBean() {
    }
       private String theme = "aristo";
       private String themex = "start";
       private Boolean xy=false;
    public String getTheme()
    {
        return theme;
    }
    public String getThemex()
    {
        return themex;
    }

    /**
     * @return the xy
     */
    public Boolean getXy() {
        return xy;
    }

    /**
     * @param xy the xy to set
     */
    public void setXy(Boolean xy) {
        this.xy = xy;
    }
}
