/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel;

import com.oakeel.ejb.ptpEnum.SysInfo;
import com.oakeel.ejb.transaction.InitEjbLocal;
import com.oakeel.shiro.PtpRealm;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.mgt.DefaultSecurityManager;

/**
 *
 * @author root
 */
@ManagedBean(eager=true)
@ApplicationScoped
public class PtpApplicationBean {
    
    @EJB
    InitEjbLocal initEjbLocal;
    /**
     * Creates a new instance of PtpApplicationBean
     */
    @PostConstruct
    public void init()
    {
//        DefaultSecurityManager securityManager = new DefaultSecurityManager(new PtpRealm());
//        //设置 authenticator 验证策略
//        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
//        authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
//        securityManager.setAuthenticator(authenticator);
//        //设置 authorizer 授权策略
//        ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
//        authorizer.setPermissionResolver(new WildcardPermissionResolver());
//        securityManager.setAuthorizer(authorizer);
//        //设置 Realm
//        //将 SecurityManager 设置到 SecurityUtils 方便全局使用
//        SecurityUtils.setSecurityManager(securityManager);
    }
    public PtpApplicationBean() {
    }
    public void initDB()
    {
        initEjbLocal.InitDB();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(SysInfo.提示.toString(),  "数据库初始化完毕!") );
    }
}
