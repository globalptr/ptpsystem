/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel.ejb.entityAndEao.roleResource;

import com.oakeel.ejb.entityAndEao.resource.ResourceEntity;
import com.oakeel.ejb.entityAndEao.role.RoleEntity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author root
 */
@Entity
public class RoleResourceEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String roleResourceUuid;
    public RoleResourceEntity()
    {
        roleResourceUuid=UUID.randomUUID().toString();
    }
    public RoleResourceEntity(ResourceEntity resource)
    {
        roleResourceUuid=UUID.randomUUID().toString();
        this.resource=resource;
    }
    @OneToOne
    private ResourceEntity resource;
    
    private Boolean addPower=true;
    private Boolean deletePower=true;
    private Boolean viewPwer=true;
    private Boolean updatePower=true;
    //角色与资源是多对多的关系，主控在角色
    @ManyToMany(mappedBy="roleResourceEntitys")
    private Set<RoleEntity> roleEntitys;
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getRoleResourceUuid() != null ? getRoleResourceUuid().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoleResourceEntity)) {
            return false;
        }
        RoleResourceEntity other = (RoleResourceEntity) object;
        if ((this.getRoleResourceUuid() == null && other.getRoleResourceUuid() != null) || (this.getRoleResourceUuid() != null && !this.roleResourceUuid.equals(other.roleResourceUuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oakeel.ejb.entityAndEao.RoleResource.RoleResourceEntity[ id=" + getRoleResourceUuid() + " ]";
    }

    /**
     * @return the roleResourceUuid
     */
    public String getRoleResourceUuid() {
        return roleResourceUuid;
    }

    /**
     * @param roleResourceUuid the roleResourceUuid to set
     */
    public void setRoleResourceUuid(String roleResourceUuid) {
        this.roleResourceUuid = roleResourceUuid;
    }


    /**
     * @return the roleEntitys
     */
    public Set<RoleEntity> getRoleEntitys() {
        return roleEntitys;
    }

    /**
     * @param roleEntitys the roleEntitys to set
     */
    public void setRoleEntitys(Set<RoleEntity> roleEntitys) {
        this.roleEntitys = roleEntitys;
    }

    /**
     * @return the resource
     */
    public ResourceEntity getResource() {
        return resource;
    }

    /**
     * @param resource the resource to set
     */
    public void setResource(ResourceEntity resource) {
        this.resource = resource;
    }

    /**
     * @return the addPower
     */
    public Boolean getAddPower() {
        return addPower;
    }

    /**
     * @param addPower the addPower to set
     */
    public void setAddPower(Boolean addPower) {
        this.addPower = addPower;
    }

    /**
     * @return the deletePower
     */
    public Boolean getDeletePower() {
        return deletePower;
    }

    /**
     * @param deletePower the deletePower to set
     */
    public void setDeletePower(Boolean deletePower) {
        this.deletePower = deletePower;
    }

    /**
     * @return the viewPwer
     */
    public Boolean getViewPwer() {
        return viewPwer;
    }

    /**
     * @param viewPwer the viewPwer to set
     */
    public void setViewPwer(Boolean viewPwer) {
        this.viewPwer = viewPwer;
    }

    /**
     * @return the updatePower
     */
    public Boolean getUpdatePower() {
        return updatePower;
    }

    /**
     * @param updatePower the updatePower to set
     */
    public void setUpdatePower(Boolean updatePower) {
        this.updatePower = updatePower;
    }

 
    
}
