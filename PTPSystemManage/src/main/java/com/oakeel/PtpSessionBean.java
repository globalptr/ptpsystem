/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.TreeNode;

/**
 *
 * @author root
 */
@ManagedBean
@SessionScoped
public class PtpSessionBean implements Serializable{

    //对于一个登录用户的session数据，统一使用此bean
    /**
     * Creates a new instance of PtpSessionBean
     */
    public PtpSessionBean() {
    }
    
    private String userName;//保存会话期内的用户名

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    

}
