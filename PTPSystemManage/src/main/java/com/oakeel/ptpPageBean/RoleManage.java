/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel.ptpPageBean;

import com.oakeel.ejb.entityAndEao.resource.ResourceEaoLocal;
import com.oakeel.ejb.entityAndEao.resource.ResourceEntity;
import com.oakeel.ejb.entityAndEao.role.RoleEaoLocal;
import com.oakeel.ejb.entityAndEao.role.RoleEntity;
import com.oakeel.ejb.entityAndEao.user.UserEaoLocal;
import com.oakeel.ejb.entityAndEao.user.UserEntity;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author root
 */
@ManagedBean
@ViewScoped
public class RoleManage {

    @EJB
    UserEaoLocal userEaoLocal;
    @EJB
    RoleEaoLocal roleEaoLocal;
    @EJB
    ResourceEaoLocal ResourceEaoLocal;
    private List<UserEntity> userEntitys;//用户表数据源
    private List<UserEntity> userFilter;//用户表数据源
    private List<RoleEntity> roleEntitys;//角色
    private List<RoleEntity> roleFilter;//角色筛选
    private RoleEntity selectRole;//选择的角色
    List<ResourceEntity> resourceEntitys;
 
    /**
     * Creates a new instance of RoleManage
     */
    public RoleManage() {
    }
    @PostConstruct
    public void init()
    {
        userEntitys=userEaoLocal.getAllUser();
        roleEntitys=roleEaoLocal.getAllRole();
        resourceEntitys=ResourceEaoLocal.getAllResource();
    }
    /**
     * @return the userEntitys
     */
    public List<UserEntity> getUserEntitys() {
        return userEntitys;
    }

    /**
     * @param userEntitys the userEntitys to set
     */
    public void setUserEntitys(List<UserEntity> userEntitys) {
        this.userEntitys = userEntitys;
    }

    /**
     * @return the userFilter
     */
    public List<UserEntity> getUserFilter() {
        return userFilter;
    }

    /**
     * @param userFilter the userFilter to set
     */
    public void setUserFilter(List<UserEntity> userFilter) {
        this.userFilter = userFilter;
    }

    /**
     * @return the roleEntitys
     */
    public List<RoleEntity> getRoleEntitys() {
        return roleEntitys;
    }

    /**
     * @param roleEntitys the roleEntitys to set
     */
    public void setRoleEntitys(List<RoleEntity> roleEntitys) {
        this.roleEntitys = roleEntitys;
    }

    /**
     * @return the roleFilter
     */
    public List<RoleEntity> getRoleFilter() {
        return roleFilter;
    }

    /**
     * @param roleFilter the roleFilter to set
     */
    public void setRoleFilter(List<RoleEntity> roleFilter) {
        this.roleFilter = roleFilter;
    }

    /**
     * @return the selectRole
     */
    public RoleEntity getSelectRole() {
        return selectRole;
    }

    /**
     * @param selectRole the selectRole to set
     */
    public void setSelectRole(RoleEntity selectRole) {
        this.selectRole = selectRole;
    }
    
}
