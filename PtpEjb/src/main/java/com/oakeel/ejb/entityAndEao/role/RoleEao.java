/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel.ejb.entityAndEao.role;

import com.oakeel.ejb.entityAndEao.resource.ResourceEntity;
import com.oakeel.ejb.entityAndEao.permission.PermissionEntity;
import java.util.List;
import java.util.Set;
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
public class RoleEao implements RoleEaoLocal {
    @PersistenceContext(unitName="ptpEjbPu")
    EntityManager em;
    @Override
    public void addNewRole(RoleEntity role)
    {
        em.persist(role);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<RoleEntity> getAllRole() {
        CriteriaBuilder builder=em.getCriteriaBuilder();
        CriteriaQuery<RoleEntity> query=builder.createQuery(RoleEntity.class);
        Root<RoleEntity> s=query.from(RoleEntity.class);
        query.select(s);        
        TypedQuery<RoleEntity> q=em.createQuery(query);
        return q.getResultList();
    }

    @Override
    public void deleteRole(RoleEntity role) {
        em.remove(em.merge(role));
    }

    @Override
    public void updateRole(RoleEntity role) {
        em.merge(role);
    }

    @Override
    public void deletePermission(RoleEntity role, PermissionEntity permission) {
        role.getPermissions().remove(permission);
        em.merge(role);
    }
}
