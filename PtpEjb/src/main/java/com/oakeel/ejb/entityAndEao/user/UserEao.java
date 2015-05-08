/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel.ejb.entityAndEao.user;


import com.oakeel.ejb.entityAndEao.organization.OrganizationEntity;
import com.oakeel.ejb.entityAndEao.role.RoleEntity;
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
public class UserEao implements UserEaoLocal {
    @PersistenceContext(unitName="ptpEjbPu")
    EntityManager em;
    @Override
    public void addUser(UserEntity user)
    {
        user.setUserUuid(UUID.randomUUID().toString());
        em.persist(user);
    }
    
    @Override
    public Boolean  validateUser(UserEntity user)
    {
        if(user.getName()==null&&user.getTelephone()==null&&user.getEmail()==null)
        {
            return false;
        }
        else if(user.getName()!=null)
        {
            CriteriaBuilder builder=em.getCriteriaBuilder();
            CriteriaQuery<UserEntity> query=builder.createQuery(UserEntity.class);
            Root<UserEntity> s=query.from(UserEntity.class);
            query.select(s).where(builder.equal(s.get(UserEntity_.name), user.getName()));        
            TypedQuery<UserEntity> q=em.createQuery(query);
            return !q.getResultList().isEmpty();
        }
        else if(user.getEmail()!=null)
        {
            CriteriaBuilder builder=em.getCriteriaBuilder();
            CriteriaQuery<UserEntity> query=builder.createQuery(UserEntity.class);
            Root<UserEntity> s=query.from(UserEntity.class);
            //降序排列
            query.select(s).where(builder.equal(s.get(UserEntity_.email), user.getEmail())).orderBy(builder.desc(s.get(UserEntity_.priority)));        
            TypedQuery<UserEntity> q=em.createQuery(query);
            return !q.getResultList().isEmpty();
        }
        else if(user.getTelephone()!=null)
        {
            CriteriaBuilder builder=em.getCriteriaBuilder();
            CriteriaQuery<UserEntity> query=builder.createQuery(UserEntity.class);
            Root<UserEntity> s=query.from(UserEntity.class);
            query.select(s).where(builder.equal(s.get(UserEntity_.telephone), user.getTelephone()));        
            TypedQuery<UserEntity> q=em.createQuery(query);
            return !q.getResultList().isEmpty();
        }
        else
        {
            return false;
        }
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<UserEntity> getAllUser() {
        CriteriaBuilder builder=em.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query=builder.createQuery(UserEntity.class);
        Root<UserEntity> s=query.from(UserEntity.class);
        query.select(s);        
        TypedQuery<UserEntity> q=em.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<UserEntity> getUsersByOrganization(OrganizationEntity org) {
        if(org==null)
            return null;
        CriteriaBuilder builder=em.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query=builder.createQuery(UserEntity.class);
        Root<UserEntity> s=query.from(UserEntity.class);
        query.select(s).where(builder.equal(s.get(UserEntity_.organizationEntity), org));
        TypedQuery<UserEntity> q=em.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<UserEntity> getUnclassedUser() {
        CriteriaBuilder builder=em.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query=builder.createQuery(UserEntity.class);
        Root<UserEntity> s=query.from(UserEntity.class);
        query.select(s).where(s.get(UserEntity_.organizationEntity).isNull());        
        TypedQuery<UserEntity> q=em.createQuery(query);
        List<UserEntity> ss=q.getResultList();
        return q.getResultList();
    }

    @Override
    public void updateUser(UserEntity user) {
        em.merge(user);
    }

    @Override
    public void deleteUser(UserEntity user) {
        em.remove(em.merge(user));
    }

    @Override
    public void deleteRole(UserEntity user, RoleEntity role) {
        Set<RoleEntity> roles=user.getRoleEntitys();
        for(RoleEntity item:roles)
        {
            if(item.getRoleUuid().equals(role.getRoleUuid()))
            {
                roles.remove(item);
                break;
            }
        }
        em.merge(user);
    }

    @Override
    public void addUserRole(UserEntity user, RoleEntity role) {
        user.getRoleEntitys().add(role);
        em.merge(user);
    }
}
