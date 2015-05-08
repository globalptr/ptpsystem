/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel.ejb.entityAndEao.roleResource;

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
public class RoleResourceEao implements RoleResourceEaoLocal {

    @PersistenceContext(unitName="ptpEjbPu")
    EntityManager em;


    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void addRoleResource(RoleResourceEntity roleResource) {
        em.persist(em.merge(roleResource));
    }

    @Override
    public void deleteRoleResource(RoleResourceEntity roleResource) {
        em.remove(em.merge(roleResource));
    }

    @Override
    public void updateRoleResource(RoleResourceEntity roleResource) {
        em.merge(roleResource);
    }

    @Override
    public List<RoleResourceEntity> viewAllRoleResource() {
        CriteriaBuilder builder=em.getCriteriaBuilder();
        CriteriaQuery<RoleResourceEntity> query=builder.createQuery(RoleResourceEntity.class);
        Root<RoleResourceEntity> s=query.from(RoleResourceEntity.class);
        query.select(s);        
        TypedQuery<RoleResourceEntity> q=em.createQuery(query);
        return q.getResultList();
    }


}
