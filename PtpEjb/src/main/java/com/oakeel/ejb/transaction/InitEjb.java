/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oakeel.ejb.transaction;

import com.oakeel.ejb.entityAndDao.organization.OrganizationEntity;
import com.oakeel.ejb.entityAndDao.resource.ResourceEntity;
import com.oakeel.ejb.entityAndDao.role.RoleEntity;
import com.oakeel.ejb.entityAndDao.user.UserEntity;
import com.oakeel.ejb.entityAndDao.user.UserEnum;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class InitEjb implements InitEjbLocal , Serializable{

    @PersistenceContext(unitName = "ptpEjbPu")
    EntityManager em;

    @Override
    public void InitDB() {
        //初始化部门
        OrganizationEntity root = new OrganizationEntity("总部", 1, 0);
        OrganizationEntity d1 = new OrganizationEntity("理财部", 1, 1);
        OrganizationEntity d2 = new OrganizationEntity("财务部", 1, 1);
        OrganizationEntity d3 = new OrganizationEntity("人事部", 1, 1);
        OrganizationEntity d4 = new OrganizationEntity("技术部", 1, 1);
        OrganizationEntity d5 = new OrganizationEntity("运维部", 1, 1);
        em.persist(root);
        d1.setParentUuid(root.getOrganizationUuid());
        d2.setParentUuid(root.getOrganizationUuid());
        d3.setParentUuid(root.getOrganizationUuid());
        d4.setParentUuid(root.getOrganizationUuid());
        d5.setParentUuid(root.getOrganizationUuid());
        root.getChildOrganizationEntitys().add(d1);
        root.getChildOrganizationEntitys().add(d2);
        root.getChildOrganizationEntitys().add(d3);
        root.getChildOrganizationEntitys().add(d4);
        root.getChildOrganizationEntitys().add(d5);
        em.persist(d1);
        em.persist(d2);
        em.persist(d3);
        em.persist(d4);
        em.persist(d5);
        //初始化用户孔融 、屈原、李白、杜甫、 白居易、刘禹锡、李商隐、杜牧、李贺、张九龄、王之涣、王勃、皇甫松、李珣、李煜、高适、王维、刘长卿、韩愈、柳宗元、顾况、李益、范仲淹、 欧阳修、陆游、辛弃疾、岳飞

        UserEntity user1 = new UserEntity("孔融", "ttjc", UserEnum.用户名创建);
        UserEntity user2 = new UserEntity("屈原", "ttjc", UserEnum.用户名创建);
        UserEntity user3 = new UserEntity("李白", "ttjc", UserEnum.用户名创建);
        UserEntity user4 = new UserEntity("杜甫", "ttjc", UserEnum.用户名创建);
        UserEntity user5 = new UserEntity("白居易", "ttjc", UserEnum.用户名创建);
        UserEntity user6 = new UserEntity("刘禹锡", "ttjc", UserEnum.用户名创建);
        UserEntity user7 = new UserEntity("李商隐", "ttjc", UserEnum.用户名创建);
        UserEntity user8 = new UserEntity("杜牧", "ttjc", UserEnum.用户名创建);
        UserEntity user9 = new UserEntity("李贺", "ttjc", UserEnum.用户名创建);
        UserEntity user10 = new UserEntity("张九龄", "ttjc", UserEnum.用户名创建);
        UserEntity user11 = new UserEntity("刘禹锡", "ttjc", UserEnum.用户名创建);
        UserEntity user12 = new UserEntity("王之涣", "ttjc", UserEnum.用户名创建);
        UserEntity user13 = new UserEntity("孔融", "ttjc", UserEnum.用户名创建);
        em.persist(user1);
        em.persist(user2);
        em.persist(user3);
        em.persist(user4);
        em.persist(user5);
        em.persist(user6);
        em.persist(user7);
        em.persist(user8);
        em.persist(user9);
        em.persist(user10);
        em.persist(user11);
        em.persist(user12);
        em.persist(user13);
        RoleEntity role1 = new RoleEntity("超级管理员");
        RoleEntity role2 = new RoleEntity("管理员");
        RoleEntity role3 = new RoleEntity("运维经理");
        RoleEntity role4 = new RoleEntity("技术经理");
        RoleEntity role5 = new RoleEntity("财务主管");
        RoleEntity role6 = new RoleEntity("理财总经理");
        em.persist(role1);
        em.persist(role2);
        em.persist(role3);
        em.persist(role4);
        em.persist(role5);
        em.persist(role6);
        ResourceEntity resource1 = new ResourceEntity("财务管理", "all");
        ResourceEntity resource2 = new ResourceEntity("财务管理", "create");
        ResourceEntity resource3 = new ResourceEntity("财务管理", "delete");
        ResourceEntity resource4 = new ResourceEntity("财务管理", "update");
        ResourceEntity resource5 = new ResourceEntity("财务管理", "query");

        ResourceEntity resource11 = new ResourceEntity("用户管理", "all");
        ResourceEntity resource22 = new ResourceEntity("用户管理", "create");
        ResourceEntity resource33 = new ResourceEntity("用户管理", "delete");
        ResourceEntity resource44 = new ResourceEntity("用户管理", "update");
        ResourceEntity resource55 = new ResourceEntity("用户管理", "query");
        em.persist(resource1);
        em.persist(resource2);
        em.persist(resource3);
        em.persist(resource4);
        em.persist(resource5);
        em.persist(resource11);
        em.persist(resource22);
        em.persist(resource33);
        em.persist(resource44);
        em.persist(resource55);
        ///
        user1.getRoleEntitys().add(role1);
        user1.getRoleEntitys().add(role2);
        user1.getRoleEntitys().add(role3);
        user2.getRoleEntitys().add(role2);
        user2.getRoleEntitys().add(role3);
        user2.getRoleEntitys().add(role4);
        role1.getResourceEntity().add(resource1);
        role1.getResourceEntity().add(resource2);
        role1.getResourceEntity().add(resource3);

        role2.getResourceEntity().add(resource2);
        role2.getResourceEntity().add(resource3);
        role2.getResourceEntity().add(resource4);

        user1.setOrganizationEntity(d1);
        user2.setOrganizationEntity(d1);
        user3.setOrganizationEntity(d1);
        user4.setOrganizationEntity(d2);
        user5.setOrganizationEntity(d2);
        em.merge(user1);
        em.merge(user2);
        em.merge(role1);
        em.merge(role2);
    }
    @Override
    public void InitDepartment()
    {
        //初始化部门
        OrganizationEntity root = new OrganizationEntity("总部", 1, 0);
        OrganizationEntity d1 = new OrganizationEntity("理财部", 1, 1);
        OrganizationEntity d2 = new OrganizationEntity("财务部", 1, 1);
        OrganizationEntity d3 = new OrganizationEntity("人事部", 1, 1);
        OrganizationEntity d4 = new OrganizationEntity("技术部", 1, 1);
        OrganizationEntity d5 = new OrganizationEntity("运维部", 1, 1);
        em.persist(root);
        d1.setParentUuid(root.getOrganizationUuid());
        d2.setParentUuid(root.getOrganizationUuid());
        d3.setParentUuid(root.getOrganizationUuid());
        d4.setParentUuid(root.getOrganizationUuid());
        d5.setParentUuid(root.getOrganizationUuid());
        root.getChildOrganizationEntitys().add(d1);
        root.getChildOrganizationEntitys().add(d2);
        root.getChildOrganizationEntitys().add(d3);
        root.getChildOrganizationEntitys().add(d4);
        root.getChildOrganizationEntitys().add(d5);
        em.persist(d1);
        em.persist(d2);
        em.persist(d3);
        em.persist(d4);
        em.persist(d5);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
