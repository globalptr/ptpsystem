/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel.ptpPageBean;

import com.oakeel.ejb.entityAndEao.role.RoleEaoLocal;
import com.oakeel.ejb.entityAndEao.role.RoleEntity;
import com.oakeel.ejb.entityAndEao.user.UserEaoLocal;
import com.oakeel.ejb.entityAndEao.user.UserEntity;
import com.oakeel.ejb.ptpEnum.SysInfo;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.DragDropEvent;

/**
 *
 * @author root
 */
@ManagedBean
@ViewScoped
public class UserToRole {

    /**
     * Creates a new instance of NewJSFManagedBean
     */
    @EJB
    private UserEaoLocal userEaoLocal;
    @EJB
    private RoleEaoLocal roleEaoLocal;
            
    private List<UserEntity> userEntitys;//所有的用户
    private List<UserEntity> userFilter;//筛选的用户
    private List<RoleEntity> roleEntitys;//所有的角色
    private List<RoleEntity> roleFilter;//筛选的角色
    private UserEntity selectUser;//选择的用户
    private Set<RoleEntity> selectUserRoles;//选择用户的角色
    private RoleEntity delUserRole;//准备删除的用户角色
    public UserToRole() {
    }
    @PostConstruct
    public void init()
    {
        setUserEntitys(getUserEaoLocal().getAllUser());
        setRoleEntitys(getRoleEaoLocal().getAllRole());
    }
    //列出所有的用户
    public void viewAllUser()
    {
        userEntitys=userEaoLocal.getAllUser();
        userFilter=userEaoLocal.getAllUser();
        
    }
    //列出所有的角色
    public void viewAllRole()
    {
        roleEntitys=roleEaoLocal.getAllRole();
        roleFilter=roleEaoLocal.getAllRole();
    }
    //列出用户角色
    public void viewUserRole()
    {
        if(selectUser!=null)
        {
            selectUserRoles=selectUser.getRoleEntitys();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(SysInfo.提示.toString(), "选择用户"+selectUser.getName()));
        }
    }
    //一键为用户赋值（角色）
    public void oneClickEva()
    {
        //如果filter为空，则将roleEntitys赋给选择的用户，否则，将filter赋值
        if(selectUser!=null)
        {
            if(roleFilter!=null)
            {
                for(RoleEntity item:roleFilter)
                {
                    selectUser.getRoleEntitys().add(item);
                    selectUserRoles.add(item);
                }
            }
            else
            {
                for(RoleEntity item:roleEntitys)
                {
                    selectUser.getRoleEntitys().add(item);
                    selectUserRoles.add(item);
                }
            }
        }
    }
    //删除用户角色
    public String deleteUserRole()
    {
        if(selectUser!=null&&delUserRole!=null)
        {
            userEaoLocal.deleteRole(selectUser, delUserRole);
        }
        return null;
    }
    //拖角色到用户的角色表
    public void onRoleDrop(DragDropEvent event)
    {
        if(selectUser!=null)
        {
            RoleEntity role=(RoleEntity)event.getData();
            userEaoLocal.addUserRole(selectUser, role);
            selectUserRoles.add(role);
        }
        else
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(SysInfo.错误.toString(), "没有选择操作的用户"));
        }
        
    }

    /**
     * @return the userEaoLocal
     */
    public UserEaoLocal getUserEaoLocal() {
        return userEaoLocal;
    }

    /**
     * @param userEaoLocal the userEaoLocal to set
     */
    public void setUserEaoLocal(UserEaoLocal userEaoLocal) {
        this.userEaoLocal = userEaoLocal;
    }

    /**
     * @return the roleEaoLocal
     */
    public RoleEaoLocal getRoleEaoLocal() {
        return roleEaoLocal;
    }

    /**
     * @param roleEaoLocal the roleEaoLocal to set
     */
    public void setRoleEaoLocal(RoleEaoLocal roleEaoLocal) {
        this.roleEaoLocal = roleEaoLocal;
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
     * @return the selectUser
     */
    public UserEntity getSelectUser() {
        return selectUser;
    }

    /**
     * @param selectUser the selectUser to set
     */
    public void setSelectUser(UserEntity selectUser) {
        this.selectUser = selectUser;
    }



    /**
     * @return the delUserRole
     */
    public RoleEntity getDelUserRole() {
        return delUserRole;
    }

    /**
     * @param delUserRole the delUserRole to set
     */
    public void setDelUserRole(RoleEntity delUserRole) {
        this.delUserRole = delUserRole;
    }

    /**
     * @return the selectUserRoles
     */
    public Set<RoleEntity> getSelectUserRoles() {
        return selectUserRoles;
    }

    /**
     * @param selectUserRoles the selectUserRoles to set
     */
    public void setSelectUserRoles(Set<RoleEntity> selectUserRoles) {
        this.selectUserRoles = selectUserRoles;
    }
}
