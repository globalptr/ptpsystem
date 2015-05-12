/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oakeel.ejb.entityAndEao.permission;

import com.oakeel.ejb.entityAndEao.operation.OperationEntity;
import com.oakeel.ejb.entityAndEao.resource.ResourceEntity;
import com.oakeel.ejb.entityAndEao.role.RoleEntity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author root
 */
@Entity
public class PermissionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String permissionUuid;

    public PermissionEntity () {
        permissionUuid = UUID.randomUUID().toString();
    }

    public PermissionEntity(ResourceEntity resource) {
        permissionUuid = UUID.randomUUID().toString();
        this.resourceEntity = resource;
    }
    public PermissionEntity(RoleEntity role,ResourceEntity resource) {
        permissionUuid = UUID.randomUUID().toString();
        this.roleEntity=role;
        this.resourceEntity = resource;
    }
    @ManyToOne
    private ResourceEntity resourceEntity;
    @OneToMany
    private Set<OperationEntity> operationEntitys=new HashSet<>();

    @ManyToOne
    private RoleEntity roleEntity;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getPermissionUuid() != null ? getPermissionUuid().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
              // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PermissionEntity)) {
            return false;
        }
        PermissionEntity other = (PermissionEntity) object;
        if ((this.getPermissionUuid() == null && other.getPermissionUuid() != null) || (this.getPermissionUuid() != null && !this.permissionUuid.equals(other.permissionUuid))) 
        {
            return false;
        }
        return true;
    }

    /**
     * @return the permissionUuid
     */
    public String getPermissionUuid() {
        return permissionUuid;
    }

    /**
     * @param permissionUuid the permissionUuid to set
     */
    public void setPermissionUuid(String permissionUuid) {
        this.permissionUuid = permissionUuid;
    }

    /**
     * @return the resourceEntity
     */
    public ResourceEntity getResourceEntity() {
        return resourceEntity;
    }

    /**
     * @param resourceEntity the resourceEntity to set
     */
    public void setResourceEntity(ResourceEntity resourceEntity) {
        this.resourceEntity = resourceEntity;
    }

    /**
     * @return the roleEntity
     */
    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    /**
     * @param roleEntity the roleEntity to set
     */
    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }

    /**
     * @return the operationEntitys
     */
    public Set<OperationEntity> getOperationEntitys() {
        return operationEntitys;
    }

    /**
     * @param operationEntitys the operationEntitys to set
     */
    public void setOperationEntitys(Set<OperationEntity> operationEntitys) {
        this.operationEntitys = operationEntitys;
    }

  





}
