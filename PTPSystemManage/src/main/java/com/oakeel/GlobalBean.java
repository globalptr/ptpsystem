/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oakeel;

import com.oakeel.ejb.transaction.InitEjbLocal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author root
 */
@ManagedBean(eager=true)
@ApplicationScoped
public class GlobalBean {

    /**
     * Creates a new instance of GlobalBean
     */

    Boolean isinit = true;
    @EJB
    InitEjbLocal initEjbLocal;
    public GlobalBean() {
    }

    @PostConstruct
    public void init()  {
        if (isinit) {
            System.out.println("__________________________start init system__________________________");
            //initEjbLocal.InitDB();
            System.out.println("__________________________init system over__________________________");
        }

    }

}
