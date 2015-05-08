/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel.ejb.entityAndEao.resource;

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
public class ResourceEao implements ResourceEaoLocal {
    @PersistenceContext(unitName="ptpEjbPu")
    EntityManager em;
    @Override
    public void addNewResource(ResourceEntity resource)
    {
        em.persist(resource);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<ResourceEntity> getAllResource() {
        CriteriaBuilder builder=em.getCriteriaBuilder();
        CriteriaQuery<ResourceEntity> query=builder.createQuery(ResourceEntity.class);
        Root<ResourceEntity> s=query.from(ResourceEntity.class);
        query.select(s);        
        TypedQuery<ResourceEntity> q=em.createQuery(query);
        return q.getResultList();
    }

    @Override
    public void updateResource(ResourceEntity resource) {
        em.merge(resource);
    }

    @Override
    public void deleteReource(ResourceEntity resource) {
        em.remove(em.merge(resource));
    }
    
}
