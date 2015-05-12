/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel.ejb.entityAndEao.resource;

import com.oakeel.ejb.entityAndEao.permission.PermissionEntity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author root
 */
@Entity
public class ResourceEntity implements Serializable {
    public ResourceEntity()
    {
        ResourceUuid=UUID.randomUUID().toString();
    }
    public ResourceEntity(String name,String permission,ResourceTypeEnum type,Boolean available,int priority)
    {
        ResourceUuid=UUID.randomUUID().toString();
        this.name=name;
        this.resourceDesc=permission;
        this.type=type;
        this.priority=priority;
        this.available=available;
    }
    public ResourceEntity(String name,String permission)
    {
        ResourceUuid=UUID.randomUUID().toString();
        this.name=name;
        this.resourceDesc=permission;
        
    }
    private static final long serialVersionUID = 1L;
    @Id
    @Column(length=36)
    private String ResourceUuid;
    @Column(nullable=false,length=50)
    private String name;//资源名
    @Enumerated(EnumType.STRING)
    @Column(length=10)
    private ResourceTypeEnum type=ResourceTypeEnum.未定义;//资源类型
    private int priority=0;//显示顺序
    @Column(length=50)
    private String resourceDesc="";//描述
    private Boolean available=true;//是否可用
    @OneToMany(mappedBy="resourceEntity")//资源与权限的关系是一对多的关系，主控必须在权限
    private Set<PermissionEntity> permissionEntitys=new HashSet<>();
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getUuid() != null ? getUuid().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResourceEntity)) {
            return false;
        }
        ResourceEntity other = (ResourceEntity) object;
        if ((this.getUuid() == null && other.getUuid() != null) || (this.getUuid() != null && !this.ResourceUuid.equals(other.ResourceUuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oakeel.EntityAndDao.Permission.PermissionEntity[ id=" + getUuid() + " ]";
    }

    /**
     * @return the ResourceUuid
     */
    public String getUuid() {
        return ResourceUuid;
    }

    /**
     * @param ResourceUuid
     */
    public void setUuid(String ResourceUuid) {
        this.ResourceUuid = ResourceUuid;
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
     * @return the type
     */
    public ResourceTypeEnum getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(ResourceTypeEnum type) {
        this.type = type;
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
     * @return the resourceDesc
     */
    public String getResourceDesc() {
        return resourceDesc;
    }

    /**
     * @param resourceDesc the resourceDesc to set
     */
    public void setResourceDesc(String resourceDesc) {
        this.resourceDesc = resourceDesc;
    }

    /**
     * @return the permissionEntitys
     */
    public Set<PermissionEntity> getPermissionEntitys() {
        return permissionEntitys;
    }

    /**
     * @param permissionEntitys the permissionEntitys to set
     */
    public void setPermissionEntitys(Set<PermissionEntity> permissionEntitys) {
        this.permissionEntitys = permissionEntitys;
    }




   

    
}
