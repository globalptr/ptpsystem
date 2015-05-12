/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel.ejb.entityAndEao.role;

import com.oakeel.ejb.entityAndEao.permission.PermissionEntity;
import com.oakeel.ejb.entityAndEao.user.UserEntity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author root
 */
@Entity
public class RoleEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    public RoleEntity(){
        roleUuid=UUID.randomUUID().toString();
    }
    public RoleEntity(String name)
    {
        roleUuid=UUID.randomUUID().toString();
        this.name=name;
    }
    public RoleEntity(String name,String description,Boolean available,int priority)
    {
        roleUuid=UUID.randomUUID().toString();
        this.name=name;
        this.description=description;
        this.available=available;
        this.priority=priority;
    }
    @Id
    @Column(length=36)
    private String roleUuid;
    @Column(nullable=false,length=50)
    private String name;//名字
    private int priority=0;//显示顺序
    @Column(length=200)
    private String description="";//描述
    private Boolean available=true;//是否可用
    //与用户是多对多的关系
    @ManyToMany(mappedBy="roleEntitys")
    private Set<UserEntity> userEntitys;
    //角色与资源是一对多的关系，主控在角色资源
    @OneToMany(cascade={CascadeType.REMOVE,CascadeType.MERGE},mappedBy="roleEntity")//如果删除了角色，那么角色的权限一并删除;角色与权限是一对多的关系，双向关联主控在权限
    private Set<PermissionEntity> permissions=new HashSet<>();

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getRoleUuid() != null ? getRoleUuid().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoleEntity)) {
            return false;
        }
        RoleEntity other = (RoleEntity) object;
        if ((this.getRoleUuid() == null && other.getRoleUuid() != null) || (this.getRoleUuid() != null && !this.roleUuid.equals(other.roleUuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oakeel.EntityAndDao.Role.RoleEntity[ id=" + getRoleUuid() + " ]";
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the available
     */
    public Boolean getAvailable() {
        return available;
    }

    /**
     * @param available the available to set
     */
    public void setAvailable(Boolean available) {
        this.available = available;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the userEntitys
     */
    public Set<UserEntity> getUserEntitys() {
        return userEntitys;
    }

    /**
     * @param userEntitys the userEntitys to set
     */
    public void setUserEntitys(Set<UserEntity> userEntitys) {
        this.userEntitys = userEntitys;
    }

    /**
     * @return the roleUuid
     */
    public String getRoleUuid() {
        return roleUuid;
    }

    /**
     * @param roleUuid the roleUuid to set
     */
    public void setRoleUuid(String roleUuid) {
        this.roleUuid = roleUuid;
    }

    /**
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * @return the permissions
     */
    public Set<PermissionEntity> getPermissions() {
        return permissions;
    }

    /**
     * @param permissions the permissions to set
     */
    public void setPermissions(Set<PermissionEntity> permissions) {
        this.permissions = permissions;
    }


  



    
    
}
