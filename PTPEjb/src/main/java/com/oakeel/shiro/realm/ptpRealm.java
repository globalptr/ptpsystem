/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oakeel.shiro.realm;

import com.oakeel.ptpEntityAndCtrl.User.UserDao;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 *
 * @author root
 */
@Stateless
@LocalBean
public class ptpRealm extends AuthorizingRealm {

    @EJB
    UserDao userDao;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken at) throws AuthenticationException {
        String username = (String) at.getPrincipal(); //得到用户名
        String password = new String((char[]) at.getCredentials()); //得到密码
        if (!"zhang".equals(username)) {
            throw new UnknownAccountException(); //如果用户名错误
        }
        if (!"123".equals(password)) {
            throw new IncorrectCredentialsException(); //如果密码错误
        }
        //如果身份认证验证成功,返回一个 AuthenticationInfo 实现;
        return new SimpleAuthenticationInfo(username, password, getName());

    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
