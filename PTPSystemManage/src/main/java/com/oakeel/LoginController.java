/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel;

import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 *
 * @author root
 */

@ManagedBean
@RequestScoped
public class LoginController { 
  
    private String username; 
    private String password; 
    boolean rememberMe = false; 
    private static final Logger log = Logger.getLogger(LoginController.class.getClass());        

    public LoginController() throws IOException
    {		
    }
    @RequiresRoles("admin")
    public String authenticate() { 
        
        try{
        
            Factory<org.apache.shiro.mgt.SecurityManager> factory =new IniSecurityManagerFactory("classpath:shiro.ini");
            org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
      
            SecurityUtils.setSecurityManager(securityManager);
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
        }


        // Example using most common scenario of username/password pair: 
        UsernamePasswordToken token = new UsernamePasswordToken(username, password); 
  
        // "Remember Me" built-in: 
        token.setRememberMe(rememberMe); 
  
        Subject currentUser = SecurityUtils.getSubject(); 
  
        log.info("Submitting login with username of " + getUsername()+ " and password of " + getPassword()); 
  
        try { 
            
            currentUser.login(token); 
        } catch (AuthenticationException e) { 
            // Could catch a subclass of AuthenticationException if you like 
            log.warn(e.getMessage()); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login Failed: " + e.getMessage(), e.toString())); 
            return "/login"; 
        } 
        return "protected?faces-redirect=true"; 
  
    } 
    public String logout() { 
  
        Subject currentUser = SecurityUtils.getSubject(); 
        try { 
            currentUser.logout(); 
        } catch (Exception e) { 
            log.warn(e.toString()); 
        } 
        return "index"; 
    } 
  

  
    public boolean getRememberMe() { 
        return rememberMe; 
    } 
  
    public void setRememberMe(boolean rememberMe) { 
        this.rememberMe = rememberMe; 
    } 

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
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

