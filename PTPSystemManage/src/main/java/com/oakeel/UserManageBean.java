/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel;

import com.oakeel.ejb.entityAndDao.organization.OrganizationDaoLocal;
import com.oakeel.ejb.entityAndDao.organization.OrganizationEntity;
import com.oakeel.ejb.entityAndDao.user.UserDaoLocal;
import com.oakeel.ejb.entityAndDao.user.UserEntity;
import com.oakeel.ejb.transaction.InitEjbLocal;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author root
 */
@ManagedBean
@ViewScoped
public class UserManageBean {

    /**
     * Creates a new instance of UserManageBean
     */
    @EJB
    InitEjbLocal initEjbLocal;
    @EJB
    UserDaoLocal userDaoLocal;
    private UserEntity newUser=new UserEntity();
    private List<UserEntity> userEntitys;//用户表数据源
    private TreeNode rootNode;//机构树根节点
    private TreeNode selectNode;//选择的机构树
    private List<OrganizationEntity> orglist;
    private UserEntity deleteUserEntity;
    @EJB
    OrganizationDaoLocal orginazationDaoLocal;
    public UserManageBean() {
    }
   
    @PostConstruct
    public void init()
    {
        orglist=orginazationDaoLocal.getAllOrganization();
        OrganizationEntity rootOrg=orginazationDaoLocal.getRoot();
        if(rootOrg!=null)
        {
            rootNode=new DefaultTreeNode("department",rootOrg , null);
            getAllNode(rootOrg,rootNode);
        }
        int i=0;
    }
     public void saveUser(RowEditEvent event) {
         UserEntity temp=(UserEntity) event.getObject();
         userDaoLocal.updateUser(temp);
    }
     public void deleteUser()
     {
         if(deleteUserEntity!=null)
         {
             userDaoLocal.deleteUser(deleteUserEntity);
         }
     }
     public void addNewUser()
     {
         userDaoLocal.addUser(newUser);
     }
    public void getAllNode(OrganizationEntity org,TreeNode node)
    {
        Set<OrganizationEntity> orglist=org.getChildOrganizationEntitys();
        TreeNode child = new DefaultTreeNode("department", org, node);
        if(!orglist.isEmpty())
        {
            Iterator i = orglist.iterator();//先迭代出来  
          
            while(i.hasNext()){//遍历  
                getAllNode((OrganizationEntity) i.next(),child);
            } 
        }
    }
    public void xxx()
    {
        initEjbLocal.InitDB();
        
    }
    public void unclassedUser()
    {
        setUserEntitys(userDaoLocal.getUnclassedUser());
        int i=0;
    }
    public void allUser()
    {
        setUserEntitys(userDaoLocal.getAllUser());
        int i=0;
    }
    public List<OrganizationEntity> getAllOrganization()
    {
        List<OrganizationEntity> ss= orginazationDaoLocal.getAllOrganization();
        return orginazationDaoLocal.getAllOrganization();
    }
    public void test()
    {
        if(selectNode==null)
            setUserEntitys(userDaoLocal.getAllUser());
        else
        {
            OrganizationEntity org=(OrganizationEntity)selectNode.getData();
            setUserEntitys(userDaoLocal.getUsersByOrganization(org));
        }
        int i=0;
    }



    /**
     * @return the rootNode
     */
    public TreeNode getRootNode() {
        return rootNode;
    }

    /**
     * @param rootNode the rootNode to set
     */
    public void setRootNode(TreeNode rootNode) {
        this.rootNode = rootNode;
    }

    /**
     * @return the selectNode
     */
    public TreeNode getSelectNode() {
        return selectNode;
    }

    /**
     * @param selectNode the selectNode to set
     */
    public void setSelectNode(TreeNode selectNode) {
        this.selectNode = selectNode;
    }

    /**
     * @return the userEntitys
     */
    public List<UserEntity> getUserEntitys() {
        return userEntitys;
    }

    /**
     * @param userEntitys the userEntitys to set
     */
    public void setUserEntitys(List<UserEntity> userEntitys) {
        this.userEntitys = userEntitys;
    }

    /**
     * @return the orglist
     */
    public List<OrganizationEntity> getOrglist() {
        return orglist;
    }

    /**
     * @param orglist the orglist to set
     */
    public void setOrglist(List<OrganizationEntity> orglist) {
        this.orglist = orglist;
    }

    /**
     * @return the newUser
     */
    public UserEntity getNewUser() {
        return newUser;
    }

    /**
     * @param newUser the newUser to set
     */
    public void setNewUser(UserEntity newUser) {
        this.newUser = newUser;
    }

    /**
     * @return the deleteUserEntity
     */
    public UserEntity getDeleteUserEntity() {
        return deleteUserEntity;
    }

    /**
     * @param deleteUserEntity the deleteUserEntity to set
     */
    public void setDeleteUserEntity(UserEntity deleteUserEntity) {
        this.deleteUserEntity = deleteUserEntity;
    }


    
}
