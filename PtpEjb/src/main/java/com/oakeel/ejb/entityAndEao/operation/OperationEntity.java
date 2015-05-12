/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel.ejb.entityAndEao.operation;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author root
 */
@Entity
public class OperationEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(length=36)
    private String operationUuid;
    public OperationEntity()
    {
        operationUuid=UUID.randomUUID().toString();
    }
    public OperationEntity(String word,String desc)
    {
        operationUuid=UUID.randomUUID().toString();
        this.permissionWord=word;
        this.permissionDesc=desc;
    }
    public OperationEntity(String word)
    {
        operationUuid=UUID.randomUUID().toString();
        this.permissionWord=word;
    }
    @Column(nullable=false,length=50)
    private String permissionWord;
    @Column(length=200)
    private String permissionDesc="";
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getOperationUuid() != null ? getOperationUuid().hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OperationEntity)) {
            return false;
        }
        OperationEntity other = (OperationEntity) object;
        if ((this.getOperationUuid() == null && other.getOperationUuid() != null) || (this.getOperationUuid() != null && !this.operationUuid.equals(other.operationUuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oakeel.ejb.entityAndEao.operation.OperationEntity[ id=" + getOperationUuid() + " ]";
    }

    /**
     * @return the permissionWord
     */
    public String getPermissionWord() {
        return permissionWord;
    }

    /**
     * @param permissionWord the permissionWord to set
     */
    public void setPermissionWord(String permissionWord) {
        this.permissionWord = permissionWord;
    }

    /**
     * @return the permissionDesc
     */
    public String getPermissionDesc() {
        return permissionDesc;
    }

    /**
     * @param permissionDesc the permissionDesc to set
     */
    public void setPermissionDesc(String permissionDesc) {
        this.permissionDesc = permissionDesc;
    }

    /**
     * @return the operationUuid
     */
    public String getOperationUuid() {
        return operationUuid;
    }

    /**
     * @param operationUuid the operationUuid to set
     */
    public void setOperationUuid(String operationUuid) {
        this.operationUuid = operationUuid;
    }
    
}
