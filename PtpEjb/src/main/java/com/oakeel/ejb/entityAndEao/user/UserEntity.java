/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel.ejb.entityAndEao.user;

import com.oakeel.ejb.entityAndEao.organization.OrganizationEntity;
import com.oakeel.ejb.entityAndEao.role.RoleEntity;
import com.oakeel.ejb.entityAndEao.userSet.UserSetEntity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author root
 */
@Entity
public class UserEntity implements Serializable {
    public UserEntity()
    {
        userUuid=UUID.randomUUID().toString();
    }
    public UserEntity(String param,String password,UserEnum userEnum)
    {
        userUuid=UUID.randomUUID().toString();
        if(userEnum==UserEnum.用户名)
            this.name=param;
        if(userEnum==UserEnum.电话)
            this.telephone=param;
        if(userEnum==UserEnum.邮箱)
            this.email=param;
        this.password=password;
    }
    public UserEntity(String name,String telephone,String email,String password,int priority)
    {
        userUuid=UUID.randomUUID().toString();
        this.name=name;
        this.telephone=telephone;
        this.email=email;
        this.password=password;
        this.priority=priority;
    }
    static long serialVersionUID = 1L;
    @Id
    @Column(length=36)
    private String userUuid;
    @Column(nullable=false)
    private String name;
    private int priority=0;
    @Column(nullable=false)
    private String password;
    private String telephone="";
    private String email="";
    private String salt="";
    private Boolean locked=false;
    //用户与机构是多对一关系，主控在用户
    @ManyToOne(cascade={CascadeType.MERGE})//级联修改user=>org
    private OrganizationEntity organizationEntity;
    //用户与角色是多对多的关系，主控在用户
    @ManyToMany(cascade={CascadeType.MERGE})//级联修改user=>role
    private Set<RoleEntity> roleEntitys=new HashSet<>();
    @OneToOne(mappedBy="userEntity")
    UserSetEntity userSetEntity;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userUuid != null ? userUuid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserEntity)) {
            return false;
        }
        UserEntity other = (UserEntity) object;
        if ((this.userUuid == null && other.userUuid != null) || (this.userUuid != null && !this.userUuid.equals(other.userUuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oakeel.EntityAndDao.User.UserEntity[ id=" + userUuid + " ]";
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
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * @param salt the salt to set
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * @return the locked
     */
    public Boolean getLocked() {
        return locked;
    }

    /**
     * @param locked the locked to set
     */
    public void setLocked(Boolean locked) {
        this.locked = locked;
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
     * @return the userUuid
     */
    public String getUserUuid() {
        return userUuid;
    }

    /**
     * @param userUuid the userUuid to set
     */
    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    /**
     * @return the organizationEntity
     */
    public OrganizationEntity getOrganizationEntity() {
        return organizationEntity;
    }

    /**
     * @param organizationEntity the organizationEntity to set
     */
    public void setOrganizationEntity(OrganizationEntity organizationEntity) {
        this.organizationEntity = organizationEntity;
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

 
    
}
