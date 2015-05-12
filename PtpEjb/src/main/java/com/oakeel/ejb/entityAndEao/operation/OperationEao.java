/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel.ejb.entityAndEao.operation;

import com.oakeel.ejb.entityAndEao.permission.PermissionEaoLocal;
import com.oakeel.ejb.entityAndEao.permission.PermissionEntity;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author root
 */
@Stateless
public class OperationEao implements OperationEaoLocal {

    @EJB
    PermissionEaoLocal permissionEaoLocal;
    @PersistenceContext(unitName="ptpEjbPu")
    EntityManager em;
    @Override
    public void addNewOperation(OperationEntity operation) {
        em.persist(operation);
    }

    @Override
    public void deleteOperation(OperationEntity operation) {
        em.remove(em.merge(operation));
        //删除操作，必须把所有的权限中的此操作全部删除才行
        List<PermissionEntity> permissions=permissionEaoLocal.getAllPermission();
        for(PermissionEntity item:permissions)
        {
            item.getOperationEntitys().remove(operation);
        }
    }

    @Override
    public void updateOperation(OperationEntity operation) {
        em.merge(operation);
    }

    @Override
    public List<OperationEntity> getAllOperation() {
        
        CriteriaBuilder builder=em.getCriteriaBuilder();
        CriteriaQuery<OperationEntity> query=builder.createQuery(OperationEntity.class);
        Root<OperationEntity> s=query.from(OperationEntity.class);
        query.select(s);        
        TypedQuery<OperationEntity> q=em.createQuery(query);
        return q.getResultList();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
