/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

/**
 *
 * @author root
 */
@ManagedBean
@SessionScoped
public class LogUser {
    private String userName;
    private String password;
    @PostConstruct
    public void init()
    {
    }
    public String log()
    {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        subject.login(token);
        int i=0;
        return null;
//        try
//        {
//            shiroManager.log(userName, password);
//            return "success";
//        }
//        catch(AuthenticationException ex)
//        {
//            return "failure";
//        }
    }
    /**
     * Creates a new instance of User
     */
    public LogUser() {
    }

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

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }





    
}
