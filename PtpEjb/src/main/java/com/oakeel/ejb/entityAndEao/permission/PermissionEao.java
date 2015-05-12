/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel.ejb.entityAndEao.permission;

import com.oakeel.ejb.entityAndEao.resource.ResourceEntity;
import com.oakeel.ejb.entityAndEao.role.RoleEntity;
import java.util.List;
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
public class PermissionEao implements PermissionEaoLocal {

    @PersistenceContext(unitName="ptpEjbPu")
    EntityManager em;


    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void addPermission(PermissionEntity roleResource){
        em.persist(roleResource);
    }
    
    @Override
    public void deletePermission(PermissionEntity permission){
        em.remove(em.merge(permission));
    }
    @Override
    public void updatePermission(PermissionEntity permission)
    {
        em.merge(permission);
    }

    @Override
    public List<PermissionEntity> getAllPermission() {
        CriteriaBuilder builder=em.getCriteriaBuilder();
        CriteriaQuery<PermissionEntity> query=builder.createQuery(PermissionEntity.class);
        Root<PermissionEntity> s=query.from(PermissionEntity.class);
        query.select(s);        
        TypedQuery<PermissionEntity> q=em.createQuery(query);
        return q.getResultList();
    }



}
