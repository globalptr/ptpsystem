/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel.ptpEntityAndCtrl.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author root
 */
@Stateless
@LocalBean
public class UserDao {
    @PersistenceContext(unitName="ptpejb_pu")
    private EntityManager em;
    public void addNewUser(UserEntity user)
    {
        em.persist(user);
        em.flush();
    }
    public Boolean validationUserEntity(UserEntity user)
    {
        CriteriaBuilder builder=em.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query=builder.createQuery(UserEntity.class);
        Root<UserEntity> s=query.from(UserEntity.class);
        
        if(user.getUserName()==null&&user.getTelephoneNum()==null&&user.geteMail()==null)
        {
            return false;
        }
        else if(user.getUserName()!=null)
        {
            return false;
        }
        else if(user.getTelephoneNum()!=null)
        {
            return false;
        }
        else
        {
            return false;
        }
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
