/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel.ptpPageBean;

import com.oakeel.PtpSessionBean;
import com.oakeel.ejb.entityAndDao.organization.OrganizationDaoLocal;
import com.oakeel.ejb.entityAndDao.organization.OrganizationEntity;
import com.oakeel.ejb.transaction.InitEjbLocal;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author root
 */
@ManagedBean
@ViewScoped

public class OrganizationBean implements Serializable{
    @ManagedProperty(value="#{nptpSessionBean}")
    private PtpSessionBean ptpSessionBean;
    /**
     * Creates a new instance of OrganizationRequestBean
     */
    public OrganizationBean() {
    }
     /**
     * Creates a new instance of OrganizationSesionBean
     */
    @EJB
    OrganizationDaoLocal orginazationDaoLocal;
    @EJB
    InitEjbLocal xxx;
    private TreeNode rootNode;
    Boolean flushTree = false;
    private TreeNode selectNode;
    private OrganizationEntity newOrganization=new OrganizationEntity();

    @PostConstruct
    public void init() {
         OrganizationEntity rootOrg = orginazationDaoLocal.getRoot();
        if(rootOrg!=null)
            getAllNode(rootOrg, null);
        int i = 0;
    }

    //得到组织的所有节点
    public void getAllNode(OrganizationEntity org, TreeNode node) {
        Set<OrganizationEntity> orglist = org.getChildOrganizationEntitys();
        if(node==null)
        {
            rootNode = new DefaultTreeNode("department", org, node);
            if (!orglist.isEmpty()) {
                Iterator i = orglist.iterator();//先迭代出来  

                while (i.hasNext()) {//遍历  
                    getAllNode((OrganizationEntity) i.next(), rootNode);
                }
            }
        }
        else
        {
            TreeNode child = new DefaultTreeNode("department", org, node);
            if (!orglist.isEmpty()) {
                Iterator i = orglist.iterator();//先迭代出来  

                while (i.hasNext()) {//遍历  
                    getAllNode((OrganizationEntity) i.next(), child);
                }
            }
        }
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


    public void tests() {
        xxx.InitDepartment();
    }
    public void deleteOrg() {
      if(selectNode!=null)
      {
          OrganizationEntity deleteOrg=(OrganizationEntity) selectNode.getData();
          orginazationDaoLocal.deleteOrganization(deleteOrg);
          //遍历所有节点删除指定节点
          try
          {
            deleteNode(deleteOrg,rootNode);
          }
          catch(Exception ex)
          {
              System.out.println("删除节点退出递归引发的正常的异常");
          }
      }
    }
    public void deleteNode(OrganizationEntity org,TreeNode node)
    {
        List<TreeNode> nodelist=node.getChildren();
        for(TreeNode item:nodelist)
        {
            OrganizationEntity temp=(OrganizationEntity)item.getData();
            if(temp.getOrganizationUuid().equals(org.getOrganizationUuid()))
            {
                node.getChildren().remove(item);
                throw new RuntimeException();
            }
            else
            {
                deleteNode(org,item);
            }
        }
    }
    public void addNewNode()
    {
         int i=0;
        if(selectNode!=null&&newOrganization!=null)
        {
            newOrganization.setOrganizationUuid(null);
            OrganizationEntity parent=(OrganizationEntity)selectNode.getData();
            orginazationDaoLocal.AddNewOrganization(newOrganization, parent);
            TreeNode property = new DefaultTreeNode("department",newOrganization, selectNode);
        }
    }
    /**
     * @return the ptpSessionBean
     */
    public PtpSessionBean getPtpSessionBean() {
        return ptpSessionBean;
    }

    /**
     * @param ptpSessionBean the ptpSessionBean to set
     */
    public void setPtpSessionBean(PtpSessionBean ptpSessionBean) {
        this.ptpSessionBean = ptpSessionBean;
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
     * @return the newOrganization
     */
    public OrganizationEntity getNewOrganization() {
        return newOrganization;
    }

    /**
     * @param newOrganization the newOrganization to set
     */
    public void setNewOrganization(OrganizationEntity newOrganization) {
        this.newOrganization = newOrganization;
    }
}
