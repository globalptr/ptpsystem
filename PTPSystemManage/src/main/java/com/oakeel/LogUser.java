/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel;

import com.oakeel.ejb.ptpEnum.SysInfo;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
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
    private String userThemes="ui-darkness";
    @PostConstruct
    public void init()
    {
    }
    public String log()
    {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        try
        {
            subject.login(token);
            return "main?faces-redirect=true";
        }
        catch(AuthenticationException ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SysInfo.提示.toString(),"用户名/手机号/邮箱和密码不匹配")); 
            return null;
        }
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

    /**
     * @return the userThemes
     */
    public String getUserThemes() {
        return userThemes;
    }

    /**
     * @param userThemes the userThemes to set
     */
    public void setUserThemes(String userThemes) {
        this.userThemes = userThemes;
    }





    
}
