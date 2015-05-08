/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel;

import com.oakeel.ejb.ptpEnum.SysInfo;
import com.oakeel.ejb.transaction.InitEjbLocal;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author root
 */
@ManagedBean
@RequestScoped
public class PtpApplicationBean {
    
    @EJB
    InitEjbLocal initEjbLocal;
    /**
     * Creates a new instance of PtpApplicationBean
     */
    public PtpApplicationBean() {
    }
    public void initDB()
    {
        initEjbLocal.InitDB();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(SysInfo.提示.toString(),  "数据库初始化完毕!") );
    }
}
