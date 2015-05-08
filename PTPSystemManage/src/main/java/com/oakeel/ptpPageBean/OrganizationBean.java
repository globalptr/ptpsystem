/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel.ptpPageBean;

import com.oakeel.ejb.entityAndEao.organization.OrganizationEaoLocal;
import com.oakeel.ejb.entityAndEao.organization.OrganizationEntity;
import com.oakeel.ejb.transaction.InitEjbLocal;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
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
    /**
     * Creates a new instance of OrganizationRequestBean
     */
    public OrganizationBean() {
    }
     /**
     * Creates a new instance of OrganizationSesionBean
     */
    @EJB
    OrganizationEaoLocal organizationEaoLocal;
    @EJB
    InitEjbLocal xxx;
    private TreeNode rootNode;
    Boolean flushTree = false;
    private TreeNode selectNode;//选择的节点
    private OrganizationEntity selectOrganizationEntity=new OrganizationEntity();
    private OrganizationEntity newOrganization=new OrganizationEntity();
    String updateUuid;//修改界面的uuid
    String updateOrganizationName;
    String updateOrganizationPriority;

    @PostConstruct
    public void init() {
         OrganizationEntity rootOrg = organizationEaoLocal.getRoot();
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
    //保存修改的节点
    public void saveOrganization()
    {
        organizationEaoLocal.updateOrganizationEntity(selectOrganizationEntity);
        updateNode(selectOrganizationEntity,rootNode);
    }
    //更新指定的节点的数据
    public void updateNode(OrganizationEntity org,TreeNode node)
    {
        List<TreeNode> nodelist=node.getChildren();
        for(TreeNode item:nodelist)
        {
            OrganizationEntity temp=(OrganizationEntity)item.getData();
            if(temp.getOrganizationUuid().equals(org.getOrganizationUuid()))
            {
                node.getChildren().remove(item);
                DefaultTreeNode defaultTreeNode = new DefaultTreeNode("department", org, node);
                throw new RuntimeException();
            }
            else
            {
                updateNode(org,item);
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
          organizationEaoLocal.deleteOrganization(deleteOrg);
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
        if(selectNode!=null&&newOrganization!=null)
        {
            OrganizationEntity parent=(OrganizationEntity)selectNode.getData();
            OrganizationEntity temp=organizationEaoLocal.AddNewOrganization(newOrganization, parent);
            TreeNode property = new DefaultTreeNode("department",temp, selectNode);
            newOrganization=new OrganizationEntity();//这里必须新建组织，不然之前添加的和现在添加的节点数据指向一个对象，导致错误
        }
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

    /**
     * @return the selectOrganizationEntity
     */
    public OrganizationEntity getSelectOrganizationEntity() {
       return selectOrganizationEntity;
    }

    /**
     * @param selectOrganizationEntity the selectOrganizationEntity to set
     */
    public void setSelectOrganizationEntity(OrganizationEntity selectOrganizationEntity) {
        this.selectOrganizationEntity = selectOrganizationEntity;
    }
    public void treeNodeToOrganizationEntity()
    {
        if(selectNode!=null)
        {
            OrganizationEntity temp=(OrganizationEntity)selectNode.getData();
            if(!"".equals(temp.getName()))
            {
                setSelectOrganizationEntity(temp);
            }
        }
        
    }
 
  
}
