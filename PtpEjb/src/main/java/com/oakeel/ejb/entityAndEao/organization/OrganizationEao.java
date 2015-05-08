/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package com.oakeel.ejb.entityAndEao.organization;
import com.oakeel.ejb.entityAndEao.resource.ResourceEntity;
import com.oakeel.ejb.entityAndEao.user.UserEntity;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
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
public class OrganizationEao implements OrganizationEaoLocal {

    @PersistenceContext(unitName="ptpEjbPu")
    EntityManager em;
    @Override
    public OrganizationEntity AddNewOrganization(OrganizationEntity organization,OrganizationEntity parent){
        if(parent==null||parent.getOrganizationUuid()==null)
            return null;
        organization.setOrganizationUuid(UUID.randomUUID().toString());
        organization.setParentUuid(parent.getOrganizationUuid());
        
        em.persist(organization);
        //首先将parent转为托管状态，
        parent.getChildOrganizationEntitys().add(organization);
        OrganizationEntity temp=em.merge(parent);
        return organization;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<OrganizationEntity> getAllOrganization() {
        CriteriaBuilder builder=em.getCriteriaBuilder();
        CriteriaQuery<OrganizationEntity> query=builder.createQuery(OrganizationEntity.class);
        Root<OrganizationEntity> s=query.from(OrganizationEntity.class);
        query.select(s);        
        TypedQuery<OrganizationEntity> q=em.createQuery(query);
        return q.getResultList();
    
    }

    
    @Override
    public OrganizationEntity getRoot() {
        CriteriaBuilder builder=em.getCriteriaBuilder();
        CriteriaQuery<OrganizationEntity> query=builder.createQuery(OrganizationEntity.class);
        Root<OrganizationEntity> s=query.from(OrganizationEntity.class);
        query.select(s).where(s.get(OrganizationEntity_.parentUuid).isNull());
        TypedQuery<OrganizationEntity> qx=em.createQuery(query);
        if(qx.getResultList().size()!=1)
        {
            return null;//必须只有一个根
        }
        else
        {
            return qx.getResultList().get(0);
        }
    }



    @Override
    public List<OrganizationEntity> getOrganizationEntityByName(String name) {
        CriteriaBuilder builder=em.getCriteriaBuilder();
        CriteriaQuery<OrganizationEntity> query=builder.createQuery(OrganizationEntity.class);
        Root<OrganizationEntity> s=query.from(OrganizationEntity.class);
        query.select(s).where(builder.equal(s.get(OrganizationEntity_.name), name));        
        TypedQuery<OrganizationEntity> q=em.createQuery(query);
        return q.getResultList();
    }

    @Override
    public void deleteOrganization(OrganizationEntity org) {
        //递归将所属用户机构字段设置未null
        deleteOrganization_sub(org);
        //级联删除
        OrganizationEntity temp=em.merge(org);
        em.remove(temp);
    }
    public void deleteOrganization_sub(OrganizationEntity org)
    {
        if(!org.getChildOrganizationEntitys().isEmpty())
        {
            Set<OrganizationEntity> orgs=org.getChildOrganizationEntitys();
            Iterator<OrganizationEntity> it=orgs.iterator();
            while(it.hasNext())
            {
                deleteOrganization_sub(it.next());
            }
        }
        Set<UserEntity> users=org.getUserEntitys();
        if(!users.isEmpty())
        {
            Iterator<UserEntity> it=users.iterator();
            while(it.hasNext())
            {
                UserEntity user=it.next();
                user.setOrganizationEntity(null);
                em.merge(user);
            }
        }
        
    }

    @Override
    public OrganizationEntity getOrganizationByUuid(String uuid) {
        return em.find(OrganizationEntity.class, uuid);
    }

    @Override
    public void updateOrganizationEntity(OrganizationEntity org) {
        em.merge(org);
    }

    
}
